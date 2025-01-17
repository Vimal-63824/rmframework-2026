package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks January 15, 2025
 */
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class PurchaseOrderFunctions {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();

	/**
	 * Verify Direct PO Creation
	 * 
	 * @author Deepan-GWL
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @return boolean flag
	 */
	public boolean directPOCreation(String LocationId, String SupplierName, String POType) {
		flag = commonFunc.clickCreateIcon();
		if (flag) {
			flag = verifyCreateDirectPOAddingParameters(LocationId, SupplierName, POType);
			if (flag) {
				flag = verifyTickIconcreatePO();
			}
		}
		return flag;
	}

	/**
	 * Verify Direct PO Domestic General Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPoDomesticGeneralSimilarStockAndSupplier() {
		flag = directPOCreation(webDB.getDataFromProperties("Loc_100"), webDB.getDataFromProperties("PraxairSupplier"),
				webDB.getDataFromProperties("GeneralValue"));
		if (flag) {
			flag = verifyCreateItemAndAddedQuantity();
		}
		return flag;
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author Deepan-GWL
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @return boolean flag
	 */
	public boolean verifyCreateDirectPOAddingParameters(String LocationId, String SupplierName, String POType) {
		flag = commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.LocationIdButton, LocationId);
		if (flag) {
			webDB.selectDropDownByValue(PurchaseOrderPageLocators.PoTypeSelect, POType, ElementType.Xpath);
			if (flag) {
				flag = commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.SupplierNameButton,
						SupplierName);
			}
		}
		return flag;

	}

	/**
	 * Verify Clicking Add Button
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyClickingAddButton() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.AddButtonPurchaseOrder, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.AddButtonPurchaseOrder, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.PurchaseOrderheader, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Button In Purchase Order Is Clicked");
				String Text = webDB.getTextFromElement(PurchaseOrderPageLocators.PurchaseOrderheader,
						ElementType.Xpath);
				logs.logging("INFO", "Get Text Header From Purchase order Is: " + Text);
				flag = Text.contains(webDB.getDataFromProperties("PurchaseOrder"));
				if (flag) {
					logs.logging("INFO", "Verified Header Get Text Value From Purchase Order");
				}
			}
		}
		return flag;
	}

	/**
	 * Verify Create Item In Po
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 * @return boolean flag
	 */
	public boolean verifyCreateItemInPo(String Item) {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Item Add Button Is Displayed Successfully");
			webDB.clickAnElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.POPageItemTable, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Item Add Button Is Clicked");
				webDB.clickAnElement(PurchaseOrderPageLocators.POPageItemTable, ElementType.Xpath);
				flag = commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ItemSelectButton, Item);
				flag = webDB.waitForElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
				if (flag) {
					String value = webDB.getAttributeFromElement(PurchaseOrderPageLocators.InputItemPO,
							ElementType.Xpath, webDB.getDataFromProperties("Value"));
					logs.logging("INFO", "Get Text Item Name Value from Table Is: " + value);
					flag = value.contains(webDB.getDataFromProperties("GwlItem_Serial"));
					if (flag) {
						logs.logging("INFO", "Verified Item Value Successfully");
					}
				}
			}
		}
		return flag;

	}

	/**
	 * Verify Create Item And Added Quantity
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreateItemAndAddedQuantity() {
		flag = verifyCreateItemInPo(webDB.getDataFromProperties("GwlItem_Serial"));
		if (flag) {
			flag = verifyCreateItemInPO();
		}

		return flag;

	}

	/**
	 * Verify Add Table Specs
	 * 
	 * @author Deepan-GWL
	 * @param Tablespec
	 * @param InputParameter
	 * @param Enteredvalue
	 * @return boolean flag
	 */
	public boolean verifyAddTableSpecs(String Tablespec, String InputParameter, String Enteredvalue) {
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
						webDB.getDataFromProperties("Value"));
				logs.logging("INFO", "Get Attribute Value Is: " + value);
				flag = value.contains(Enteredvalue);
				if (flag) {
					logs.logging("INFO", "Verified Entered Value With Get Attribute Value");
				}
			}
		}

		return flag;

	}

	/**
	 * Verify Create Item In Po
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreateItemInPO() {
		try {
			Thread.sleep(1000);
			flag = verifyAddTableSpecs(PurchaseOrderPageLocators.OrderedQuantity,
					PurchaseOrderPageLocators.OrderedQuantityInput, webDB.getDataFromProperties("QuantityValue"));
			if (flag) {
				Thread.sleep(1000);
				flag = verifyAddTableSpecs(PurchaseOrderPageLocators.UnitCost, PurchaseOrderPageLocators.UnitCostInput,
						webDB.getDataFromProperties("UnitCostValue"));
				if (flag) {
					String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber,
							ElementType.Xpath, webDB.getDataFromProperties("Value"));
					logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
					VariableHandler.setPONumber(PONumbervalue);
					flag = commonFunc.checkRefreshCellsDisappear();
					if (flag) {
						flag = verifyTickIconcreateItem();
						if (flag) {
							flag = verifyHeaderTickAndPost();
							if (flag) {
								flag = verifyWaitingForApproval();
							}
						}
					}
				}
			}
		} catch (Exception e) {
		}
		return flag;

	}

	/**
	 * Verify Header Tick And Post
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyHeaderTickAndPost() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.HeaderTickIconPO, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header Tick Icon Is Displayed");
			webDB.clickAnElement(PurchaseOrderPageLocators.HeaderTickIconPO, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Close Toast Icon Is Displayed");
				webDB.clickAnElement(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseOrderPageLocators.HeaderTPostIcon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseOrderPageLocators.HeaderTPostIcon, ElementType.Xpath);
					logs.logging("INFO", "Header Post Icon Is Clicked");
				}
			}
		}
		return flag;
	}

	/**
	 * Verify Waiting For Approval
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyWaitingForApproval() {
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}
		webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
		logs.logging("INFO", "Switched To Workflow Frame");
		flag = commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ForwardApproval,
				webDB.getDataFromProperties("AshokValue"));
		if (flag) {
			try {
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
		}
		webDB.switchToDefaultContent();
		return flag;
	}

	/**
	 * Verify Tick Icon Create PO
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyTickIconcreatePO() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.DPOTickButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Tick Icon Is Displayed");
			webDB.clickAnElement(PurchaseOrderPageLocators.DPOTickButton, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Verified Navigate To PO Edit Page");
			}
		}
		return flag;
	}

	/**
	 * Verify Tick Icon Create Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyTickIconcreateItem() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.TickIconPO, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.TickIconPO, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Item Tick Icon Is Clicked");
				webDB.clickAnElement(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath);
				logs.logging("INFO", "Toast Close Icon Is Clicked");
			}
		}
		return flag;
	}

	/**
	 * Verify Select PO Number
	 * 
	 * @author Deepan-GWL
	 * @param locators
	 * @param PONumber
	 * @return boolean flag
	 */
	public boolean verifySelectPONumber(String locators, String PONumber) {
		flag = webDB.waitForElement(locators, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(locators, ElementType.Xpath);
			logs.logging("INFO", "Select Locators Is Clicked Successfully");
			List<WebElement> searchBar = webDB.getDriver().findElements(By.xpath(PurchaseOrderPageLocators.searchBar));
			for (int i = 0; i < searchBar.size(); i++) {
				try {
					searchBar.get(i).isDisplayed();
					searchBar.get(i).sendKeys(PONumber);
					logs.logging("INFO", "Send Text Po Number Is: " + PONumber);
					flag = true;
					break;
				} catch (Exception e) {
					flag = false;
					continue;
				}
			}
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
			}
			webDB.pressDownArrowKey();
			logs.logging("INFO", "Down Arrow Is Clicked");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
			webDB.pressEnterKey();
			logs.logging("INFO", "Enter Key Is Clicked");
			flag = commonFunc.checkRefreshCellsDisappear();
		}
		return flag;
	}

	/**
	 * Verify Validate PO Approved Status
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyValidatePoApprovedStatus() {
		flag = verifySelectPONumber(PurchaseOrderPageLocators.PONumberSelect, VariableHandler.getPONumber());
		if (flag) {
			flag = webDB.waitForElement(PurchaseOrderPageLocators.SearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseOrderPageLocators.SearchButton, ElementType.Xpath);
				logs.logging("INFO", "Search Button Is Clicked");
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
				}
				flag = webDB.waitForElement("//a[text()='" + VariableHandler.getPONumber() + "']/../../td[last()]",
						ElementType.Xpath);
				if (flag) {
					String ApproveStatus = webDB.getTextFromElement(
							"//a[text()='" + VariableHandler.getPONumber() + "']/../../td[last()]", ElementType.Xpath);
					logs.logging("INFO", "Get Text Status Is: " + ApproveStatus);
					flag = ApproveStatus.contains(webDB.getDataFromProperties("ApproveStatus"));
					if (flag) {
						logs.logging("INFO", "Verified Approve Status In Table");
					}
				}
			}
		}

		return flag;
	}

	public boolean verifyDirectPODomesticGeneralSimilarUOM() throws Exception {
		flag = commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		if (flag) {
			flag = commonFunc.navigatePurchaseOrderPage();
			if (flag) {
				flag = verifyDirectPoDomesticGeneralSimilarStockAndSupplier();
				if (flag) {
					flag = commonFunc.logoutUser();
					if (flag) {
						flag = approvalFunc.verifyAshokAccountPOApproval();
						if (flag) {
							flag = commonFunc.logoutUser();
							if (flag) {
								flag = commonFunc.validCommonLogin(System.getProperty("HodUsername"),
										System.getProperty("HodPassword"));
								if (flag) {
									flag = commonFunc.navigatePurchaseOrderPage();
									if (flag) {
										flag = verifyValidatePoApprovedStatus();
										if (flag) {
											flag = commonFunc.logoutUser();
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return flag;
	}

	/**
	 * Verify Direct PO Domestic Material Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPoDomesticMaterialSimilarStockAndSupplier() {
		flag = directPOCreation(webDB.getDataFromProperties("Loc_100"), webDB.getDataFromProperties("PraxairSupplier"),
				webDB.getDataFromProperties("MaterialValue"));
		if (flag) {
			flag = verifyCreateItemAndAddedQuantity();
		}
		return flag;
	}

	public boolean verifyDirectPODomesticMaterialSimilarUOM() throws Exception {
		flag = commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		if (flag) {
			flag = commonFunc.navigatePurchaseOrderPage();
			if (flag) {
				flag = verifyDirectPoDomesticMaterialSimilarStockAndSupplier();
				if (flag) {
					flag = commonFunc.logoutUser();
					if (flag) {
						flag = approvalFunc.verifyAshokAccountPOApprovalWithMaterial();
						if (flag) {
							flag = commonFunc.logoutUser();
							if (flag) {
								flag = commonFunc.validCommonLogin(System.getProperty("HodUsername"),
										System.getProperty("HodPassword"));
								if (flag) {
									flag = commonFunc.navigatePurchaseOrderPage();
									if (flag) {
										flag = verifyValidatePoApprovedStatus();
										if (flag) {
											flag = commonFunc.logoutUser();
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public boolean verifyDirectPODomesticServiceSimilarUOM() throws Exception {
		flag = commonFunc.validCommonLogin(webDB.getDataFromProperties("AdminLoginUsername"),
				webDB.getDataFromProperties("AdminLoginPassword"));
		if (flag) {
			flag = commonFunc.navigatePurchaseOrderPage();
			if (flag) {
				flag = verifyDirectPoDomesticServiceSimilarStockAndSupplier();
				if (flag) {
					flag = commonFunc.logoutUser();
					if (flag) {
						flag = approvalFunc.verifyAshokAccountPOApprovalWithService();
						if (flag) {
							flag = commonFunc.logoutUser();
							if (flag) {
								flag = commonFunc.validCommonLogin(webDB.getDataFromProperties("AdminLoginUsername"),
										webDB.getDataFromProperties("AdminLoginPassword"));
								if (flag) {
									flag = commonFunc.navigatePurchaseOrderPage();
									if (flag) {
										flag = verifyValidatePoApprovedStatus();
									}
								}
							}
						}
					}
				}
			}
		}
		return flag;
	}

	/**
	 * Verify Direct PO Domestic Service Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPoDomesticServiceSimilarStockAndSupplier() {
		flag = directPOCreation(webDB.getDataFromProperties("Loc_100"), webDB.getDataFromProperties("PraxairSupplier"),
				webDB.getDataFromProperties("ServiceValue"));
		if (flag) {
			flag = verifyCreateItemAndAddedQuantity();
		}
		return flag;
	}
}
