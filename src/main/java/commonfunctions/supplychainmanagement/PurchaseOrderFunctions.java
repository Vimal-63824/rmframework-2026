package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks January 15, 2025
 */
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import static org.testng.Assert.assertTrue;
import java.text.DecimalFormat;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import commonlocators.supplychainmanagement.PurchaseRequestLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class PurchaseOrderFunctions {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	private static PurchaseRequestFunctions PR_Func = new PurchaseRequestFunctions();

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author Deepan-GWL
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 */
	public void verifyCreateDirectPOAddingParameters(String LocationId, String SupplierName, String POType) {
		commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.LocationIdButton, LocationId);
		flag = webDB.selectDropDownByValue(PurchaseOrderPageLocators.PoTypeSelect, POType, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.SupplierNameButton, SupplierName);
		}
		assertTrue(flag);
	}

	/**
	 * Verify Clicking Add Button
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyClickingAddButton() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.AddButtonPurchaseOrder, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.AddButtonPurchaseOrder, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.PurchaseOrderheader, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Button In Purchase Order Is Clicked");
				String Text = webDB.getTextFromElement(PurchaseOrderPageLocators.PurchaseOrderheader,
						ElementType.Xpath);
				logs.logging("INFO", "Get Text Header From Purchase order Is: " + Text);
				flag = Text.contains(commonFunc.getDataFromTestDataSheet("PurchaseOrder"));
				if (flag) {
					logs.logging("INFO", "Verified Header Get Text Value From Purchase Order");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Item In Po
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void verifyCreateItemInPo(String Item) {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Item Add Button Is Displayed Successfully");
			webDB.clickAnElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.POPageItemTable, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Item Add Button Is Clicked");
				webDB.clickAnElement(PurchaseOrderPageLocators.POPageItemTable, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ItemSelectButton, Item);
				flag = webDB.waitForElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
				if (flag) {
					String value = webDB.getAttributeFromElement(PurchaseOrderPageLocators.InputItemPO,
							ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
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
	 * Verify Add Table Specs
	 * 
	 * @author Deepan-GWL
	 * @param Tablespec
	 * @param InputParameter
	 * @param Enteredvalue
	 */
	public void verifyAddTableSpecs(String Tablespec, String InputParameter, String Enteredvalue) {
		flag = webDB.waitForElement(Tablespec, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(Tablespec, ElementType.Xpath);
			logs.logging("INFO", "Table Input Box Is Clicked");
			webDB.clearTextField(InputParameter, ElementType.Xpath);
			logs.logging("INFO", "Clear Text Field The Input Value");
			flag = webDB.waitForElement(Tablespec, ElementType.Xpath);
			if (flag) {
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
	 * Verify Create Item In Po
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAddItemInPOWithQuantityAndUnitCost() {
		try {
			// TODO WAIT_LOAD_TIME (will remove future)
			Thread.sleep(1000);
			verifyAddTableSpecs(PurchaseOrderPageLocators.OrderedQuantity,
					PurchaseOrderPageLocators.OrderedQuantityInput,
					commonFunc.getDataFromTestDataSheet("QuantityValue"));
			// TODO WAIT_LOAD_TIME (will remove future)
			Thread.sleep(1000);
			verifyAddTableSpecs(PurchaseOrderPageLocators.UnitCost, PurchaseOrderPageLocators.UnitCostInput,
					commonFunc.getDataFromTestDataSheet("UnitCostValue"));
			String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
			VariableHandler.setPONumber(PONumbervalue);
		} catch (Exception e) {
		}
	}

	/**
	 * Verify Header Tick And Post
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyHeaderTickAndPost() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.HeaderTickIconPO, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header Tick Icon Is Displayed");
			webDB.clickAnElement(PurchaseOrderPageLocators.HeaderTickIconPO, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForClickElement(PurchaseOrderPageLocators.HeaderTPostIcon, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(PurchaseOrderPageLocators.HeaderTPostIcon, ElementType.Xpath);
				logs.logging("INFO", "Header Post Icon Is Clicked");
				flag = (webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 10)
						|| webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath));
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
		try {
			// TODO WAIT_LOAD_TIME (will remove future)
			Thread.sleep(3000);
		} catch (Exception e) {
		}
		if (webDB.verifyIsElementDisplayed(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ForwardApproval,
					commonFunc.getDataFromTestDataSheet("AshokValue"));
			try {
				// TODO WAIT_LOAD_TIME (will remove future)
				Thread.sleep(2000);
			} catch (Exception e) {
			}
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(PurchaseOrderPageLocators.OkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseOrderPageLocators.OkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(PurchaseOrderPageLocators.AddButtonPurchaseOrder, ElementType.Xpath);
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
	 * Verify Tick Icon Create PO
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyTickIconcreatePO() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForClickElement(PurchaseOrderPageLocators.DPOTickButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Tick Icon Is Displayed");
			commonFunc.checkRefreshCellsDisappear();
			webDB.clickAnElement(PurchaseOrderPageLocators.DPOTickButton, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Verified Navigate To PO Edit Page");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Tick Icon Create Item
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyTickIconcreateItem() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseOrderPageLocators.TickIconPO, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.TickIconPO, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Item Tick Icon Is Clicked");
				webDB.clickAnElement(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath);
				logs.logging("INFO", "Toast Close Icon Is Clicked");
				flag = webDB.waitForElement(PurchaseOrderPageLocators.HeaderTickIconPO, ElementType.Xpath);
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
	public void verifyValidatePoStatus(String POno, String ExpectedStatus) {
		commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.PONumberSelect, POno);
		flag = webDB.waitForElement(PurchaseOrderPageLocators.SearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.SearchButton, ElementType.Xpath);
			logs.logging("INFO", "Search Button Is Clicked");
			try {
				// TODO WAIT_LOAD_TIME (will remove future)
				Thread.sleep(1000);
			} catch (Exception e) {
			}
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PurchaseOrderPageLocators.POStatusTableText(POno), ElementType.Xpath);
			if (flag) {
				String Status = webDB.getTextFromElement(PurchaseOrderPageLocators.POStatusTableText(POno),
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
	 * This Method Is Used To Verify Direct PO Domestic General Similar UOM
	 * 
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticGeneralSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
		verifyAddItemInPOWithQuantityAndUnitCost();
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("PohType"),
					commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct Po Domestic Material Similar UOM
	 * 
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticMaterialSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
		verifyAddItemInPOWithQuantityAndUnitCost();
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("MaterialType"), commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * 
	 * This Method Is Used To Verify Direct PO Domestic General Dissimilar
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticGeneralDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("DissimilarItem"));
		verifyAddItemInPOWithQuantityAndUnitCost();
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("Kgs"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("PohType"),
					commonFunc.getDataFromTestDataSheet("Kgs"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * 
	 * This Method Is Used To Verify Direct PO Domestic General Dissimilar
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticMaterialDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("DissimilarItem"));
		verifyAddItemInPOWithQuantityAndUnitCost();
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("Kgs"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("MaterialType"), commonFunc.getDataFromTestDataSheet("Kgs"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct Po Domestic Service Similar UOM
	 * 
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticServiceSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("GwlItem_Service"));
		verifyAddItemInPOWithQuantityAndUnitCost();
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		if (VariableHandler.getApprovalFlag()) {
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("CurrencyInr"),
						commonFunc.getDataFromTestDataSheet("ServiceType"),
						commonFunc.getDataFromTestDataSheet("UomNos"));
				approvalFunc.verifyNavigateback();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
				flag = commonFunc.logoutUser();
				if (flag) {
					commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
					commonFunc.navigatePurchaseOrderPage();
				}
			}
		}
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Import General Similar UOM
	 * 
	 * @return boolean flag
	 */
	public boolean verifyDirectPOImportGeneralSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
		verifyAddItemInPOWithQuantityAndUnitCost();
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyUsd"), commonFunc.getDataFromTestDataSheet("PohType"),
					commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct Po Import Material Similar UOM
	 * 
	 * @return boolean flag
	 */
	public boolean verifyDirectPOImportMaterialSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
		verifyAddItemInPOWithQuantityAndUnitCost();
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyUsd"),
					commonFunc.getDataFromTestDataSheet("MaterialType"), commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct Po Import Service Similar UOM
	 * 
	 * @return boolean flag
	 */
	public boolean verifyDirectPOImportServiceSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("GwlItem_Service"));
		verifyAddItemInPOWithQuantityAndUnitCost();
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		if (VariableHandler.getApprovalFlag()) {
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("CurrencyUsd"),
						commonFunc.getDataFromTestDataSheet("ServiceType"),
						commonFunc.getDataFromTestDataSheet("UomNos"));
				approvalFunc.verifyNavigateback();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
				flag = commonFunc.logoutUser();
				if (flag) {
					commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
					commonFunc.navigatePurchaseOrderPage();
				}
			}
		}
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();

		return flag;
	}

	/**
	 * 
	 * This Method Is Used To Verify Direct PO Import General Dissimilar
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPOImportGeneralDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("DissimilarItem"));
		verifyAddItemInPOWithQuantityAndUnitCost();
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("Kgs"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyUsd"), commonFunc.getDataFromTestDataSheet("PohType"),
					commonFunc.getDataFromTestDataSheet("Kgs"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * 
	 * This Method Is Used To Verify Direct PO Import Material Dissimilar
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPOImportMaterialDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("DissimilarItem"));
		verifyAddItemInPOWithQuantityAndUnitCost();
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("Kgs"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyUsd"),
					commonFunc.getDataFromTestDataSheet("MaterialType"), commonFunc.getDataFromTestDataSheet("Kgs"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Delete Particular Lines Purchase Order PO
	 * 
	 * @author Deepan-GWL
	 * @param DataValue
	 */
	public void deleteParticularLinesPurchaseOrder(String DataValue) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseOrderPageLocators.DeleteLineItemPO(DataValue), ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Item Delete Button Is Displayed");
			webDB.moveToElement(PurchaseRequestLocators.ItemDataLastRow, ElementType.Xpath);
			webDB.moveToElement(PurchaseOrderPageLocators.DeleteLineItemPO(DataValue), ElementType.Xpath);
			webDB.clickAnElement(PurchaseOrderPageLocators.DeleteLineItemPO(DataValue), ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Toast Message Displayed, Line Deleted Successfully with value: " + DataValue);
				if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Cancel Purchase Order Documents
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void VerifyCancelPurchaseOrderDocument() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
		verifyAddItemInPOWithQuantityAndUnitCost();
		verifyTickIconcreateItem();
		deleteParticularLinesPurchaseOrder(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
		clickHeaderCancelButton();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("CancelStatus"));
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click Header Cancel Button
	 * 
	 * @author Deepan-GWL
	 */
	public void clickHeaderCancelButton() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.CancelButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseOrderPageLocators.CancelButton, ElementType.Xpath);
			webDB.clickAnElement(PurchaseOrderPageLocators.CancelButton, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Toast Message Displayed, PO Cancelled Successfully");
				if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click pending and search PR no and verify
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickPendingPRAndSearchPRNo() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PendingPRBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.PendingPRBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PurchaseOrderPageLocators.PendingPRiframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated to the Pending PR Successfully");
				commonFunc.checkRefreshCellsDisappear();
				webDB.switchToFrame(PurchaseOrderPageLocators.PendingPRiframe, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseOrderPageLocators.PendingPR_FindPRNo, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.PendingPR_FindPRNo,
							VariableHandler.getPRNumber());
					commonFunc.checkRefreshCellsDisappear();
					webDB.switchToFrame(PurchaseOrderPageLocators.PendingPRiframe, ElementType.Xpath);
					flag = webDB.waitForElement(PurchaseOrderPageLocators.PendingPR_SearchBtn, ElementType.Xpath);
					if (flag) {
						webDB.moveToElement(PurchaseOrderPageLocators.PendingPR_SearchBtn, ElementType.Xpath);
						webDB.clickAnElement(PurchaseOrderPageLocators.PendingPR_SearchBtn, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						logs.logging("INFO", "Pending PR Search Button Clicked");
						List<WebElement> elements = webDB.getDriver().findElements(By.xpath("//table//td[3]"));
						a: for (WebElement webElement : elements) {
							if (!webElement.getText().equals(VariableHandler.getPRNumber())) {
								flag = false;
								break a;
							} else {
								flag = true;
							}
						}
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * This method used to click select all check box in pending PR and click
	 * 'create PO'
	 * 
	 * @author Sakthi-GWL
	 */
	public void selectAllPendingPRAndClickCreatePO() {
		commonFunc.checkRefreshCellsDisappear();
		webDB.switchToFrame(PurchaseOrderPageLocators.PendingPRiframe, ElementType.Xpath);
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PendingPR_SelectAllCheckBox, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.PendingPR_SelectAllCheckBox, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			webDB.waitForElement(PurchaseOrderPageLocators.PendingPR_CreatePO_Btn, ElementType.Xpath);
			flag = webDB.isElementEnabled(PurchaseOrderPageLocators.PendingPR_CreatePO_Btn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Select All CheckBox Clicked in Pending PR and Create PO Enabled");
				webDB.clickAnElement(PurchaseOrderPageLocators.PendingPR_CreatePO_Btn, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseOrderPageLocators.PendingPR_SupplierSelectBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Pending PR - 'Create PO' Clicked");
				}
			}
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * This method used to select supplier in Pending PR - Create PO and click Ok
	 * Button
	 * 
	 * @param Supplier
	 * 
	 * @author Sakthi-GWL
	 */
	public void selectSupplierAndCurrencyPendingPRAndClickTickIcon(String Supplier) {
		webDB.switchToFrame(PurchaseOrderPageLocators.PendingPRiframe, ElementType.Xpath);
		commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.PendingPR_SupplierSelectBtn, Supplier);
		webDB.switchToFrame(PurchaseOrderPageLocators.PendingPRiframe, ElementType.Xpath);
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PendingPR_OkBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.PendingPR_OkBtn, ElementType.Xpath);
			webDB.switchToDefaultContent();
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Create PO - 'Ok' Button Clicked");
				String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber,
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "Displayed PO Number Is: " + PONumbervalue);
				VariableHandler.setPONumber(PONumbervalue);
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify From PR to PO - Domestic Supplier General Item (Similar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDomesticGeneralSimilarUOMFromPRtoPO() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PR_General"), commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("PR_CPCCode"), commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		PR_Func.fillItemsQuantityCostAndSaveLine(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"), true);
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		verifyHeaderTickAndPost();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Verify From PR to PO - Domestic Supplier Service Item (Similar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDomesticServiceSimilarUOMFromPRtoPO() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PR_Service"), commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"),
				commonFunc.getDataFromTestDataSheet("PR_CPCCode"), commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		PR_Func.fillItemsQuantityCostAndSaveLine(commonFunc.getDataFromTestDataSheet("GwlItem_Service"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"), true);
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		verifyHeaderTickAndPost();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Verify From PR to PO - Domestic Supplier General Item (Dissimilar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDomesticGeneralDissimilarUOMFromPRtoPO() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PR_General"), commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("PR_CPCCode"), commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		PR_Func.fillItemsQuantityCostAndSaveLine(commonFunc.getDataFromTestDataSheet("DissimilarItem"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"), true);
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		verifyHeaderTickAndPost();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Verify From PR to PO - Domestic Supplier Service Item (Dissimilar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDomesticServiceDissimilarUOMFromPRtoPO() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PR_Service"), commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"),
				commonFunc.getDataFromTestDataSheet("PR_CPCCode"), commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		PR_Func.fillItemsQuantityCostAndSaveLine(commonFunc.getDataFromTestDataSheet("DissimilarService"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"), true);
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		verifyHeaderTickAndPost();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Verify From PR to PO - Import Supplier General Item (Similar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyImportGeneralSimilarUOMFromPRtoPO() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PR_General"), commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("PR_CPCCode"), commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		PR_Func.fillItemsQuantityCostAndSaveLine(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"), true);
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("WalmartSupplier"));
		verifyHeaderTickAndPost();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Verify From PR to PO - Import Supplier Service Item (Similar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyImportServiceSimilarUOMFromPRtoPO() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PR_Service"), commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"),
				commonFunc.getDataFromTestDataSheet("PR_CPCCode"), commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		PR_Func.fillItemsQuantityCostAndSaveLine(commonFunc.getDataFromTestDataSheet("GwlItem_Service"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"), true);
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("WalmartSupplier"));
		verifyHeaderTickAndPost();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Verify From PR to PO - Import Supplier General Item (Dissimilar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyImportGeneralDissimilarUOMFromPRtoPO() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PR_General"), commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("PR_CPCCode"), commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		PR_Func.fillItemsQuantityCostAndSaveLine(commonFunc.getDataFromTestDataSheet("DissimilarItem"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"), true);
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("WalmartSupplier"));
		verifyHeaderTickAndPost();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Verify From PR to PO - Import Supplier Service Item (Dissimilar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyImportServiceDissimilarUOMFromPRtoPO() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PR_Service"), commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"),
				commonFunc.getDataFromTestDataSheet("PR_CPCCode"), commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		PR_Func.fillItemsQuantityCostAndSaveLine(commonFunc.getDataFromTestDataSheet("DissimilarService"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"), true);
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("WalmartSupplier"));
		verifyHeaderTickAndPost();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * 
	 * This Method Is Used To Verify Direct PO Domestic Stock Transfer Similar Uom
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticStockTransferSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Location02"),
				commonFunc.getDataFromTestDataSheet("U1Supplier"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("St_Item_Similar"));
		verifyAddItemInPOWithQuantityAndUnitCost();
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("Po_Stock_Transfer"),
					commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO With Item Charges Calculations
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public void verifyDirectPOWithItemChargesCalculations() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
		verifyAddItemInPOWithQuantityAndUnitCost();
		verifyTickIconcreateItem();
		navigateToItemChargesTab();
		verifyAddItemCharges(commonFunc.getDataFromTestDataSheet("PackingCharges"));
		verifyAddItemChargesPercentages();
		itemChargesTickIcon();
		verifyItemCalculation();
		verifyPOTotalAmountCalculation();
		verifyTotalAmountCalculation();
		verifyClickPostIcon();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("PohType"),
					commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To verify Item Calculation
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void verifyItemCalculation() {
		String Item;
		List<WebElement> OrderedQtyList = webDB.getDriver()
				.findElements(By.xpath(PurchaseOrderPageLocators.OrderedQuantity));
		for (int i = 1; i <= OrderedQtyList.size(); i++) {
			Item = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.ItemName(i))).getText();
			logs.logging("INFO", "Get Text Item For Calculation Is: " + Item);
			String OrderedQty = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.OrderedQty(i)))
					.getText();
			logs.logging("INFO", "Get Text Ordered Quantity Is: " + OrderedQty);
			String UnitCost = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.UnitCostTable(i)))
					.getText();
			logs.logging("INFO", "Get Text Unit Cost Is: " + UnitCost);
			double Quantityd = Double.parseDouble(OrderedQty);
			double Costd = Double.parseDouble(UnitCost);
			double LineAmountd = Costd * Quantityd;
			String ExpectedLineAmount = verifyRoundOffDoubleNumberWithReturnString(LineAmountd,
					commonFunc.getDataFromTestDataSheet("DoubleDecimal"));
			logs.logging("INFO", "Expected Line Amount Is: " + ExpectedLineAmount);
			String ActualLineAmount = webDB.getTextFromElement(PurchaseOrderPageLocators.LineAmount(Item),
					ElementType.Xpath);
			logs.logging("INFO", "Actual Line Amount Is: " + ActualLineAmount);
			flag = ExpectedLineAmount.equals(ActualLineAmount);
			if (flag) {
				logs.logging("INFO", "Verified Actual Line Amount With Expected Line Amount");
				verifyTaxCalculation(Item, ExpectedLineAmount);
			} else {
				assertTrue(flag);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Tax Calculation
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 * @param LineAmount
	 */
	public void verifyTaxCalculation(String Item, String LineAmount) {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.TaxPercentage(Item), ElementType.Xpath);
		if (flag) {
			String TaxPercentage = webDB.getTextFromElement(PurchaseOrderPageLocators.TaxPercentage(Item),
					ElementType.Xpath);
			logs.logging("INFO", "Get Text Tax Percentage For Item " + Item + " Is: " + TaxPercentage);
			double TaxPercentaged = Double.parseDouble(TaxPercentage);
			double LineAmountd = Double.parseDouble(LineAmount);
			double onePercentd = LineAmountd / 100;
			logs.logging("INFO", "Calculation For One Percentage Tax Is: " + onePercentd);
			double TotalTaxd = onePercentd * TaxPercentaged;
			double individualTaxd = TotalTaxd / 2;
			String IndividaulTaxFinal = verifyRoundOffDoubleNumberWithReturnString(individualTaxd,
					commonFunc.getDataFromTestDataSheet("DoubleDecimal"));
			verifyPOTaxAmountWithTable(Item, IndividaulTaxFinal);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Round Off Double Number With Return String
	 * 
	 * @author Deepan-GWL
	 * @param number
	 * @param example
	 * @return String ExpectedNumber
	 */
	public String verifyRoundOffDoubleNumberWithReturnString(double number, String example) {
		DecimalFormat decfor = new DecimalFormat(example);
		String Expectednumber = decfor.format(number);
		return Expectednumber;
	}

	/**
	 * This Method Is Used To Verify Round Off Double With Return Double
	 * 
	 * @author Deepan-GWL
	 * @param number
	 * @param example
	 * @return double ExpectedNumber
	 */
	public double verifyRoundOffDoubleNumberWithReturnDouble(double number, String example) {
		DecimalFormat decfor = new DecimalFormat(example);
		String Expectednumber = decfor.format(number);
		double Expectednumberd = Double.parseDouble(Expectednumber);
		return Expectednumberd;
	}

	/**
	 * This Method Is Used To Verify PO Tax Amount With Table
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 * @param IndividualTax
	 */
	public void verifyPOTaxAmountWithTable(String Item, String IndividualTax) {
		logs.logging("INFO", "Expected Individual Tax Amount Is: " + IndividualTax);
		flag = webDB.waitForElement(PurchaseOrderPageLocators.TaxCGST(Item), ElementType.Xpath);
		if (flag) {
			flag = webDB.waitForElement(PurchaseOrderPageLocators.TaxSGST(Item), ElementType.Xpath);
			if (flag) {
				String Cgst = webDB.getTextFromElement(PurchaseOrderPageLocators.TaxCGST(Item), ElementType.Xpath);
				logs.logging("INFO", "Actual CGST Tax Value Is: " + Cgst);
				flag = Cgst.equals(IndividualTax);
				if (flag) {
					logs.logging("INFO", "Verified With CGST Tax Value With Individual Tax Value Calculated");
					String Sgst = webDB.getTextFromElement(PurchaseOrderPageLocators.TaxSGST(Item), ElementType.Xpath);
					flag = Sgst.equals(IndividualTax);
					logs.logging("INFO", "Actual CGST Tax Value Is: " + Sgst);
					if (flag) {
						logs.logging("INFO", "Verified With SGST Tax Value With Individual Tax Value Calculated");
					}
				}
			}

		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Total Amount Calculation
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyTotalAmountCalculation() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.POTotalAmount, ElementType.Xpath);
		if (flag) {
			String POTotalAmount = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.POTotalAmount,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			flag = POTotalAmount.equals(VariableHandler.getPOTotalAmount());

		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Item Charge Addition
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyItemChargeAddition() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.AddItemCharges, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Add Item Charges Button Is Displayed");
			webDB.clickAnElement(PurchaseOrderPageLocators.AddItemCharges, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.ChargeItemTable, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Navigate To Item Charges Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToItemChargesTab() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemChargesTab, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Item Charges Tab Is Displayed");
			webDB.clickAnElement(PurchaseOrderPageLocators.ItemChargesTab, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.AddItemCharges, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Add Item Charges
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void verifyAddItemCharges(String Item) {
		verifyItemChargeAddition();
		flag = webDB.waitForElement(PurchaseOrderPageLocators.ChargeItemTable, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Item Charges Add Button Is Clicked");
			webDB.clickAnElement(PurchaseOrderPageLocators.ChargeItemTable, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ChargeItemButton, Item);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.ChargeItemInput, ElementType.Xpath);
			if (flag) {
				String value = webDB.getAttributeFromElement(PurchaseOrderPageLocators.ChargeItemInput,
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "Get Text Item Charges Value from Table Is: " + value);
				flag = value.contains(Item);
				if (flag) {
					logs.logging("INFO", "Verified Item Charges Value Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Add Item Charges Percentages
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAddItemChargesPercentages() {
		try {
			// TODO WAIT_LOAD_TIME (wverifyAddItemChargesPercentagesill remove future)
			Thread.sleep(1000);
			verifyAddTableSpecs(PurchaseOrderPageLocators.ChargePercentage,
					PurchaseOrderPageLocators.ChargePercentageInput,
					commonFunc.getDataFromTestDataSheet("QuantityValue"));
			flag = webDB.waitForElement(PurchaseOrderPageLocators.GLAccDesc, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseOrderPageLocators.GLAccDesc, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.GlAccDescButton,
						commonFunc.getDataFromTestDataSheet("GlAccDescription"));
				flag = webDB.waitForElement(PurchaseOrderPageLocators.ChargesTickIcon, ElementType.Xpath);
			}

		} catch (Exception e) {
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Item Charges Click Tick Icon
	 * 
	 * @author Deepan-GWL
	 */
	public void itemChargesTickIcon() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.ChargesTickIcon, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Charges Tick Button Is Displayed");
			webDB.clickAnElement(PurchaseOrderPageLocators.ChargesTickIcon, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Close Toast Icon Is Displayed");
				webDB.clickAnElementWithoutException(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseOrderPageLocators.IncludeCharges, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseOrderPageLocators.IncludeCharges, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(PurchaseOrderPageLocators.LineTab, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Include Charges Is Clicked");
						webDB.clickAnElement(PurchaseOrderPageLocators.LineTab, ElementType.Xpath);
						flag = webDB.waitForElement(PurchaseOrderPageLocators.HeaderTPostIcon, ElementType.Xpath);
					}
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
		double TotalLineAmountd = 0;
		double TotalTaxAmountd = 0;
		double LinecalAmount = 0;
		List<WebElement> LineAmountWE = webDB.getDriver()
				.findElements(By.xpath(PurchaseOrderPageLocators.LineAmountWE));
		for (int i = 1; i <= LineAmountWE.size(); i++) {
			String LineAmount = webDB.getTextFromElement(PurchaseOrderPageLocators.LineAmount(i), ElementType.Xpath);
			logs.logging("INFO", "Get Text Line Amount Is: " + LineAmount);
			double LineAmountd = Double.parseDouble(LineAmount);
			TotalLineAmountd = LinecalAmount + LineAmountd;
			logs.logging("INFO", "Calculated Total Line Amount Is: " + TotalLineAmountd);
			LinecalAmount = verifyRoundOffDoubleNumberWithReturnDouble(TotalLineAmountd,
					commonFunc.getDataFromTestDataSheet("DoubleDecimal"));
		}
		double TaxcalAmount = 0;
		for (int i = 1; i <= LineAmountWE.size(); i++) {
			for (int j = 13; j <= 14; j++) {
				String TaxAmount = webDB.getTextFromElement(PurchaseOrderPageLocators.TaxAmount(i, j),
						ElementType.Xpath);
				logs.logging("INFO", "Get Text Tax Amount Is: " + TaxAmount);
				double TaxAmountd = Double.parseDouble(TaxAmount);
				TotalTaxAmountd = TaxcalAmount + TaxAmountd;
				TaxcalAmount = verifyRoundOffDoubleNumberWithReturnDouble(TotalTaxAmountd,
						commonFunc.getDataFromTestDataSheet("DoubleDecimal"));
			}
			logs.logging("INFO", "Total Tax Amount For Line " + i + " Is: " + TotalTaxAmountd);
		}
		logs.logging("INFO", "Total Tax Amount Calculated Is: " + TotalTaxAmountd);
		double POTotalAmount = LinecalAmount + TaxcalAmount;
		String Totalvalue = verifyRoundOffDoubleNumberWithReturnString(POTotalAmount,
				commonFunc.getDataFromTestDataSheet("DoubleDecimal"));
		logs.logging("INFO", "Expected Total Po Value Is: " + Totalvalue);
		VariableHandler.setPOTotalAmount(Totalvalue);
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Click Post Icon
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyClickPostIcon() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.HeaderTPostIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.HeaderTPostIcon, ElementType.Xpath);
			logs.logging("INFO", "Header Post Icon Is Clicked");
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To verify Item Calculation
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void verifyItemCalculationWithLineAmount() {
		String Item;
		List<WebElement> OrderedQtyList = webDB.getDriver()
				.findElements(By.xpath(PurchaseOrderPageLocators.OrderedQuantity));
		for (int i = 1; i <= OrderedQtyList.size(); i++) {
			Item = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.ItemName(i))).getText();
			logs.logging("INFO", "Get Text Item For Calculation Is: " + Item);
			String OrderedQty = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.OrderedQty(i)))
					.getText();
			logs.logging("INFO", "Get Text Ordered Quantity Is: " + OrderedQty);
			String UnitCost = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.UnitCostTable(i)))
					.getText();
			logs.logging("INFO", "Get Text Unit Cost Is: " + UnitCost);
			double Quantityd = Double.parseDouble(OrderedQty);
			double Costd = Double.parseDouble(UnitCost);
			double LineAmountd = Costd * Quantityd;
			String ExpectedLineAmount = String.format("%.2f", LineAmountd);
			logs.logging("INFO", "Expected Line Amount Is: " + ExpectedLineAmount);
			String ActualLineAmount = webDB.getTextFromElement(PurchaseOrderPageLocators.LineAmount(Item),
					ElementType.Xpath);
			logs.logging("INFO", "Actual Line Amount Is: " + ActualLineAmount);
			flag = ExpectedLineAmount.equals(ActualLineAmount);
			if (flag) {
				logs.logging("INFO", "Verified Actual Line Amount With Expected Line Amount");
			} else {
				assertTrue(flag);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify PO UOM
	 * 
	 * @author Deepan-GWL
	 * @param ExpectedUOM
	 */
	public void verifyPoUOM(String ExpectedUOM) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseOrderPageLocators.UOMTable, ElementType.Xpath);
		if (flag) {
			String ActualUOM = webDB.getTextFromElement(PurchaseOrderPageLocators.UOMTable, ElementType.Xpath);
			logs.logging("INFO", "Expected UOM Is: " + ExpectedUOM);
			logs.logging("INFO", "Actual Get Text UOM Is: " + ActualUOM);
			flag = ActualUOM.equals(ExpectedUOM);
			if (flag) {
				logs.logging("INFO", "Verified Actual UOM With Expected UOM");
			}

		}
		assertTrue(flag);

	}

}
