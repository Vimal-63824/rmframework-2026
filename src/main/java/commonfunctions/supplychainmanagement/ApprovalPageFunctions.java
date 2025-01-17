package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks January 15, 2025
 */
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.supplychainmanagement.ApprovalPageLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class ApprovalPageFunctions {
	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private CommonFunctions commonFunc = new CommonFunctions();

	/**
	 * This method used to navigate Approval Page
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean navigateToApprovals() {
		flag = webDB.waitForElement(ApprovalPageLocators.UserNotifyIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ApprovalPageLocators.UserNotifyIcon, ElementType.Xpath);
			flag = webDB.waitForElement(ApprovalPageLocators.ApprovalNotifyOption, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ApprovalPageLocators.ApprovalNotifyOption, ElementType.Xpath);
				flag = webDB.waitForElementUntilGivenTime(ApprovalPageLocators.DocumentsTypeDropDown, ElementType.Xpath,
						45);
				if (flag)
					logs.logging("INFO", "Navigated to Doc Approvals page successfully");
			}
		}
		return flag;
	}

	/**
	 * Verify Ashok Account PO Approval
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 * @throws Exception
	 */
	public boolean verifyAshokAccountPOApproval() throws Exception {
		flag = commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
		if (flag) {
			flag = navigateToApprovals();
			if (flag) {
				flag = filterPoforApproval(VariableHandler.getPONumber());
				if (flag) {
					flag = verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
							webDB.getDataFromProperties("CURRENCYINR"), webDB.getDataFromProperties("POHTYPE"),
							webDB.getDataFromProperties("UOMNOS"));
					if (flag) {
						flag = verifyNavigateback();
						if (flag) {
							flag = filterPoforApproval(VariableHandler.getPONumber());
							if (flag) {
								flag = verifyApprovePoNumber(VariableHandler.getPONumber());
							}
						}
					}
				}
			}
		}
		return flag;

	}

	/**
	 * Filter Po For Approval
	 * 
	 * @author Deepan-GWL
	 * @param PONumber
	 * @return boolean flag
	 */
	public boolean filterPoforApproval(String PONumber) {
		flag = webDB.waitForElement(ApprovalPageLocators.SearchBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Search Box IS Displayed");
			webDB.clickAnElement(ApprovalPageLocators.SearchBoxContainer, ElementType.Xpath);
			webDB.clearTextField(ApprovalPageLocators.SearchBox, ElementType.Xpath);
			logs.logging("INFO", "Clear Text Search Box");
			webDB.sendTextToAnElement(ApprovalPageLocators.SearchBox, PONumber, ElementType.Xpath);
			logs.logging("INFO", "Entered PO Number Is: " + PONumber);
			flag = webDB.waitForClickElement(ApprovalPageLocators.SearchBoxGoBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ApprovalPageLocators.SearchBoxGoBtn, ElementType.Xpath);
				flag = webDB.waitForElement(ApprovalPageLocators.SearchTableResult, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Search Go Button Is Clicked");
				}
			}
		}
		return flag;
	}

	/**
	 * Verify VAlidate Details PO Details
	 * 
	 * @author Deepan-GWL
	 * @param PONumber
	 * @param ExpCurrency
	 * @param POHType
	 * @param UOM
	 * @return
	 */
	public boolean verifyValidatedetailsPoDetails(String PONumber, String ExpCurrency, String POHType, String UOM) {
		flag = webDB.waitForClickElement(ApprovalPageLocators.PODetailsInfoBtn(PONumber), ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(ApprovalPageLocators.PODetailsInfoBtn(PONumber), ElementType.Xpath);
			flag = webDB.waitForElement(ApprovalPageLocators.CURRENCY, ElementType.Id);
			if (flag) {
				logs.logging("INFO", "INFO Button Is Clicked ");
				String currency = webDB.getTextFromElement(ApprovalPageLocators.CURRENCY, ElementType.Id);
				logs.logging("INFO", "Get Text Currency Is: " + currency);
				flag = currency.equals(ExpCurrency);
				if (flag) {
					logs.logging("INFO", "Verified Expected Currency With Actual Currency");
					flag = webDB.waitForElement(ApprovalPageLocators.POTYPE, ElementType.Id);
					if (flag) {
						String Potype = webDB.getTextFromElement(ApprovalPageLocators.POTYPE, ElementType.Id);
						logs.logging("INFO", "Get Text Po Type Is: " + Potype);
						flag = Potype.equals(POHType);
						if (flag) {
							logs.logging("INFO", "Verified Expected PO Type With Actual PO Type");
							flag = webDB.waitForElement(ApprovalPageLocators.UOMTYPE, ElementType.Xpath);
							if (flag) {
								String Uomtype = webDB.getTextFromElement(ApprovalPageLocators.UOMTYPE,
										ElementType.Xpath);
								logs.logging("INFO", "Get Text UOM Type Is: " + Uomtype);
								flag = Uomtype.equals(UOM);
								if (flag) {
									logs.logging("INFO", "Verified Actual And Expected UOM");
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
	 * Verify Navigate Back
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyNavigateback() {
		flag = webDB.waitForElement(ApprovalPageLocators.BackButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ApprovalPageLocators.BackButton, ElementType.Xpath);
			flag = webDB.waitForElementUntilGivenTime(ApprovalPageLocators.DocumentsTypeDropDown, ElementType.Xpath,
					45);
			if (flag) {
				logs.logging("INFO", "Back Button Is Clicked Verified Navigate To Approval Table Page");
			}
		}
		return flag;
	}

	/**
	 * Verify Approve PO Number
	 * 
	 * @author Deepan-GWL
	 * @param PONumber
	 * @return boolean flag
	 */
	public boolean verifyApprovePoNumber(String PONumber) {
		flag = webDB.waitForClickElement(ApprovalPageLocators.POSelectCheckBox(PONumber), ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(ApprovalPageLocators.POSelectCheckBox(PONumber), ElementType.Xpath);
			flag = webDB.waitForClickElement(ApprovalPageLocators.ProcessButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "CheckBox Is Clicked And Process Button IS Displayed");
				webDB.javaScriptClickAnElement(ApprovalPageLocators.ProcessButton, ElementType.Xpath);
				flag = webDB.waitForElement(ApprovalPageLocators.ApproveRadioBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "CheckBox select for purchase ID " + PONumber + " and Process button clicked");
					commonFunc.checkRefreshCellsDisappear();
					webDB.clickAnElement(ApprovalPageLocators.ApproveRadioBtn, ElementType.Xpath);
					flag = webDB.waitForElement(ApprovalPageLocators.ApproveProcessButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Approval Process Button IS Displayed");
						webDB.clickAnElement(ApprovalPageLocators.ApproveProcessButton, ElementType.Xpath);
					}
				}
			}
		}
		return flag;
	}

	/**
	 * Verify Ashok Account PO Approval
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 * @throws Exception
	 */
	public boolean verifyAshokAccountPOApprovalWithMaterial() throws Exception {
		flag = commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
		if (flag) {
			flag = navigateToApprovals();
			if (flag) {
				flag = filterPoforApproval(VariableHandler.getPONumber());
				if (flag) {
					flag = verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
							webDB.getDataFromProperties("CURRENCYINR"), webDB.getDataFromProperties("MaterialType"),
							webDB.getDataFromProperties("UOMNOS"));
					if (flag) {
						flag = verifyNavigateback();
						if (flag) {
							flag = filterPoforApproval(VariableHandler.getPONumber());
							if (flag) {
								flag = verifyApprovePoNumber(VariableHandler.getPONumber());
							}
						}
					}
				}
			}
		}
		return flag;

	}

	/**
	 * Verify Ashok Account PO Approval
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 * @throws Exception
	 */
	public boolean verifyAshokAccountPOApprovalWithService() throws Exception {
		flag = commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
		if (flag) {
			flag = navigateToApprovals();
			if (flag) {
				flag = filterPoforApproval(VariableHandler.getPONumber());
				if (flag) {
					flag = verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
							webDB.getDataFromProperties("CURRENCYINR"), webDB.getDataFromProperties("ServiceType"),
							webDB.getDataFromProperties("UOMNOS"));
					if (flag) {
						flag = verifyNavigateback();
						if (flag) {
							flag = filterPoforApproval(VariableHandler.getPONumber());
							if (flag) {
								flag = verifyApprovePoNumber(VariableHandler.getPONumber());
							}
						}
					}
				}
			}
		}
		return flag;

	}

}
