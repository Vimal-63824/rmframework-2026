package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL
 * @company Galaxy Weblinks May 20, 2025
 */
import static org.testng.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.financials.PaymentReceiptsLocators;
import commonlocators.supplychainmanagement.ApprovedSupplierPOLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class ApprovedSupplierPOFunctions {
	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	static PricelistPageFunctions pricelistPageFunc = new PricelistPageFunctions();
	static ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();
	static OpenPoRcFunctions openPoRcFunctions = new OpenPoRcFunctions();
	private static CustomerItemsFunctions customerItemFunc = new CustomerItemsFunctions();

	/**
	 * This Method Is Used tO Verify Create Approved Supplier For Item
	 * 
	 * @author Deepan-GWL
	 * @param Unit
	 * @param Doc
	 * @param Supplier
	 * @param Item
	 * @param PriceBasis
	 * @param HsnCode
	 */
	public void verifyCreateApprovedSupplier(String Unit, String Doc, String Supplier, String Item, String PriceBasis,
			String HsnCode, String LeadTime) {
		flag = webDB.waitForElement(CommonUiLocators.AddSupplierButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.AddSupplierButton, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(ApprovedSupplierPOLocators.UnitLovButton, Unit);
			commonFunc.searchAndSelectValueInFilters(ApprovedSupplierPOLocators.DocLovButton, Doc);
			commonFunc.searchAndSelectValueInFilters(ApprovedSupplierPOLocators.SupplierLovButton, Supplier);
			commonFunc.searchAndSelectValueInFilters(ApprovedSupplierPOLocators.ItemLovButton, Item);
			commonFunc.searchAndSelectValueInFilters(ApprovedSupplierPOLocators.HsnCodeButton, HsnCode);
			webDB.selectDropDownByText(ApprovedSupplierPOLocators.PriceBasisSelect, PriceBasis, ElementType.Xpath);
			webDB.sendTextToAnElement(ApprovedSupplierPOLocators.LeadTimeInput, LeadTime, ElementType.Xpath);
			flag = webDB.waitForElement(ApprovedSupplierPOLocators.SaveButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ApprovedSupplierPOLocators.SaveButton, ElementType.Xpath);
				approvedsupplierNumberExtractor();
				commonFunc.checkRefreshCellsDisappear();
				if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				flag = webDB.waitForElement(ApprovedSupplierPOLocators.PostButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(ApprovedSupplierPOLocators.PostButton, ElementType.Xpath);
					flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
					if (flag) {
						logs.logging("INFO", "Post Button Clicked Successfully");
					}

				}
			}

		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Extract Approved Supplier Number
	 * 
	 * @author Deepan-GWL
	 */
	public void approvedsupplierNumberExtractor() {
		flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.ToastMessagePopup, ElementType.Xpath, 60);
		if (flag) {
			String MessagePopup = webDB.getTextFromElement(CommonUiLocators.ToastMessagePopup, ElementType.Xpath);
			Pattern pattern = Pattern.compile("ASP/\\d{5}/\\d{2}-\\d{2}");
			Matcher matcher = pattern.matcher(MessagePopup);
			if (matcher.find()) {
				String match = matcher.group();
				logs.logging("INFO", "Approved Supplier Number Is: " + match);
				VariableHandler.setAppsNumber(match);
			} else {
				System.out.println("No match found.");
			}
		}
	}

	/**
	 * This Methos Is Used To Verify Create Approved Supplier For Created Item
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateApprovedSupplierForCreatedItem() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage();
		System.out.println(commonFunc.getDataFromTestDataSheet("OpenSCOApprovedstatus"));
		pricelistPageFunc.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("ChargesItems"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"TvsUsername");
		commonFunc.navigateToApprovedSupplierPOTab();
		verifyCreateApprovedSupplier(commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("Apps"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UserDefinedGeneral"),
				commonFunc.getDataFromTestDataSheet("HsnService"),
				commonFunc.getDataFromTestDataSheet("LeaveElgblDays"));
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getAppsNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"TvsUsername");
		commonFunc.navigateToApprovedSupplierPOTab();
		verifyApprovedSupplierApprovalStatus(VariableHandler.getAppsNumber());
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"TvsUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyCostBasisValidation(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		commonFunc.logoutUser();
		verifyCreateApprovedSupplierForCreatedItemWithOpenBlanket();
		verifyCreateApprovedSupplierForCreatedItemWithPriceList();
		assertTrue(flag);
	}

	/**
	 * This Methos Is Used To Verify Create Approved Supplier For Created Item
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateApprovedSupplierForCreatedItemWithOpenBlanket() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunc.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("RawMaterial10Item"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"TvsUsername");
		commonFunc.navigateToApprovedSupplierPOTab();
		verifyCreateApprovedSupplier(commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("Apps"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("OpenBlanket"),
				commonFunc.getDataFromTestDataSheet("HSN2022-5%"),
				commonFunc.getDataFromTestDataSheet("LeaveElgblDays"));
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
//			commonFunc.logoutUser();
//			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//					"HodUsername");
//			approvalFunc.navigateToApprovals();
//			approvalFunc.filterDocumentsforApproval(VariableHandler.getAppsNumber());
//			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getAppsNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getAppsNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"TvsUsername");
		commonFunc.navigateToApprovedSupplierPOTab();
		verifyApprovedSupplierApprovalStatus(VariableHandler.getAppsNumber());
		commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Methos Is Used To Verify Create Approved Supplier For Created Item
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateApprovedSupplierForCreatedItemWithPriceList() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunc.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("RawMaterial10Item"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"TvsUsername");
		commonFunc.navigateToApprovedSupplierPOTab();
		verifyCreateApprovedSupplier(commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("Apps"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("PriceListApprovedSupplier"),
				commonFunc.getDataFromTestDataSheet("HSN2022-5%"),
				commonFunc.getDataFromTestDataSheet("LeaveElgblDays"));
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
//			commonFunc.logoutUser();
//			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//					"HodUsername");
//			approvalFunc.navigateToApprovals();
//			approvalFunc.filterDocumentsforApproval(VariableHandler.getAppsNumber());
//			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getAppsNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getAppsNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"TvsUsername");
		commonFunc.navigateToApprovedSupplierPOTab();
		verifyApprovedSupplierApprovalStatus(VariableHandler.getAppsNumber());
		commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Cost Basis Validation
	 * 
	 * @author Deepan-GWL
	 * @param quantity
	 * @param UnitCost
	 * @param DiscValue
	 * @param AmendValue
	 */
	public void verifyCostBasisValidation(String quantity, String UnitCost) {
		commonFunc.checkRefreshCellsDisappear();
		openPoRcFunctions.verifyAddTableSpecs(PurchaseOrderPageLocators.OrderedQuantity(),
				PurchaseOrderPageLocators.OrderedQuantityInput, quantity);
		openPoRcFunctions.verifyAddTableSpecs(PurchaseOrderPageLocators.UnitCost(),
				PurchaseOrderPageLocators.UnitCostInput, UnitCost);
		purchaseOrderFunc.verifyTickIconcreateItem();
		purchaseOrderFunc.navigateToLineDetailPage(VariableHandler.getItemName());
		webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
		String CostBasis = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.CostBasis, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		flag = CostBasis.equals(commonFunc.getDataFromTestDataSheet("CostBasis"));
		if (flag) {
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(PurchaseOrderPageLocators.LineDetailsCloseIcons, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseOrderPageLocators.LineDetailsCloseIcons, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
				logs.logging("INFO", "Verified Navigate To Purchase Order Page");
			}
		}

		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Approved Supplier Approval Status
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyApprovedSupplierApprovalStatus(String DocNo) {
		commonFunc.hardWait(2000);
		commonFunc.searchAndSelectValueInFilters(ApprovedSupplierPOLocators.DocNumberLovButton, DocNo);
		flag = webDB.waitForElement(ApprovedSupplierPOLocators.FindButton, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(2000);
			webDB.clickAnElement(ApprovedSupplierPOLocators.FindButton, ElementType.Xpath);
			logs.logging("INFO", "Search Button Is Clicked");
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(ApprovedSupplierPOLocators.ApprovedStatus(DocNo), ElementType.Xpath);
			if (flag) {
				String Status = webDB.getTextFromElement(ApprovedSupplierPOLocators.ApprovedStatus(DocNo),
						ElementType.Xpath);
				flag = Status.contains(commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				if (flag) {
					logs.logging("INFO", "Expected Status: " + commonFunc.getDataFromTestDataSheet("ApproveStatus"));
					logs.logging("INFO", "Verified Status ,Displayed Status: " + Status);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Approved Supplier PO
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyApprovedSupplierPO() {
		verifyItemUnitAssociation();
		commonFunc.navigateToApprovedSupplierPOTab();
		verifyCreateApprovedSupplier(commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("Apps"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UserDefinedGeneral"),
				commonFunc.getDataFromTestDataSheet("HsnService"),
				commonFunc.getDataFromTestDataSheet("LeaveElgblDays"));
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getAppsNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
	}

	/**
	 * This method Is Used To Click Approved Supplier In Item Unit Association
	 * 
	 * @author Deepan-GWL
	 */
	public void clickApprovedSupplierInItemUnitAsso() {
//		flag = webDB.waitForElement(ApprovedSupplierPOLocators.ApprovedSupplierPoCheckBox, ElementType.Xpath);
//		if (flag) {
//		commonFunc.hardWait(1500);
		webDB.clickAnElement(ApprovedSupplierPOLocators.ApprovedSupplierPoCheckBox, ElementType.Xpath);
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.isElementSelected(ApprovedSupplierPOLocators.ApprovedSupplierPoCheckBox, ElementType.Xpath);
//	}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Change GRN Inspection TO Always
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyChangeGRNInspectionToAlways() {
		webDB.selectDropDownByText(ApprovedSupplierPOLocators.GrnInspectionSelect,
				commonFunc.getDataFromTestDataSheet("Always"), ElementType.Xpath);
	}

	/**
	 * This Method Is Used To Verify Change GRN Inspection TO Always
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyItemUnitAssociation() {
		commonFunc.navigateItemAssoScreen();
		customerItemFunc.searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		customerItemFunc.searchItemInFindPage();
		customerItemFunc.clickSearchButton();
		customerItemFunc.searchedItem();
		clickApprovedSupplierInItemUnitAsso();
		customerItemFunc.changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		verifyChangeGRNInspectionToAlways();
		customerItemFunc.clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();
	}

	/**
	 * This Method Is Used To Verify Item Unit Association For Final Inspection
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyItemUnitAssociationForFinalInnspection() {
		commonFunc.navigateItemAssoScreen();
		customerItemFunc.searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		customerItemFunc.searchItemInFindPage();
		customerItemFunc.clickSearchButton();
		customerItemFunc.searchedItem();
		clickApprovedSupplierInItemUnitAsso();
		customerItemFunc.changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		verifyClickFinalInspectionCheckBox();
		customerItemFunc.clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();

	}

	/**
	 * This Method Is Used To Verify Click Final Inspection CheckBox
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyClickFinalInspectionCheckBox() {
		webDB.clickAnElement(CommonUiLocators.FinalInspectionCheckBox, ElementType.Xpath);
		commonFunc.hardWait(2000);
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.isElementSelected(CommonUiLocators.FinalInspectionCheckBox, ElementType.Xpath);
		assertTrue(flag);
	}
}
