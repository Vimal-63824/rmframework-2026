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
			webDB.switchToFrame(OpenPoRcLocators.CreateOpenPoFrame, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(OpenPoRcLocators.LocationId, Location);
			webDB.switchToFrame(OpenPoRcLocators.CreateOpenPoFrame, ElementType.Xpath);
			flag = webDB.waitForElement(OpenPoRcLocators.PoTypeSelect, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByText(OpenPoRcLocators.PoTypeSelect, OpenPOType, ElementType.Xpath);
				logs.logging("INFO", "Select Text Po Type Is: " + OpenPOType);
				commonFunc.searchAndSelectValueInFilters(OpenPoRcLocators.SupplierButton, Supplier);
				logs.logging("INFO", "Supplier Button Is Displayed" + Supplier);
				webDB.switchToFrame(OpenPoRcLocators.CreateOpenPoFrame, ElementType.Xpath);
				flag = webDB.waitForElement(OpenPoRcLocators.TickButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(OpenPoRcLocators.TickButton, ElementType.Xpath);
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(OpenPoRcLocators.OpenPoNo, ElementType.Xpath);
					if (flag) {
						String OpenPONumber = webDB.getTextFromElement(OpenPoRcLocators.OpenPoNo, ElementType.Xpath);
						logs.logging("INFO", "Get Text Open PO Number Is: " + OpenPONumber);
						VariableHandler.setOpenPoNumber(OpenPONumber);

					}
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
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
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
		purchaseOrderFunc.verifyAddTableSpecs(OpenPoRcLocators.UnitCost(), OpenPoRcLocators.UnitCostInput, UnitCost);
		System.out.println("Unitcost");
		purchaseOrderFunc.verifyAddTableSpecs(OpenPoRcLocators.DiscPercentage(), OpenPoRcLocators.DiscPercentageInput,
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
				commonFunc.hardWait(3000);
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
		webDB.moveToElement(OpenPoRcLocators.HsnCodeTable(), ElementType.Xpath);
		flag = webDB.waitForElement(OpenPoRcLocators.HsnCodeTable(), ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "HSN Code In Table Is Displayed");
			webDB.clickAnElement(OpenPoRcLocators.HsnCodeTable(), ElementType.Xpath);
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
//				commonFunc.hardWait(2000);
				webDB.clickAnElement(OpenPoRcLocators.OpenPoNo, ElementType.Xpath);
				webDB.clearText(OpenPoRcLocators.EffTo, ElementType.Xpath);
				commonFunc.hardWait(1500);
				webDB.clickAnElement(OpenPoRcLocators.OpenPoNo, ElementType.Xpath);
				commonFunc.hardWait(1000);
				webDB.doubleClickAnElement(OpenPoRcLocators.EffTo, ElementType.Xpath);
				webDB.sendTextToAnElement(OpenPoRcLocators.EffTo, commonFunc.getDataFromTestDataSheet("EffToDate"),
						ElementType.Xpath);
				logs.logging("INFO",
						"Send Text Effective To Date Is: " + commonFunc.getDataFromTestDataSheet("EffToDate"));
				String EffToValue = webDB.getDomPropertyFromElement(OpenPoRcLocators.EffTo, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
				if (EffToValue.equals(commonFunc.getDataFromTestDataSheet("EffToDate"))) {
					logs.logging("INFO", "Verified Effective Date Successfully");
					webDB.clickAnElement(OpenPoRcLocators.OpenPoNo, ElementType.Xpath);
				} else {
					flag = false;
				}
			} else {
				flag = false;
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
		verifyAddTableSpecs(PurchaseOrderPageLocators.OrderedQuantity(), PurchaseOrderPageLocators.OrderedQuantityInput,
				quantity);
		webDB.pressEnterKey();
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseOrderPageLocators.UnitCostTable, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			String Text = webDB.getTextFromElement(PurchaseOrderPageLocators.UnitCostTable, ElementType.Xpath);
			logs.logging("INFO", "Get Text Value Unit Cost Is: " + Text);
			flag = Text.equals(UnitCost);
			if (flag) {
				purchaseOrderFunc.verifyTickIconcreateItem();
				purchaseOrderFunc.navigateToLineDetailPage(VariableHandler.getItemName());
				webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
				verifyTradeDiscountPercentage(DiscValue);
//				purchaseOrderFunc.verifyNavigateLineDetailTwo();
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
		verifyDiscountTabOpening();
		flag = webDB.waitForElement(PurchaseOrderPageLocators.TradePercentageValue, ElementType.Xpath);
		if (flag) {
			String DiscValue = webDB.getTextFromElement(PurchaseOrderPageLocators.TradePercentageValue,
					ElementType.Xpath);
			purchaseOrderFunc.verifyAssertionBothValues(DiscValue, DiscountValue);
		}
	}

	/**
	 * This Method Is Used To Verify Open PO Documents Validation
	 * 
	 * @author Deepna-GWL
	 * @param AmendValue
	 */
	public void verifyOpenPODocumentsValidation(String AmendValue) {
		verifyExpandRateContractDocument();
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
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
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
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
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
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
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
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
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
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
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
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("GwlItem_Service"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
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
			webDB.doubleClickAnElement(OpenPoRcLocators.LineCancelButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.OkayButton, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.OkayButton, ElementType.Xpath);
			}
//			webDB.pressEnterKey();
//			webDB.switchToAlertAccept(5);
//			commonFunc.hardWait(7000);
//			webDB.switchToDefaultContent();
//			commonFunc.checkRefreshCellsDisappear();
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
//		webDB.moveToElement(OpenPoRcLocators.HeaderCancelButton, ElementType.Xpath);
		flag = webDB.waitForElement(OpenPoRcLocators.HeaderCancelButton, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(OpenPoRcLocators.HeaderCancelButton, ElementType.Xpath);
//			webDB.switchToAlertAccept();
//			commonFunc.hardWait(3000);
			flag = webDB.waitForElement(CommonUiLocators.OkayButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CommonUiLocators.OkayButton, ElementType.Xpath);
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
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
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
//		commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To Verify Filter Search OpenPO
	 * 
	 * @author Deepna-GWL
	 * @param LovButton
	 * @param Value
	 * @param i
	 */
	public void verifyFilterSearchOpenPO(String LovButton, String Value, String i) {
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
	public void verifyTableValue(String Header, String Value) {
		List<WebElement> elements = webDB.getDriver().findElements(By.xpath(OpenPoRcLocators.FindTableData(Header)));
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
			commonFunc.expandFindTable();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Filter Open PO Table Data
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyFilterOpenPOTableData() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyFilterSearchOpenPO(OpenPoRcLocators.FindOpenPoNoButton,
				commonFunc.getDataFromTestDataSheet("SampleOpenPONumber"), "OpenPO");

		verifyFilterSearchOpenPO(OpenPoRcLocators.FindLocationButton, commonFunc.getDataFromTestDataSheet("Loc_100"),
				"Loc. ID ");
		verifyFilterSearchOpenPO(OpenPoRcLocators.FindUnitButton, commonFunc.getDataFromTestDataSheet("Unit_100"),
				"Unit");
		verifyFilterSearchOpenPO(OpenPoRcLocators.FindSupplierButton,
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"), " Supplier ID");

//		commonFunc.logoutUser();
	}

	/**
	 * Amend - Standard - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAmendStandardDomesticWithStockAndSupplierSimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddItemInAmendOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("SO_Qty"),
				commonFunc.getDataFromTestDataSheet("SixValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));

			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
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
			commonFunc.expandFindTable();
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
			flag = webDB.waitForElement(OpenPoRcLocators.AmendItemTable(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Button IS Clicked");
				webDB.clickAnElement(OpenPoRcLocators.AmendItemTable(), ElementType.Xpath);
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
		purchaseOrderFunc.verifyAddTableSpecs(OpenPoRcLocators.AmendNewUnitCostTable(),
				OpenPoRcLocators.AmendNewUnitCostInput, UnitCost);
		purchaseOrderFunc.verifyAddTableSpecs(OpenPoRcLocators.AmendDiscTable(), OpenPoRcLocators.AmendDiscInput,
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
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddItemInAmendOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("SO_Qty"),
				commonFunc.getDataFromTestDataSheet("SixValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
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
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddItemInAmendOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("SO_Qty"),
				commonFunc.getDataFromTestDataSheet("SixValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
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
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddItemInAmendOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("SO_Qty"),
				commonFunc.getDataFromTestDataSheet("SixValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
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
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddItemInAmendOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("SO_Qty"),
				commonFunc.getDataFromTestDataSheet("SixValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
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
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddItemInAmendOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("SO_Qty"),
				commonFunc.getDataFromTestDataSheet("SixValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
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
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("GwlItem_Service"));
//		VariableHandler.setItemName("ROAU-SIM-577496");
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("GwlItem_Service"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddItemInAmendOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("SO_Qty"),
				commonFunc.getDataFromTestDataSheet("SixValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
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

	/**
	 * This Method IS Used To Verify Add Open PO Line Item For Modify
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAddPoLineItemForModify() {
		flag = webDB.waitForElement(OpenPoRcLocators.AmendFetchOpenPOLine, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(OpenPoRcLocators.AmendFetchOpenPOLine, ElementType.Xpath);
			flag = webDB.waitForElement(OpenPoRcLocators.AmendLineDetailsHeader, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(OpenPoRcLocators.AmendLineDetailsIframe, ElementType.Xpath);
				webDB.moveToElement(OpenPoRcLocators.AmendInputCheckBox, ElementType.Xpath);
				flag = webDB.waitForElement(OpenPoRcLocators.AmendInputCheckBox, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "CheckBox Is Displayed");
					commonFunc.hardWait(3000);
					webDB.clickAnElement(OpenPoRcLocators.AmendInputCheckBox, ElementType.Xpath);
					flag = webDB.waitForElement(OpenPoRcLocators.AmendLineOkButton, ElementType.Xpath);
					if (flag) {
						commonFunc.hardWait(3000);
						logs.logging("INFO", "CheckBox IS Clicked");
						webDB.clickAnElement(OpenPoRcLocators.AmendLineOkButton, ElementType.Xpath);
						webDB.switchToDefaultContent();
						flag = webDB.waitForElement(OpenPoRcLocators.AmendType(), ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Added Open Po Lines Successfully");
						}
					}

				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Change Amend Type
	 * 
	 * @author Deepan-GWL
	 * @param AmendType
	 */
	public void verifyChangeAmendType(String AmendType) {
		flag = webDB.waitForElement(OpenPoRcLocators.AmendType(), ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Amend Type Is Displayed");
			webDB.doubleClickAnElement(OpenPoRcLocators.AmendType(), ElementType.Xpath);
			flag = webDB.waitForElement(OpenPoRcLocators.AmendTypeSelect, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByText(OpenPoRcLocators.AmendTypeSelect, AmendType, ElementType.Xpath);
				logs.logging("INFO", "Amend Type Select Is Selected As DOI");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Amend - Standard - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDOIAmendStandardDomesticWithStockAndSupplierSimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendType(commonFunc.getDataFromTestDataSheet("DeletionOfItem"));
		verifyAmendItemTickButton();
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyDOIUnitCostValidation(commonFunc.getDataFromTestDataSheet("SixValue"));
		assertTrue(flag);
	}

	/**
	 * Amend - Standard - Domestic Supplier with Amend - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDOIAmendStandardDomesticWithStockAndSupplierDissimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendType(commonFunc.getDataFromTestDataSheet("DeletionOfItem"));
		verifyAmendItemTickButton();
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyDOIUnitCostValidation(commonFunc.getDataFromTestDataSheet("SixValue"));
		assertTrue(flag);
	}

	/**
	 * Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDOIAmendStandardImportWithStockAndSupplierSimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendType(commonFunc.getDataFromTestDataSheet("DeletionOfItem"));
		verifyAmendItemTickButton();
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.hardWait(3000);
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GoogleSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyDOIUnitCostValidation(commonFunc.getDataFromTestDataSheet("SixValue"));
		assertTrue(flag);
	}

	/**
	 * Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDOIAmendStandardImportWithStockAndSupplierDissimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendType(commonFunc.getDataFromTestDataSheet("DeletionOfItem"));
		verifyAmendItemTickButton();
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GoogleSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyDOIUnitCostValidation(commonFunc.getDataFromTestDataSheet("SixValue"));
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Deletion Unit Cost Validation
	 * 
	 * @author Deepan-GWL
	 * @param quantity
	 * @param UnitCost
	 * @param DiscValue
	 * @param AmendValue
	 */
	public void verifyDOIUnitCostValidation(String quantity) {
		commonFunc.checkRefreshCellsDisappear();
		verifyAddTableSpecs(PurchaseOrderPageLocators.OrderedQuantity(), PurchaseOrderPageLocators.OrderedQuantityInput,
				quantity);
		webDB.pressEnterKey();
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseOrderPageLocators.ContractPrice, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Verified Contract Price Not Found Popup Displayed");
			webDB.clickAnElement(PurchaseOrderPageLocators.ContractPriceOkButton, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDOIAmendStockTransferDomesticWithStockAndSupplierSimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendType(commonFunc.getDataFromTestDataSheet("DeletionOfItem"));
		verifyAmendItemTickButton();
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc16"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplierOpenPO"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyDOIUnitCostValidation(commonFunc.getDataFromTestDataSheet("SixValue"));
		assertTrue(flag);
	}

	/**
	 * Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDOIAmendStockTransferDomesticWithStockAndSupplierDissimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendType(commonFunc.getDataFromTestDataSheet("DeletionOfItem"));
		verifyAmendItemTickButton();
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc16"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplierOpenPO"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyDOIUnitCostValidation(commonFunc.getDataFromTestDataSheet("SixValue"));
		assertTrue(flag);
	}

	/**
	 * Amend - Service - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDOIAmendServiceDomesticWithStockAndSupplierSimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("GwlItem_Service"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendType(commonFunc.getDataFromTestDataSheet("DeletionOfItem"));
		verifyAmendItemTickButton();
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0001"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyDOIUnitCostValidation(commonFunc.getDataFromTestDataSheet("SixValue"));
		assertTrue(flag);
	}

	/**
	 * Amend - Standard - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPCAmendStandardDomesticWithStockAndSupplierSimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendType(commonFunc.getDataFromTestDataSheet("PriceChange"));
		purchaseOrderFunc.verifyAddTableSpecs(OpenPoRcLocators.AmendNewUnitCostTable(),
				OpenPoRcLocators.AmendNewUnitCostInput, commonFunc.getDataFromTestDataSheet("NewUnitPrice"));
		purchaseOrderFunc.verifyAddTableSpecs(OpenPoRcLocators.AmendDiscTable(), OpenPoRcLocators.AmendDiscInput,
				commonFunc.getDataFromTestDataSheet("SixValue"));
		verifyAmendItemTickButton();
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyUnitCostValidation(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("NewUnitPrice"), commonFunc.getDataFromTestDataSheet("SixValue"),
				commonFunc.getDataFromTestDataSheet("SO_Cash_Disc"));
		assertTrue(flag);
	}

	/**
	 * Amend - Standard - Domestic Supplier with Amend - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPCAmendStandardDomesticWithStockAndSupplierDissimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendType(commonFunc.getDataFromTestDataSheet("PriceChange"));
		purchaseOrderFunc.verifyAddTableSpecs(OpenPoRcLocators.AmendNewUnitCostTable(),
				OpenPoRcLocators.AmendNewUnitCostInput, commonFunc.getDataFromTestDataSheet("NewUnitPrice"));
		purchaseOrderFunc.verifyAddTableSpecs(OpenPoRcLocators.AmendDiscTable(), OpenPoRcLocators.AmendDiscInput,
				commonFunc.getDataFromTestDataSheet("SixValue"));
		verifyAmendItemTickButton();
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyUnitCostValidation(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("NewUnitPrice"), commonFunc.getDataFromTestDataSheet("SixValue"),
				commonFunc.getDataFromTestDataSheet("SO_Cash_Disc"));
		assertTrue(flag);
	}

	/**
	 * Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPCAmendStandardImportWithStockAndSupplierSimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendType(commonFunc.getDataFromTestDataSheet("PriceChange"));
		purchaseOrderFunc.verifyAddTableSpecs(OpenPoRcLocators.AmendNewUnitCostTable(),
				OpenPoRcLocators.AmendNewUnitCostInput, commonFunc.getDataFromTestDataSheet("NewUnitPrice"));
		purchaseOrderFunc.verifyAddTableSpecs(OpenPoRcLocators.AmendDiscTable(), OpenPoRcLocators.AmendDiscInput,
				commonFunc.getDataFromTestDataSheet("SixValue"));
		verifyAmendItemTickButton();
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GoogleSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyUnitCostValidation(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("NewUnitPrice"), commonFunc.getDataFromTestDataSheet("SixValue"),
				commonFunc.getDataFromTestDataSheet("SO_Cash_Disc"));
		assertTrue(flag);
	}

	/**
	 * Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPCAmendStandardImportWithStockAndSupplierDissimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendType(commonFunc.getDataFromTestDataSheet("PriceChange"));
		purchaseOrderFunc.verifyAddTableSpecs(OpenPoRcLocators.AmendNewUnitCostTable(),
				OpenPoRcLocators.AmendNewUnitCostInput, commonFunc.getDataFromTestDataSheet("NewUnitPrice"));
		purchaseOrderFunc.verifyAddTableSpecs(OpenPoRcLocators.AmendDiscTable(), OpenPoRcLocators.AmendDiscInput,
				commonFunc.getDataFromTestDataSheet("SixValue"));
		verifyAmendItemTickButton();
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GoogleSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyUnitCostValidation(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("NewUnitPrice"), commonFunc.getDataFromTestDataSheet("SixValue"),
				commonFunc.getDataFromTestDataSheet("SO_Cash_Disc"));
		assertTrue(flag);
	}

	/**
	 * Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPCAmendStockTransferDomesticWithStockAndSupplierSimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendType(commonFunc.getDataFromTestDataSheet("PriceChange"));
		purchaseOrderFunc.verifyAddTableSpecs(OpenPoRcLocators.AmendNewUnitCostTable(),
				OpenPoRcLocators.AmendNewUnitCostInput, commonFunc.getDataFromTestDataSheet("NewUnitPrice"));
		purchaseOrderFunc.verifyAddTableSpecs(OpenPoRcLocators.AmendDiscTable(), OpenPoRcLocators.AmendDiscInput,
				commonFunc.getDataFromTestDataSheet("SixValue"));
		verifyAmendItemTickButton();
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc16"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplierOpenPO"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyUnitCostValidation(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("NewUnitPrice"), commonFunc.getDataFromTestDataSheet("SixValue"),
				commonFunc.getDataFromTestDataSheet("SO_Cash_Disc"));
		assertTrue(flag);
	}

	/**
	 * Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPCAmendStockTransferDomesticWithStockAndSupplierDissimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendType(commonFunc.getDataFromTestDataSheet("PriceChange"));
		purchaseOrderFunc.verifyAddTableSpecs(OpenPoRcLocators.AmendNewUnitCostTable(),
				OpenPoRcLocators.AmendNewUnitCostInput, commonFunc.getDataFromTestDataSheet("NewUnitPrice"));
		purchaseOrderFunc.verifyAddTableSpecs(OpenPoRcLocators.AmendDiscTable(), OpenPoRcLocators.AmendDiscInput,
				commonFunc.getDataFromTestDataSheet("SixValue"));
		verifyAmendItemTickButton();
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc16"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplierOpenPO"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyUnitCostValidation(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("NewUnitPrice"), commonFunc.getDataFromTestDataSheet("SixValue"),
				commonFunc.getDataFromTestDataSheet("SO_Cash_Disc"));
		assertTrue(flag);
	}

	/**
	 * Amend - Service - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPCAmendServiceDomesticWithStockAndSupplierSimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("GwlItem_Service"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendType(commonFunc.getDataFromTestDataSheet("PriceChange"));
		purchaseOrderFunc.verifyAddTableSpecs(OpenPoRcLocators.AmendNewUnitCostTable(),
				OpenPoRcLocators.AmendNewUnitCostInput, commonFunc.getDataFromTestDataSheet("NewUnitPrice"));
		purchaseOrderFunc.verifyAddTableSpecs(OpenPoRcLocators.AmendDiscTable(), OpenPoRcLocators.AmendDiscInput,
				commonFunc.getDataFromTestDataSheet("SixValue"));
		verifyAmendItemTickButton();
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0001"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyUnitCostValidation(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("NewUnitPrice"), commonFunc.getDataFromTestDataSheet("SixValue"),
				commonFunc.getDataFromTestDataSheet("SO_Cash_Disc"));
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Add Terms And Conditions
	 * 
	 * @author Deepan-GWL
	 * @param Value
	 */
	public void verifyAddTermsAndConiditions(String Value) {
		flag = webDB.waitForElement(OpenPoRcLocators.AddTCButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Add Icon Is Displayed");
			webDB.clickAnElement(OpenPoRcLocators.AddTCButton, ElementType.Xpath);
			flag = webDB.waitForElement(OpenPoRcLocators.TCAttribute, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Table Attribute Is Displayed");
				webDB.doubleClickAnElement(OpenPoRcLocators.TCAttribute, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(OpenPoRcLocators.AttributeLovButton, Value);
				flag = webDB.waitForElement(OpenPoRcLocators.TermConditionTickIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Tick Icon Is Displayed");
					webDB.clickAnElement(OpenPoRcLocators.TermConditionTickIcon, ElementType.Xpath);
					if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
					commonFunc.checkSuccessToastMessageDisappear();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Navigate To Terms And Conditions Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToTCTab() {
		flag = webDB.waitForElement(OpenPoRcLocators.TermsAndCondition, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenPoRcLocators.TermsAndCondition, ElementType.Xpath);
			flag = webDB.waitForElement(OpenPoRcLocators.TermsAndCondition, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated To Terms And Conditions Tab");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Add Terms And Conditions Value
	 * 
	 * @author Deepan-GWL
	 * @param Value
	 */
	public void verifyAddTermsAndConditionsValue(String Value) {
		flag = webDB.waitForElement(OpenPoRcLocators.ValueAddButton, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			logs.logging("INFO", "Add Icon Is Displayed");
			webDB.javaScriptClickAnElement(OpenPoRcLocators.ValueAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(OpenPoRcLocators.ValueAttributeTable, ElementType.Xpath);
			if (flag) {
				webDB.doubleClickAnElement(OpenPoRcLocators.ValueAttributeTable, ElementType.Xpath);
				flag = webDB.waitForElement(OpenPoRcLocators.AttributeValueInput, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(OpenPoRcLocators.AttributeValueInput, Value, ElementType.Xpath);
					logs.logging("INFO", "Send Text Attribute Value Is: " + Value);
					flag = webDB.waitForElement(OpenPoRcLocators.ValueTickButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Tick Icon Is Displayed");
						webDB.clickAnElement(OpenPoRcLocators.ValueTickButton, ElementType.Xpath);
						if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
						}
						commonFunc.checkSuccessToastMessageDisappear();
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Change Amendment Term Change
	 * 
	 * @author Deepan-GWL
	 * @param Value
	 */
	public void verifyChangeAmendmentTermChange(String Value) {
		flag = webDB.waitForElement(OpenPoRcLocators.AmendTermsAndCondition, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			logs.logging("INFO", "Amend Term And Condition Button Is Displayed");
			webDB.clickAnElement(OpenPoRcLocators.AmendTermsAndCondition, ElementType.Xpath);
			flag = webDB.waitForElement(OpenPoRcLocators.AmendValueAddButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Button Is Displayed");
				webDB.javaScriptClickAnElement(OpenPoRcLocators.AmendValueAddButton, ElementType.Xpath);
				flag = webDB.waitForElement(OpenPoRcLocators.ValueAttributeTable, ElementType.Xpath);
				if (flag) {
					webDB.doubleClickAnElement(OpenPoRcLocators.ValueAttributeTable, ElementType.Xpath);
					flag = webDB.waitForElement(OpenPoRcLocators.AmendAttributeValueInput, ElementType.Xpath);
					if (flag) {
						webDB.sendTextToAnElement(OpenPoRcLocators.AmendAttributeValueInput, Value, ElementType.Xpath);
						logs.logging("INFO", "Send Text Attribute Value Is: " + Value);
						flag = webDB.waitForElement(OpenPoRcLocators.AmendValueAttributeTickButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Tick Icon Is Displayed");
							webDB.clickAnElement(OpenPoRcLocators.AmendValueAttributeTickButton, ElementType.Xpath);
							if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
								webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
										ElementType.Xpath);
							}
						}
					}
				}
			}

		}

		assertTrue(flag);
	}

	/**
	 * Amend - Standard - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyTermChangeAmendStandardDomesticWithStockAndSupplierSimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("StandardPO"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		verifyAddItemInOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyAddAggrDateAndAggrNumber();
		navigateToTCTab();
		verifyAddTermsAndConiditions(commonFunc.getDataFromTestDataSheet("TermConditionOpenPO"));
		verifyAddTermsAndConditionsValue(commonFunc.getDataFromTestDataSheet("Required"));
		verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendmentTermChange(commonFunc.getDataFromTestDataSheet("NotRequired"));
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		assertTrue(flag);
	}

	/**
	 * Amend - Standard - Domestic Supplier with Amend - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyTermChangeAmendStandardDomesticWithStockAndSupplierDissimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("StandardPO"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		verifyAddItemInOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyAddAggrDateAndAggrNumber();
		navigateToTCTab();
		verifyAddTermsAndConiditions(commonFunc.getDataFromTestDataSheet("TermConditionOpenPO"));
		verifyAddTermsAndConditionsValue(commonFunc.getDataFromTestDataSheet("Required"));
		verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendmentTermChange(commonFunc.getDataFromTestDataSheet("NotRequired"));
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));

		assertTrue(flag);
	}

	/**
	 * Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyTermChangeAmendStandardImportWithStockAndSupplierSimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("StandardPO"),
				commonFunc.getDataFromTestDataSheet("GoogleSupplier"));
		verifyAddItemInOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyAddAggrDateAndAggrNumber();
		navigateToTCTab();
		verifyAddTermsAndConiditions(commonFunc.getDataFromTestDataSheet("TermConditionOpenPO"));
		verifyAddTermsAndConditionsValue(commonFunc.getDataFromTestDataSheet("Required"));
		verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendmentTermChange(commonFunc.getDataFromTestDataSheet("NotRequired"));
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));

		assertTrue(flag);
	}

	/**
	 * Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyTermChangeAmendStandardImportWithStockAndSupplierDissimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("StandardPO"),
				commonFunc.getDataFromTestDataSheet("GoogleSupplier"));
		verifyAddItemInOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyAddAggrDateAndAggrNumber();
		navigateToTCTab();
		verifyAddTermsAndConiditions(commonFunc.getDataFromTestDataSheet("TermConditionOpenPO"));
		verifyAddTermsAndConditionsValue(commonFunc.getDataFromTestDataSheet("Required"));
		verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendmentTermChange(commonFunc.getDataFromTestDataSheet("NotRequired"));
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));

		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Eff To In Amendment
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyEffToInAmendment() {
		webDB.sendTextToAnElement(OpenPoRcLocators.AmendEffTo, commonFunc.getDataFromTestDataSheet("AmendEffTo"),
				ElementType.Xpath);
		logs.logging("INFO", "Send Text Effective To Date Is: " + commonFunc.getDataFromTestDataSheet("AmendEffTo"));
		String EffToValue = webDB.getDomPropertyFromElement(OpenPoRcLocators.AmendEffTo, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		if (EffToValue.equals(commonFunc.getDataFromTestDataSheet("AmendEffTo"))) {
			logs.logging("INFO", "Verified Effective Date Successfully");
			webDB.clickAnElement(OpenPoRcLocators.AmendOPONo, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify OpenPo Approval Status
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyEnterOpenPoNumber(String DocNo) {
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
				flag = webDB.waitForElement(OpenPoRcLocators.OpenPONumber(DocNo), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(OpenPoRcLocators.OpenPONumber(DocNo), ElementType.Xpath);
					flag = webDB.waitForElement(OpenPoRcLocators.OpenPoTC, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyTermChangeAmendStockTransferDomesticWithStockAndSupplierSimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("Loc16"),
				commonFunc.getDataFromTestDataSheet("StockTransferPO"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplierOpenPO"));
		verifyAddItemInOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyAddAggrDateAndAggrNumber();
		navigateToTCTab();
		verifyAddTermsAndConiditions(commonFunc.getDataFromTestDataSheet("TermConditionOpenPO"));
		verifyAddTermsAndConditionsValue(commonFunc.getDataFromTestDataSheet("Required"));
		verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendmentTermChange(commonFunc.getDataFromTestDataSheet("NotRequired"));
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));

		assertTrue(flag);
	}

	/**
	 * Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyTermChangeAmendStockTransferDomesticWithStockAndSupplierDissimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("Loc16"),
				commonFunc.getDataFromTestDataSheet("StockTransferPO"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplierOpenPO"));
		verifyAddItemInOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyAddAggrDateAndAggrNumber();
		navigateToTCTab();
		verifyAddTermsAndConiditions(commonFunc.getDataFromTestDataSheet("TermConditionOpenPO"));
		verifyAddTermsAndConditionsValue(commonFunc.getDataFromTestDataSheet("Required"));
		verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendmentTermChange(commonFunc.getDataFromTestDataSheet("NotRequired"));
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));

		assertTrue(flag);
	}

	/**
	 * Amend - Service - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyTermChangeAmendServiceDomesticWithStockAndSupplierSimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("GwlItem_Service"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("L0001"),
				commonFunc.getDataFromTestDataSheet("StandardService"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		verifyAddItemInOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyAddAggrDateAndAggrNumber();
		navigateToTCTab();
		verifyAddTermsAndConiditions(commonFunc.getDataFromTestDataSheet("TermConditionOpenPO"));
		verifyAddTermsAndConditionsValue(commonFunc.getDataFromTestDataSheet("Required"));
		verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendmentTermChange(commonFunc.getDataFromTestDataSheet("NotRequired"));
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));

		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Cancel Open Po Amendment Line And Documents
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyOpenPOAmendCancelDocumentAndLineWiseCancel() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendType(commonFunc.getDataFromTestDataSheet("PriceChange"));
		verifyOpenPOAmendmentCancelLine();
		verifyOpenPOHeaderCancelDocuments();

		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("CancelStatus"));
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify OpenPO Cancel Line
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void verifyOpenPOAmendmentCancelLine() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(OpenPoRcLocators.LineCancelButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(OpenPoRcLocators.AmendItemTable(), ElementType.Xpath);
			webDB.moveToElement(OpenPoRcLocators.LineCancelButton, ElementType.Xpath);
			webDB.clickAnElement(OpenPoRcLocators.LineCancelButton, ElementType.Xpath);
			flag = webDB.waitForElement(OpenPoRcLocators.OkButton, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(OpenPoRcLocators.OkButton, ElementType.Xpath);
				webDB.clickAnElement(OpenPoRcLocators.OkButton, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Toast Message Displayed, Line Deleted Successfully");
					if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
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
	public void verifyOpenPOHeaderCancelDocuments() {
		webDB.moveToElement(OpenPoRcLocators.AmendHeaderCancel, ElementType.Xpath);
		flag = webDB.waitForElement(OpenPoRcLocators.AmendHeaderCancel, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenPoRcLocators.AmendHeaderCancel, ElementType.Xpath);
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
	 * This Method Is Used To Verify Eff To In Amendment
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyOtherChangeEffFromEffToInAmendment() {
		webDB.clearText(OpenPoRcLocators.AmendEffTo, ElementType.Xpath);
		webDB.sendTextToAnElement(OpenPoRcLocators.AmendEffTo, commonFunc.getDataFromTestDataSheet("AmendEffTo"),
				ElementType.Xpath);
		logs.logging("INFO", "Send Text Effective To Date Is: " + commonFunc.getDataFromTestDataSheet("AmendEffTo"));
		String EffToValue = webDB.getDomPropertyFromElement(OpenPoRcLocators.AmendEffTo, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		if (EffToValue.equals(commonFunc.getDataFromTestDataSheet("AmendEffTo"))) {
			logs.logging("INFO", "Verified Effective Date Successfully");
			webDB.clickAnElement(OpenPoRcLocators.AmendOPONo, ElementType.Xpath);
			LocalDate today = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			webDB.clearText(OpenPoRcLocators.AmendEffFrom, ElementType.Xpath);
			commonFunc.hardWait(2000);
			webDB.sendTextToAnElement(OpenPoRcLocators.AmendEffFrom, today.format(formatter), ElementType.Xpath);
			logs.logging("INFO", "Send Text Effective From Date Is: " + today.format(formatter));
			String EffFromValue = webDB.getDomPropertyFromElement(OpenPoRcLocators.AmendEffFrom, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			if (EffFromValue.equals(today.format(formatter))) {
				logs.logging("INFO", "Verified Effective Date Successfully");
				webDB.clickAnElement(OpenPoRcLocators.AmendOPONo, ElementType.Xpath);
				webDB.clearText(OpenPoRcLocators.AmendAggrNumber, ElementType.Xpath);
				webDB.sendTextToAnElement(OpenPoRcLocators.AmendAggrNumber, VariableHandler.getOpenPOAmendNumber(),
						ElementType.Xpath);
				logs.logging("INFO", "Send Text Aggregate Number Is: " + VariableHandler.getOpenPOAmendNumber());
				String AggregateNumber = webDB.getDomPropertyFromElement(OpenPoRcLocators.AmendAggrNumber,
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
				if (AggregateNumber.equals(VariableHandler.getOpenPOAmendNumber())) {
					logs.logging("INFO", "Verified Aggregate Number Successfully");
					webDB.clickAnElement(OpenPoRcLocators.AmendOPONo, ElementType.Xpath);
					webDB.clickAnElement(OpenPoRcLocators.AmendAggrDate, ElementType.Xpath);
					webDB.clickAnElement(OpenPoRcLocators.AggrCalendarDate, ElementType.Xpath);
					webDB.clickAnElement(OpenPoRcLocators.AmendOPONo, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Amend - Standard - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyOtherChangeAmendStandardDomesticWithStockAndSupplierSimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendType(commonFunc.getDataFromTestDataSheet("PriceChange"));
		verifyAmendItemTickButton();
		verifyOtherChangeEffFromEffToInAmendment();
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		assertTrue(flag);
	}

	/**
	 * Amend - Standard - Domestic Supplier with Amend - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyOtherChangeAmendStandardDomesticWithStockAndSupplierDissimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendType(commonFunc.getDataFromTestDataSheet("PriceChange"));
		verifyAmendItemTickButton();
		verifyOtherChangeEffFromEffToInAmendment();
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		assertTrue(flag);
	}

	/**
	 * Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyOtherChangeAmendStandardImportWithStockAndSupplierSimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendType(commonFunc.getDataFromTestDataSheet("PriceChange"));
		verifyAmendItemTickButton();
		verifyOtherChangeEffFromEffToInAmendment();
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		assertTrue(flag);
	}

	/**
	 * Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyOtherChangeAmendStandardImportWithStockAndSupplierDissimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendType(commonFunc.getDataFromTestDataSheet("PriceChange"));
		verifyAmendItemTickButton();
		verifyOtherChangeEffFromEffToInAmendment();
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		assertTrue(flag);
	}

	/**
	 * Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyOtherChangeAmendStockTransferDomesticWithStockAndSupplierSimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendType(commonFunc.getDataFromTestDataSheet("PriceChange"));
		verifyAmendItemTickButton();
		verifyOtherChangeEffFromEffToInAmendment();
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		assertTrue(flag);
	}

	/**
	 * Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyOtherChangeAmendStockTransferDomesticWithStockAndSupplierDissimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendType(commonFunc.getDataFromTestDataSheet("PriceChange"));
		verifyAmendItemTickButton();
		verifyOtherChangeEffFromEffToInAmendment();
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		assertTrue(flag);
	}

	/**
	 * Amend - Service - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyOtherChangeAmendServiceDomesticWithStockAndSupplierSimilarUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("GwlItem_Service"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddPoLineItemForModify();
		verifyChangeAmendType(commonFunc.getDataFromTestDataSheet("PriceChange"));
		verifyAmendItemTickButton();
		verifyOtherChangeEffFromEffToInAmendment();
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPOAmendNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Filter Open PO Table Data
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyFilterOpenPOAmendmentTableData() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		verifyAmendFilterSearchOpenPO(OpenPoRcLocators.AmendItemButton,
				commonFunc.getDataFromTestDataSheet("SampleItemName"), "Item",
				commonFunc.getDataFromTestDataSheet("LineSearchType"));
//		verifyAmendFilterSearchOpenPO(OpenPoRcLocators.AmendSelectStatus,
//				commonFunc.getDataFromTestDataSheet("ApproveStatus"), 13,
//				commonFunc.getDataFromTestDataSheet("HeaderSearchType"), true);
//		verifyAmendFilterSearchOpenPO(OpenPoRcLocators.AmendOpenPONo,
//				commonFunc.getDataFromTestDataSheet("OpenPONumber"), 6,
//				commonFunc.getDataFromTestDataSheet("HeaderSearchType"));
		verifyAmendFilterSearchOpenPO(OpenPoRcLocators.AmendOOAPNo,
				commonFunc.getDataFromTestDataSheet("SampleOpenPOANumber"), "OpenPOAmend",
				commonFunc.getDataFromTestDataSheet("HeaderSearchType"));
		verifyAmendFilterSearchOpenPO(OpenPoRcLocators.AmendLocationId, commonFunc.getDataFromTestDataSheet("Loc_100"),
				"LocId", commonFunc.getDataFromTestDataSheet("HeaderSearchType"));
		verifyAmendFilterSearchOpenPO(OpenPoRcLocators.AmendUnitId, commonFunc.getDataFromTestDataSheet("Unit_100"),
				"Unit", commonFunc.getDataFromTestDataSheet("HeaderSearchType"));

//		commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To Click Search Button
	 * 
	 * @author Deepan-GWL
	 */
	public void clickAmendSearchButton() {
		flag = webDB.waitForElement(OpenPoRcLocators.HeaderCheckBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header CheckBox Is Displayed");
			webDB.clickAnElement(OpenPoRcLocators.HeaderCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(OpenPoRcLocators.OpenPoSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(OpenPoRcLocators.OpenPoSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Search Button Is Clicked");

				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(OpenPoRcLocators.AmendFindTable, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click Search Button
	 * 
	 * @author Deepan-GWL
	 */
	public void clickAmendLineSearchButton() {
		flag = webDB.waitForElement(OpenPoRcLocators.LineCheckBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Line CheckBox Is Displayed");
			webDB.clickAnElement(OpenPoRcLocators.LineCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(OpenPoRcLocators.OpenPoSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(OpenPoRcLocators.OpenPoSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Search Button Is Clicked");

				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(OpenPoRcLocators.AmendLineFindTable, ElementType.Xpath);
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
	public void verifyAmendTableValue(String i, String Value) {
		List<WebElement> elements = webDB.getDriver().findElements(By.xpath(OpenPoRcLocators.AmendFindTableData(i)));
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
	 * This Method Is Used To Verify Table Value
	 * 
	 * @author Deepan-GWL
	 * @param i
	 * @param Value
	 */
	public void verifyAmendTableLineValue(String i, String Value) {
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(OpenPoRcLocators.AmendFindLineTableData(i)));
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
	 * This Method Is Used To Verify Filter Search OpenPO Amendment
	 * 
	 * @author Deepan-GWL
	 * @param LovButton
	 * @param Value
	 * @param i
	 */
	public void verifyAmendFilterSearchOpenPO(String LovButton, String Value, String i, String Type,
			boolean... selectflag) {
		webDB.scrollToAnElement(LovButton, ElementType.Xpath);
		if (selectflag != null) {
			commonFunc.searchAndSelectValueInFilters(LovButton, Value);
		} else {
			webDB.selectDropDownByText(LovButton, Value, ElementType.Xpath);
		}
		if (Type.equals(commonFunc.getDataFromTestDataSheet("HeaderSearchType"))) {
			clickAmendSearchButton();
			commonFunc.expandFindTable();
			commonFunc.hardWait(3000);
			verifyAmendTableValue(i, Value);
		} else if (Type.equals(commonFunc.getDataFromTestDataSheet("LineSearchType"))) {
			clickAmendLineSearchButton();
			commonFunc.expandFindTable();
			commonFunc.hardWait(3000);
			verifyAmendTableLineValue(i, Value);
		}
		clearSearchFilter();
	}

	/**
	 * Verify Discount Tab Opening
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDiscountTabOpening() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.DiscountButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.DiscountButton, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.TradePercentageValue, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Discount Expand Button Is Displyaed");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Expand Rate Contract Document
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyExpandRateContractDocument() {
		webDB.moveToElement(PurchaseOrderPageLocators.ContractExpandButton, ElementType.Xpath);
		flag = webDB.waitForClickElement(PurchaseOrderPageLocators.ContractExpandButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.ContractExpandButton, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.ContractId, ElementType.Xpath);
		}
		assertTrue(flag);
	}

}
