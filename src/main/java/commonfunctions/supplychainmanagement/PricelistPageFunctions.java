package commonfunctions.supplychainmanagement;

/**
 * @author Durai
 * @company Galaxy Weblinks Feburary 05, 2025
 */
import static org.testng.Assert.assertTrue;
import java.io.File;
import org.testng.Assert;
import commonfunctions.CommonFunctions;
import commonfunctions.ItemDetailsHandler;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.ApprovalPageLocators;
import commonlocators.supplychainmanagement.PricelistPageLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import commonlocators.supplychainmanagement.PurchaseRequestLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class PricelistPageFunctions {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();

	/**
	 * This Methods Is Used To Verify Creation Of PRICE LIST for Domestic Supplier
	 * Similar UOM
	 * 
	 * @author Durai
	 * @return boolean flag
	 */
	public boolean verifycreationofpricelistDomesticSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.navigatePricelistPage();
		clickAddIconFillDetailsOfPricelist(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DomSupplier_ID"),
				commonFunc.getDataFromTestDataSheet("SimItem_ID"), commonFunc.getDataFromTestDataSheet("Disc_value"));
		EnterPrice(commonFunc.getDataFromTestDataSheet("Cost_Value"));
		EnterEffFromandToo();
		Findcreate(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DomSupplier_ID"),
				commonFunc.getDataFromTestDataSheet("SimItem_ID"), commonFunc.getDataFromTestDataSheet("Disc_value"));
		commonFunc.hardWait(1000);
		EnterDiscount(commonFunc.getDataFromTestDataSheet("Disc_Value"));
		saveHeaderAndClickPost();
		WaitingForApproval();
		commonFunc.hardWait(1000);
		flag = commonFunc.logoutUser();
		if (flag) {
			Forwardapprovalscreen();
			filterDocumentsforPriceistApproval(VariableHandler.getPRLISTNumber());
			verifyApproveWithDocNumber(VariableHandler.getPRLISTNumber());
		}

		return flag;

	}

	/**
	 * This Methods Is Used To Verify Creation Of PRICE LIST for Domestic Supplier
	 * DisSimilar UOM
	 * 
	 * @author Durai
	 * @return boolean flag
	 */
	public boolean verifycreationofpricelistDomesticDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.navigatePricelistPage();
		clickAddIconFillDetailsOfPricelist(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DomSupplier_ID"),
				commonFunc.getDataFromTestDataSheet("DissimItem_ID"),
				commonFunc.getDataFromTestDataSheet("Disc_value"));
		EnterPrice(commonFunc.getDataFromTestDataSheet("Cost_Value"));
		EnterEffFromandToo();
		Findcreate(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DomSupplier_ID"),
				commonFunc.getDataFromTestDataSheet("DissimItem_ID"),
				commonFunc.getDataFromTestDataSheet("Disc_Value"));
		commonFunc.hardWait(1000);
		EnterDiscount(commonFunc.getDataFromTestDataSheet("Disc_Value"));
		commonFunc.hardWait(3000);
		saveHeaderAndClickPost();
		WaitingForApproval();
		commonFunc.hardWait(1000);
		flag = commonFunc.logoutUser();
		Forwardapprovalscreen();
		filterDocumentsforPriceistApproval(VariableHandler.getPRLISTNumber());
		verifyApproveWithDocNumber(VariableHandler.getPRLISTNumber());

		return flag;

	}

	/**
	 * This Methods Is Used To Verify Creation Of PRICE LIST for Import Supplier
	 * DisSimilar UOM
	 * 
	 * @author Durai
	 * @return boolean flag
	 */
	public boolean verifycreationofpricelistImportsimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.navigatePricelistPage();
		clickAddIconFillDetailsOfPricelist(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ImpSupplier_ID"),
				commonFunc.getDataFromTestDataSheet("SimItem_ID"), commonFunc.getDataFromTestDataSheet("Disc_value"));
		EnterPrice(commonFunc.getDataFromTestDataSheet("Cost_Value"));
		EnterEffFromandToo();
		Findcreate(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ImpSupplier_ID"),
				commonFunc.getDataFromTestDataSheet("SimItem_ID"), commonFunc.getDataFromTestDataSheet("Disc_Value"));
		commonFunc.hardWait(1000);
		EnterDiscount(commonFunc.getDataFromTestDataSheet("Disc_Value"));
		commonFunc.hardWait(3000);
		saveHeaderAndClickPost();
		WaitingForApproval();
		commonFunc.hardWait(1000);
		flag = commonFunc.logoutUser();
		Forwardapprovalscreen();
		filterDocumentsforPriceistApproval(VariableHandler.getPRLISTNumber());
		verifyApproveWithDocNumber(VariableHandler.getPRLISTNumber());

		return flag;

	}

	/**
	 * This Methods Is Used To Verify Creation Of PRICE LIST for Import Supplier
	 * DisSimilar UOM
	 * 
	 * @author Durai
	 * @return boolean flag
	 */
	public boolean verifycreationofpricelistImportDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.navigatePricelistPage();
		clickAddIconFillDetailsOfPricelist(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ImpSupplier_ID"),
				commonFunc.getDataFromTestDataSheet("DissimItem_ID"),
				commonFunc.getDataFromTestDataSheet("Disc_value"));
		EnterPrice(commonFunc.getDataFromTestDataSheet("Cost_Value"));
		EnterEffFromandToo();
		Findcreate(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ImpSupplier_ID"),
				commonFunc.getDataFromTestDataSheet("DissimItem_ID"),
				commonFunc.getDataFromTestDataSheet("Disc_Value"));
		commonFunc.hardWait(1000);
		EnterDiscount(commonFunc.getDataFromTestDataSheet("Disc_Value"));
		commonFunc.hardWait(3000);
		saveHeaderAndClickPost();
		WaitingForApproval();
		commonFunc.hardWait(1000);
		flag = commonFunc.logoutUser();
		if (flag) {
			Forwardapprovalscreen();
			filterDocumentsforPriceistApproval(VariableHandler.getPRLISTNumber());
			verifyApproveWithDocNumber(VariableHandler.getPRLISTNumber());
		}

		return flag;

	}

	/**
	 * This Methods Is Used To Add Location Supplier Item Discount
	 * 
	 * @author Durai
	 */

	public void clickAddIconFillDetailsOfPricelist(String Location, String Supplier, String Item, String Discount) {
		commonFunc.clickAddIconPricelist();
		flag = webDB.waitForElement(PricelistPageLocators.LocationLov, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(PricelistPageLocators.LocationLov, Location);
			if (flag) {
				logs.logging("INFO", "Location is Entered");
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PricelistPageLocators.SuppliernameLov, ElementType.Xpath);
				if (flag) {
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.searchAndSelectValueInFilters(PricelistPageLocators.SuppliernameLov, Supplier);
					if (flag) {
						logs.logging("INFO", "Supplier is Entered");
						flag = webDB.waitForElement(PricelistPageLocators.ItemLov, ElementType.Xpath);
						if (flag) {
							commonFunc.searchAndSelectValueInFilters(PricelistPageLocators.ItemLov, Item);
							if (flag) {
								logs.logging("INFO", "Item is Entered");
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Enter Price
	 * 
	 * @author Durai
	 */
	public void EnterPrice(String Cost) {
		flag = webDB.waitForElement(PricelistPageLocators.PriceField, ElementType.Xpath);
		webDB.clearText(PricelistPageLocators.PriceField, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(PricelistPageLocators.PriceField, Cost, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Cost is Entered" + Cost);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Create find and giving Effective From Date and Too
	 * Date
	 * 
	 * @author Durai
	 */

	public void EnterEffFromandToo() {
		System.out.println("Input Eff");
		flag = webDB.waitForElement(PricelistPageLocators.EffFrom, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(PricelistPageLocators.EffFrom, PricelistPageLocators.FromDate, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "From Date Is Entered");
				flag = webDB.waitForElement(PricelistPageLocators.EffTo, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PricelistPageLocators.EffTo, ElementType.Xpath);
					webDB.sendTextToAnElement(PricelistPageLocators.EffTo, PricelistPageLocators.ToDate,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "To Date Is Entered");
						webDB.clickAnElement(PricelistPageLocators.PriceField, ElementType.Xpath);
						commonFunc.hardWait(2000);
						System.out.println("Input To");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify to create New Record for Pricelist
	 * 
	 * @author Durai
	 * @param Location
	 * @param Supplier
	 * @param Item
	 * @param Discount
	 */

	public void Findcreate(String Location, String Supplier, String Item, String Discount) {
		flag = webDB.waitForElement(PricelistPageLocators.FindCreateButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.FindCreateButton, ElementType.Xpath);
			webDB.waitForElement(PricelistPageLocators.PricelistDocNum, ElementType.Xpath);
			String PRLISTNum = webDB.getTextFromElement(PricelistPageLocators.PricelistDocNum, ElementType.Xpath);
			VariableHandler.setPRLISTNumber(PRLISTNum);
			logs.logging("INFO", "Price List Number Is :" + VariableHandler.getPRLISTNumber());
			validateLocationInPricelistHeader(Location);
			validateSupplierInPricelistHeader(Supplier);
			validateItemInPricelistHeader(Item);

		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Enter Discount
	 * 
	 * @author Durai
	 * @param Discount
	 */

	public void EnterDiscount(String Discount) {
		flag = webDB.waitForClickElement(PricelistPageLocators.DiscountField, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(PricelistPageLocators.DiscountField, ElementType.Xpath);
			webDB.clearText(PricelistPageLocators.Discountinput, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Discount Field is clicked");
			}
			webDB.doubleClickAnElement(PricelistPageLocators.DiscountField, ElementType.Xpath);
			webDB.sendTextToAnElement(PricelistPageLocators.Discountinput, Discount, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Discount value Is Entered" + Discount);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Validate the Location Filed is correctly eneterd
	 * 
	 * @author Durai
	 * @param Location
	 */

	private void validateLocationInPricelistHeader(String Location) {
		flag = webDB.waitForElement(PricelistPageLocators.Pricelist_HeaderLoc, ElementType.Xpath);
		if (flag) {
			String DisplayText = webDB.getTextFromElement(PricelistPageLocators.Pricelist_HeaderLoc, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Expected Text: " + Location + " Displayed: " + DisplayText);
			}
			flag = DisplayText.equalsIgnoreCase(Location);
			if (flag) {
				logs.logging("INFO", "verified Displayed Location in PRICELIST Header: " + DisplayText);
			} else {
				logs.logging("INFO", "Verification failed in Location PRICELIST Header");
				logs.logging("INFO", "Expected Text: " + Location + " Displayed: " + DisplayText);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Validate the Supplier Filed is correctly eneterd
	 * 
	 * @author Durai
	 * @param Supplier
	 */

	private void validateSupplierInPricelistHeader(String Supplier) {
		flag = webDB.waitForElement(PricelistPageLocators.Pricelist_HeaderSupplier, ElementType.Xpath);
		if (flag) {
			String DisplayText = webDB.getTextFromElement(PricelistPageLocators.Pricelist_HeaderSupplier,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Expected Text: " + Supplier + " Displayed: " + DisplayText);
			}

			flag = DisplayText.equalsIgnoreCase(Supplier);
			if (flag) {
				logs.logging("INFO", "verified Displayed Supplier in PRICELIST Header: " + DisplayText);
			} else {
				logs.logging("INFO", "Verification failed in Supplier PRICELIST Header");
				logs.logging("INFO", "Expected Text: " + Supplier + " Displayed: " + DisplayText);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Validate the Item Filed is correctly eneterd
	 * 
	 * @author Durai
	 * @param Item
	 */

	private void validateItemInPricelistHeader(String Item) {
		flag = webDB.waitForElement(PricelistPageLocators.Pricelist_HeaderItem, ElementType.Xpath);
		if (flag) {
			String DisplayText = webDB.getTextFromElement(PricelistPageLocators.Pricelist_HeaderItem,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Expected Text: " + Item + " Displayed: " + DisplayText);
			}
			flag = DisplayText.equalsIgnoreCase(Item);
			if (flag) {
				logs.logging("INFO", "verified Displayed Item in PRICELIST Header: " + DisplayText);
			} else {
				logs.logging("INFO", "Verification failed in Item PRICELIST Header");
				logs.logging("INFO", "Expected Text: " + Item + " Displayed: " + DisplayText);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Save the Document And click on Active Button
	 * 
	 * @author Durai
	 */

	public void saveHeaderAndClickPost() {
		flag = webDB.waitForClickElement(PricelistPageLocators.SAVEBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.SAVEBtn, ElementType.Xpath);
			logs.logging("INFO", "Header Saved Successfully.");
			commonFunc.hardWait(3500);
			flag = webDB.waitForElement(PricelistPageLocators.ActiveButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PricelistPageLocators.ActiveButton, ElementType.Xpath);
//				flag = webDB.waitForElement(PricelistPageLocators.StopAlert, ElementType.Xpath);
//				if(flag) {
//					String Alert = webDB.getTextFromElement(PricelistPageLocators.StopAlert, ElementType.Xpath);
//					logs.logging("INFO", "STOP ALERT : " +Alert);
//					webDB.tearDown();
//				}else {
				commonFunc.checkRefreshCellsDisappear();
				logs.logging("INFO", "Header Post Icon Is Clicked");
			}

		}

		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Waiting for Approval Dialog Box
	 * 
	 * @author Durai
	 * @return boolean flag
	 */

	public boolean WaitingForApproval() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(PricelistPageLocators.PriceListWorkflowFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(PricelistPageLocators.PriceListWorkflowFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(PricelistPageLocators.PricelistForwardlov,
					commonFunc.getDataFromTestDataSheet("AshokValue"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(PricelistPageLocators.PriceListWorkflowFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(PricelistPageLocators.PricelistworkflowOK, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PricelistPageLocators.PricelistworkflowOK, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
//				flag = webDB.waitForElement(PurchaseOrderPageLocators.AddButtonPurchaseOrder, ElementType.Xpath);
//				if (flag) {
//					logs.logging("INFO", "Verified Navigate To PO Home Page");
//				}
			}
			assertTrue(flag);
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		return flag;
	}

	/**
	 * This Methods Is Used To Verify Forward Screen is Opening
	 * 
	 * @author Durai
	 * @return boolean flag
	 */
	public boolean Forwardapprovalscreen() {
		commonFunc.hardWait(1000);
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
			approvalFunc.navigateToApprovals();

		}
		return flag;
	}

	/**
	 * This Methods Is Used To Verify Approve the document
	 * 
	 * @author Durai
	 * @param Number
	 */
	public void filterDocumentsforPriceistApproval(String Number) {
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
					webDB.clickAnElement(ApprovalPageLocators.SearchBoxGoBtn, ElementType.Xpath);
					flag = webDB.waitForElement(ApprovalPageLocators.SearchTableResult, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Search Go Button Is Clicked");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Approve The Document
	 * 
	 * @author Durai
	 * @param Number
	 */
	public void verifyApproveWithDocNumber(String Number) {
		commonFunc.hardWait(1000);
		flag = webDB.waitForClickElement(PricelistPageLocators.PRLISTSelectCheckBox(Number), ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(PricelistPageLocators.PRLISTSelectCheckBox(Number), ElementType.Xpath);
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
}
