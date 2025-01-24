package commonfunctions.supplychainmanagement;

import static org.testng.Assert.assertTrue;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks January 15, 2025
 */
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

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
			flag = webDB.waitForElement(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Close Toast Icon Is Displayed");
				webDB.clickAnElementWithoutException(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseOrderPageLocators.HeaderTPostIcon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseOrderPageLocators.HeaderTPostIcon, ElementType.Xpath);
					logs.logging("INFO", "Header Post Icon Is Clicked");
//					flag = webDB.waitForElement(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath);
				}
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
		flag = webDB.waitForElement(PurchaseOrderPageLocators.DPOTickButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Tick Icon Is Displayed");
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
	 * @throws Exception
	 */
	public boolean verifyDirectPODomesticGeneralSimilarUOM() throws Exception {
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
	 * @throws Exception
	 */
	public boolean verifyDirectPODomesticMaterialSimilarUOM() throws Exception {
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
	 * @throws Exception
	 */
	public boolean verifyDirectPODomesticGeneralDissimilarUOM() throws Exception {
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
	 * 
	 * This Method Is Used To Verify Direct PO Domestic General Dissimilar
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 * @throws Exception
	 */
	public boolean verifyDirectPODomesticMaterialDissimilarUOM() throws Exception {
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
	 * This Method Is Used To Verify Direct Po Domestic Service Similar UOM
	 * 
	 * @return boolean flag
	 * @throws Exception
	 */
	public boolean verifyDirectPODomesticServiceSimilarUOM() throws Exception {
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
	 * @throws Exception
	 */
	public boolean verifyDirectPOImportGeneralSimilarUOM() throws Exception {
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
	 * @throws Exception
	 */
	public boolean verifyDirectPOImportMaterialSimilarUOM() throws Exception {
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
	 * @throws Exception
	 */
	public boolean verifyDirectPOImportServiceSimilarUOM() throws Exception {
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
	 * @throws Exception
	 */
	public boolean verifyDirectPOImportGeneralDissimilarUOM() throws Exception {
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
	 * 
	 * This Method Is Used To Verify Direct PO Import Material Dissimilar
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 * @throws Exception
	 */
	public boolean verifyDirectPOImportMaterialDissimilarUOM() throws Exception {
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
	 * 
	 * This Method Is Used To Verify Direct PO Domestic Service Dissimilar
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 * @throws Exception
	 */
	public boolean verifyDirectPODomesticServiceDissimilarUOM() throws Exception {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("DissimilarService"));
		verifyAddItemInPOWithQuantityAndUnitCost();
		verifyTickIconcreateItem();
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
	 * 
	 * This Method Is Used To Verify Direct PO Import Service Dissimilar
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 * @throws Exception
	 */
	public boolean verifyDirectPOImportServiceDissimilarUOM() throws Exception {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("DissimilarService"));
		verifyAddItemInPOWithQuantityAndUnitCost();
		verifyTickIconcreateItem();
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
	 * This Method Is Used To Delete Particular Lines Purchase Order PO
	 * 
	 * @author Deepan-GWL
	 * @param DataValue
	 */
	public void deleteParticularLinesPurchaseOrder(String DataValue) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseOrderPageLocators.DeleteLineItemPO(DataValue), ElementType.Xpath);
		if (flag) {
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

}
