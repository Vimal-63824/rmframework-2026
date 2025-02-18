package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
import static org.testng.Assert.assertTrue;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.GrnLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class GrnPageFunctions {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	static PurchaseOrderFunctions POfunc = new PurchaseOrderFunctions();
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();

	/**
	 * This Methods Is Used To Click Pending PO and Search PO Number in Header
	 * 
	 * @author PremKumar.K-RM IT
	 */
	public boolean clickPendingPoAndSearchPoNo() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(GrnLocators.ClickPendingButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Button Is Displayed");
			webDB.moveToElement(GrnLocators.ClickPendingButton, ElementType.Xpath);
			webDB.clickAnElement(GrnLocators.ClickPendingButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(GrnLocators.PendingPO, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending ButtonIs Clicked");
				webDB.clickAnElement(GrnLocators.PendingPO, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Pending PO Frame Is Displayed");
					webDB.switchToFrame(GrnLocators.PendingPOIframe, ElementType.Xpath);
					logs.logging("INFO", "Switched To Pending Po Frame");
					commonFunc.searchAndSelectValueInFilters(GrnLocators.PendingPOHeaderPONumberInput,
							VariableHandler.getPONumber());
					flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(GrnLocators.PendingPOIframe, ElementType.Xpath);
						flag = webDB.waitForElement(GrnLocators.PendingPOHeaderSearchButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Pending PO Header Search Button Is Displayed");
							webDB.clickAnElement(GrnLocators.PendingPOHeaderSearchButton, ElementType.Xpath);

							flag = webDB.waitForElement(GrnLocators.PendingPOSelectAllButton, ElementType.Xpath);
							webDB.switchToDefaultContent();
						}
					}
				}
			}

		}

		assertTrue(flag);
		return flag;
	}

	/**
	 * This Methods Is Used To Click Fully selected
	 * 
	 * @author PremKumar.K-RM IT
	 */

	public boolean ClickSelectAllButton() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending PO Iframe Is Displayed");
			webDB.switchToFrame(GrnLocators.PendingPOIframe, ElementType.Xpath);
			webDB.moveToElement(GrnLocators.PendingTable, ElementType.Xpath);
			webDB.moveToElement(GrnLocators.PendingPOSelectAllButton, ElementType.Xpath);
			logs.logging("INFO", "Switched To Frame Successfully");
			flag = webDB.waitForClickElement(GrnLocators.PendingPOSelectAllButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending PO SelectAll Button Displayed");
				commonFunc.hardWait(5000);
				webDB.clickAnElement(GrnLocators.PendingPOSelectAllButton, ElementType.Xpath);
				webDB.switchToDefaultContent();
			}
		}
		if (flag) {
			logs.logging("INFO", "PO Document Fully Selected");
		}
		return flag;
	}

	/**
	 * This Methods Is Used After select the document to create GRN
	 * 
	 * @author PremKumar.K-RM IT
	 */

	public boolean CreateGrn() {
		flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(GrnLocators.PendingPOIframe, ElementType.Xpath);
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(GrnLocators.PendingPOCreateGRN, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.PendingPOCreateGRN, ElementType.Xpath);
				logs.logging("INFO", "Click GRN Document");
				flag = webDB.waitForElement(GrnLocators.PendingPOClickSupplierBillnumber, ElementType.Xpath);
			}
		}
		return flag;
	}

	/**
	 * This Methods Is Used After create the GRN to Give Supplier Bill Number And DC
	 * Number
	 * 
	 * @author PremKumar.K-RM IT
	 */

	public boolean SupplierBillNumberAndDcNumber() {
		flag = webDB.waitForElement(GrnLocators.PendingPOClickSupplierBillnumber, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(GrnLocators.PendingPOClickSupplierBillnumber, VariableHandler.getPONumber(),
					ElementType.Xpath);
			flag = webDB.waitForElement(GrnLocators.PendingPOClickDCNumber, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Po Click DC Number Is Displayed");
				webDB.sendTextToAnElement(GrnLocators.PendingPOClickDCNumber, VariableHandler.getPONumber(),
						ElementType.Xpath);
				flag = webDB.waitForElement(GrnLocators.PendingPOClickOKButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Pending PO Click Ok Button Is Displayed");
					webDB.clickAnElement(GrnLocators.PendingPOClickOKButton, ElementType.Xpath);
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(GrnLocators.LotSerailButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Suplier Bill Number And DC Number Successfully Loaded");
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		return flag;
	}

	/**
	 * This Methods Is Used To Give Lot Number Details
	 * 
	 * @author PremKumar.K-RM IT
	 */

	public boolean AddLotNumber(String ITEM, String LotNumber) {
		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(GrnLocators.LotSerailButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Successfully Created GRN and Entered GRN Transaction Screen");
			commonFunc.hardWait(3000);
			webDB.clickAnElement(GrnLocators.LotSerailButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (flag) {
				logs.logging("INFO", "LOT/SERIAL Button Clicked");
				webDB.switchToFrame(GrnLocators.LotSerialIframe, ElementType.Xpath);
				flag = webDB.waitForElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
				webDB.clickAnElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				flag = webDB.waitForElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(GrnLocators.LotSerialItemLov, ITEM);
				commonFunc.checkRefreshCellsDisappear();
				if (flag) {
					logs.logging("INFO", "LOT item Successfully Added");
					webDB.switchToFrame(GrnLocators.LotSerialIframe, ElementType.Xpath);
					flag = webDB.waitForElement(GrnLocators.LotclickInput, ElementType.Xpath);
					if (flag) {
						// webDB.moveToElement(GrnLocators.LotNumberInput, ElementType.Xpath);
						webDB.clickAnElement(GrnLocators.LotclickInput, ElementType.Xpath);
						webDB.sendTextToAnElement(GrnLocators.LotNumberInput, LotNumber, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "LOT Number Loaded Successfully");
							flag = webDB.waitForElement(GrnLocators.LotSerialDate, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(GrnLocators.LotSerialDate, ElementType.Xpath);
								webDB.sendTextToAnElement(GrnLocators.ExpiryDate, GrnLocators.DateInput,
										ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Date Insert Successfully");
									flag = webDB.waitForElement(GrnLocators.LotSerialSave, ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(GrnLocators.LotSerialSave, ElementType.Xpath);
										if (flag) {
											logs.logging("INFO", "Lot Item Successfully Saved");
											commonFunc.hardWait(3000);
											if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon,
													ElementType.Xpath)) {
												webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
														ElementType.Xpath);
												commonFunc.hardWait(3000);
												flag = webDB.waitForElement(GrnLocators.ApportionLSQty,
														ElementType.Xpath);
												webDB.clickAnElement(GrnLocators.ApportionLSQty, ElementType.Xpath);
												webDB.switchToDefaultContent();
												if (flag) {
													logs.logging("INFO", "Apportion Lot Serial Qty Successfully");
												}
											}
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
	 * This Methods Is Used After Give all Details to Click overall save and Forward
	 * the Approval User
	 * 
	 * @author PremKumar.K-RM IT
	 */

	public boolean OverAllSaveAndPost() {
		commonFunc.hardWait(3000);
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());
			flag = webDB.waitForElement(GrnLocators.OverAllSave, ElementType.Xpath);
			webDB.clickAnElement(GrnLocators.OverAllSave, ElementType.Xpath);
			logs.logging("INFO", "Document Successfully Saved");
			commonFunc.hardWait(2000);
			if (flag) {
				flag = webDB.waitForClickElement(GrnLocators.PostButton, ElementType.Xpath);
				webDB.clickAnElement(GrnLocators.PostButton, ElementType.Xpath);
				logs.logging("INFO", "Document Successfully posted");
			}
		}
		return flag;
	}

	/**
	 * This Methods Is Used for Approve the Document
	 * 
	 * @author PremKumar.K-RM IT
	 */
	public boolean WaitingForApproval() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(GrnLocators.DocAprovalIfrmae, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ForwardApproval,
					commonFunc.getDataFromTestDataSheet("AshokValue"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(PurchaseOrderPageLocators.OkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseOrderPageLocators.OkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				commonFunc.hardWait(3000);
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getGrnDocNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getGrnDocNumber());

			}
			webDB.switchToDefaultContent();
			assertTrue(flag);
		}
		return flag;
	}

}
