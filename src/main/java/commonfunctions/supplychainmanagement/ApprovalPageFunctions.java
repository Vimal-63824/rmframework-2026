package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks January 15, 2025
 */
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.ApprovalPageLocators;
import commonlocators.supplychainmanagement.OpenSalesOrderLocators;
import commonlocators.supplychainmanagement.SalesOrderLocators;
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
	 */
	public void navigateToApprovals() {
		flag = webDB.waitForElement(ApprovalPageLocators.UserNotifyIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ApprovalPageLocators.UserNotifyIcon, ElementType.Xpath);
			flag = webDB.waitForElement(ApprovalPageLocators.ApprovalNotifyOption, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ApprovalPageLocators.ApprovalNotifyOption, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(ApprovalPageLocators.FindSearchButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(ApprovalPageLocators.FindSearchButton, ElementType.Xpath);
					flag = webDB.waitForElementUntilGivenTime(ApprovalPageLocators.SearchBoxContainer,
							ElementType.Xpath, 45);
					if (flag)
						logs.logging("INFO", "Navigated to Doc Approvals page successfully");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Filter Documents For Approval
	 * 
	 * @author Deepan-GWL
	 * @param PONumber
	 */
	public void filterDocumentsforApproval(String Number) {
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
					commonFunc.hardWait(4000);
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
	 * Verify Validate Details PO Details
	 * 
	 * @author Deepan-GWL
	 * @param PONumber
	 * @param ExpCurrency
	 * @param POHType
	 * @param UOM
	 */
	public void verifyValidatedetailsPoDetails(String PONumber, String ExpCurrency, String POHType, String UOM) {
		commonFunc.hardWait(1000);
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
		Assert.assertTrue(flag);
	}

	/**
	 * Verify Navigate Back
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyNavigateback() {
		flag = webDB.waitForElement(ApprovalPageLocators.Backicon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ApprovalPageLocators.Backicon, ElementType.Xpath);
			flag = webDB.waitForElementUntilGivenTime(ApprovalPageLocators.SearchBoxContainer, ElementType.Xpath, 45);
			if (flag) {
				logs.logging("INFO", "Back Button Is Clicked Verified Navigate To Approval Table Page");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method is Used to Validate the Sales Order
	 *         Details In Document Approval Screen
	 * @param SONumber
	 * @param ExpLocation
	 * @param ExpUnit
	 * @param ExpCustId
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

					flag = webDB.waitForElement(SalesOrderLocators.HeaderLocationField, ElementType.Xpath);
					if (flag) {
						String LocId = webDB.getTextFromElement(SalesOrderLocators.HeaderLocationField,
								ElementType.Xpath);
						logs.logging("INFO", "GetText Location Id is :" + LocId);
						flag = LocId.equals(ExpLocation);
						if (flag) {
							logs.logging("INFO", "Verified Expected And Actual Location Id");
						} else {
							logs.logging("INFO", "Verification Failed, Expected :" + ExpLocation + "Actual :" + LocId);
						}
						flag = webDB.waitForElement(SalesOrderLocators.HeaderUnitField, ElementType.Xpath);
						if (flag) {
							String Unit = webDB.getTextFromElement(SalesOrderLocators.HeaderUnitField,
									ElementType.Xpath);
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
									verifyNavigateback();
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to select forwarder in WorkApproval and click Ok button
	 * 
	 * @author Sakthi-GWL
	 * @param user
	 */
	public void waitingForApprovalWithFordwarder(String user) {
		webDB.waitUntilElementToNotBeStale(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath);
		flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath, 15);
		if (flag) {
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.checkRefreshCellsDisappear();
			webDB.waitUntilElementToNotBeStale(CommonUiLocators.WorkFlow_Frwd_To_Emp, ElementType.Xpath);
			String GetText = webDB.getDomPropertyFromElement(CommonUiLocators.WorkFlow_Frwd_To_Emp, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("INFO", "Already Displayed Document Forwarder Person: " + GetText);
			if (!GetText.equals(user)) {
				commonFunc.searchAndSelectValueInFilters(CommonUiLocators.WorkAppoval_ForwardLovBtn, user);
				logs.logging("INFO", "Document Forwarder Person Selected: " + user);
				webDB.waitForElement(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath);
				webDB.switchToFrame(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath);
			}
			flag = webDB.waitForElement(CommonUiLocators.WorkAppoval_OkBtn, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(CommonUiLocators.WorkAppoval_OkBtn, ElementType.Xpath);
				flag = webDB.clickAnElement(CommonUiLocators.WorkAppoval_OkBtn, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
//				webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 15);
				if (!(webDB.verifyIsElementDisplayed(CommonUiLocators.WorkAppoval_OkBtn, ElementType.Xpath)
						&& webDB.verifyIsElementDisplayed(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath))) {
					logs.logging("INFO", "Workflow Approval Ok Button Clicked");
				}
				webDB.switchToDefaultContent();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To ExtractStocktransferDocNumber
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public boolean ExtractStocktransferDocNumber() {
		flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
		if (flag) {
			String text = webDB.getTextFromElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			logs.logging("INFO", "Get Text From Note Alert Is :" + text);
			String regex = "SOST-\\d{4}-\\d{7}";
			Pattern pattern = Pattern.compile(regex);
			Matcher SaleOrderDocNumber = pattern.matcher(text);
			if (SaleOrderDocNumber.find()) {
				String SONumber = SaleOrderDocNumber.group();
				VariableHandler.setSalesOrdertDocNumber(SONumber);
				logs.logging("INFO", "Sales Order number Is :" + VariableHandler.getSalesOrdertDocNumber());
			} else {
				logs.logging("INFO", "No match found for Sale Order Number in Extract Stock transfer.");
			}
		}
		return flag;
	}

	/**
	 * This Method is used to click check box of Doc Number and process button in
	 * Approval Screen
	 * 
	 * @author Sakthi-GWL
	 * @param Number
	 */
	public void clickCheckBoxForDocument(String Number) {
		int i = 1;
		commonFunc.hardWait(2000);
		a: do {
			flag = webDB.waitForClickElement(ApprovalPageLocators.POSelectCheckBox(Number), ElementType.Xpath);
			if (flag) {
				flag = webDB.isElementSelected(ApprovalPageLocators.POSelectCheckBox(Number), ElementType.Xpath);
				if (!flag) {
					webDB.moveToElement(ApprovalPageLocators.POSelectCheckBox(Number), ElementType.Xpath);
					webDB.clickAnElement(ApprovalPageLocators.POSelectCheckBox(Number), ElementType.Xpath);
					commonFunc.hardWait(3000);
					flag = webDB.isElementSelected(ApprovalPageLocators.POSelectCheckBox(Number), ElementType.Xpath);
				}
				if (flag) {
					logs.logging("INFO", "CheckBox Is Clicked ");
					flag = webDB.waitForClickElement(ApprovalPageLocators.ProcessButton, ElementType.Xpath);
					if (flag) {
						webDB.javaScriptClickAnElement(ApprovalPageLocators.ProcessButton, ElementType.Xpath);
						flag = webDB.waitForElement(ApprovalPageLocators.ApproveRadioBtn, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"CheckBox select for Document Number " + Number + " and Process button clicked");
							commonFunc.checkRefreshCellsDisappear();
							break a;
						} else {
							webDB.navigateToRefresh();
							filterDocumentsforApproval(Number);
							logs.logging("INFO", "Toast Message Not Displayed... Retrying.. ");
						}
					}
				}
			}
			i++;
		} while (i < 5);
		assertTrue(flag);
	}

	/**
	 * This Method is used to click Approve radio button with retrying if ajax or
	 * error toast displayed
	 * 
	 * @author Sakthi-GWL
	 * @param Number
	 */
	public void clickApproveRadioBtn(String Number) {
		int i = 1;
		a: do {
			flag = webDB.waitForElement(ApprovalPageLocators.ApproveRadioBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ApprovalPageLocators.ApproveRadioBtn, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				flag = webDB.verifyIsElementDisplayed(ApprovalPageLocators.AjaxMultiDocSelectWarning, ElementType.Xpath)
						|| webDB.verifyIsElementDisplayed(ApprovalPageLocators.ApproveErrorToast, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Ajax and Error Toast Displayed.. Retrying..." + i);

					webDB.navigateToRefresh();
					filterDocumentsforApproval(Number);
					clickCheckBoxForDocument(Number);
				} else {
					logs.logging("INFO", "Approve Radio Button Clicked");
					flag = true;
					break a;
				}
			}
			i++;
		} while ((i <= 5));
		assertTrue(flag);
	}

	/**
	 * Verify Approve PO Number
	 * 
	 * @author Deepan-GWL
	 * @param PONumber
	 */
	public void verifyApproveWithDocNumber(String Number) {
		int i = 1;
		logs.logging("INFO", "Trying to approval times.. " + i);
		a: do {
			clickCheckBoxForDocument(Number);
			clickApproveRadioBtn(Number);
			flag = webDB.waitForElement(ApprovalPageLocators.ApproveProcessButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Approval Process Button Is Displayed");
				webDB.clickAnElement(ApprovalPageLocators.ApproveProcessButton, ElementType.Xpath);
				flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 60);
				if (flag) {
					logs.logging("INFO", "Toast Message Displayed, Posted Successfully");
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.checkSuccessToastMessageDisappear();
					if (webDB.verifyIsElementDisplayed(ApprovalPageLocators.SearchButton, ElementType.Xpath)) {
						webDB.clickAnElement(ApprovalPageLocators.SearchButton, ElementType.Xpath);
					}
					break a;
				} else {
					webDB.navigateToRefresh();
					filterDocumentsforApproval(Number);
					logs.logging("INFO", "Toast Message Not Displayed... Retrying.. ");
				}
			}
			i++;
		} while ((i <= 3));
		assertTrue(flag);
	}

	/**
	 * This Method is used to click Return radio button with retrying if ajax or
	 * error toast displayed
	 * 
	 * @author Sakthi-GWL
	 * @param Number
	 */
	private void clickReturnRadioBtn(String Number) {
		int i = 1;
		a: do {
			flag = webDB.waitForElement(ApprovalPageLocators.ReturnRadioBtn, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(ApprovalPageLocators.ReturnRadioBtn, ElementType.Xpath);
				webDB.clickAnElement(ApprovalPageLocators.ReturnRadioBtn, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				flag = webDB.verifyIsElementDisplayed(ApprovalPageLocators.AjaxMultiDocSelectWarning, ElementType.Xpath)
						|| webDB.verifyIsElementDisplayed(ApprovalPageLocators.ApproveErrorToast, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Ajax and Error Toast Displayed.. Retrying..." + i);
					i++;
					webDB.navigateToRefresh();
					filterDocumentsforApproval(Number);
					clickCheckBoxForDocument(Number);
				} else {
					logs.logging("INFO", "Return Radio Button Clicked");
					flag = true;
					break a;
				}
			}
		} while ((i <= 5));
		assertTrue(flag);
	}

	/**
	 * Verify Return Doc Number
	 * 
	 * @author Sakthi-GWL
	 * @param DocNumber
	 */
	public void verifyReturnWithDocNumber(String DocNumber, String ReturnPerson) {
		int i = 1;
		logs.logging("INFO", "Trying to Return times.. " + i);
		a: do {
			clickCheckBoxForDocument(DocNumber);
			clickReturnRadioBtn(DocNumber);
			verifyReturnLOVSelect(ReturnPerson);
//			commonFunc.searchAndSelectValueInFilters(ApprovalPageLocators.ReturnPersonLovBtn, ReturnPerson);
			logs.logging("INFO", "For Return Employee Name Entered: " + ReturnPerson);
			flag = webDB.waitForClickElement(ApprovalPageLocators.ApproveProcessButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Return Process Button Is Displayed");
				webDB.moveToElement(ApprovalPageLocators.ApproveProcessButton, ElementType.Xpath);
				webDB.clickAnElement(ApprovalPageLocators.ApproveProcessButton, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Toast Message Displayed, Returned Successfully");
					break a;
				} else {
					i++;
					webDB.navigateToRefresh();
					filterDocumentsforApproval(DocNumber);
					logs.logging("INFO", "Toast Message Not Displayed... Retrying.. ");
				}
			}
		} while ((i <= 3));
		assertTrue(flag);
	}

	/**
	 * This method used to select return person in return lov
	 * 
	 * @author Sakthi-GWL
	 * @param user
	 */
	public void verifyReturnLOVSelect(String user) {
		flag = webDB.waitForClickElement(ApprovalPageLocators.ReturnPersonLovBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ApprovalPageLocators.ReturnPersonLovBtn, ElementType.Xpath);
			List<WebElement> searchBar = webDB.getDriver().findElements(By.xpath(CommonUiLocators.searchBar));
			a: for (int i = 0; i < searchBar.size(); i++) {
				WebElement element = searchBar.get(i);
				try {
					if (element.isDisplayed()) {
						element.clear();
						flag = commonFunc.fluentWaitClickElement(element);
						if (flag) {
							commonFunc.checkRefreshCellsDisappear();
							element.sendKeys(user);
							break a;
						} else {
							flag = false;
						}
					}
				} catch (Exception e) {
					logs.logging("INFO", "failed to enter search bar in return lov Exception is: " + e);
					flag = false;
				}
			}
			if (flag) {
				webDB.pressEnterKey();
				flag = webDB.waitForElement(CommonUiLocators.searchHighlightText(user), ElementType.Xpath);
				if (flag) {
					flag = webDB.clickAnElement(CommonUiLocators.searchHighlightText(user), ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Approval For Catalogue Price
	 * 
	 * @author Kowshik-RMITS
	 * @param WorkFlowType
	 */
	public void verifyWorkFlowTypeForCataloguePrice(String WorkFlowType) {
		flag = webDB.waitForElement(ApprovalPageLocators.WfTypeLovButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ApprovalPageLocators.WfTypeLovButton, ElementType.Xpath);
			flag = webDB.waitForElement(ApprovalPageLocators.SearchBoxWorkType, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked a WorkType SearchBox");
				flag = webDB.waitForElement(ApprovalPageLocators.SearchBoxWorkType, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(ApprovalPageLocators.SearchBoxWorkType, WorkFlowType, ElementType.Xpath);
					flag = webDB.waitForElement(ApprovalPageLocators.SalesCataogField, ElementType.Xpath);
					logs.logging("INFO", "Successfully Insereted a Catalog Work flow ");
					if (flag) {
						webDB.clickAnElement(ApprovalPageLocators.SalesCataogField, ElementType.Xpath);
						logs.logging("INFO", "Successfully Clicked a Sales Catalog Price");

						webDB.waitForElement(ApprovalPageLocators.SearchButtonWorkFLow, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Sucessfully Populated a Work Flow Type ");
							flag = webDB.waitForElement(ApprovalPageLocators.SearchButtonWorkFLow, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(ApprovalPageLocators.SearchButtonWorkFLow, ElementType.Xpath);
								logs.logging("INFO", "Successfully Clicked a Search Button");
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Work Flow Doc Type
	 * 
	 * @author Kowshik-RMITS
	 */
	public void verifyWorkFlowDocForCatalog() {
		flag = webDB.waitForElement(ApprovalPageLocators.VoucherDocNo, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(ApprovalPageLocators.VoucherDocNo, VariableHandler.getCatalogDocno(),
					ElementType.Xpath);
			webDB.waitForElement(ApprovalPageLocators.SearchButtonWorkFLow, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sucessfully Populated a Work Flow Type ");
				flag = webDB.waitForElement(ApprovalPageLocators.SearchButtonWorkFLow, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(ApprovalPageLocators.SearchButtonWorkFLow, ElementType.Xpath);
					logs.logging("INFO", "Successfully Clicked a Search Button");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to select forwarder in WorkApproval and click Ok button
	 * 
	 * @author Sakthi-GWL
	 * @param user
	 */
	public void waitingForApprovalWithFordwarderOSO(String user) {
		flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath, 5);
		if (flag) {
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(CommonUiLocators.WorkAppoval_ForwardLovBtn, user);
			webDB.switchToFrame(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.WorkAppoval_OkBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CommonUiLocators.WorkAppoval_OkBtn, ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(OpenSalesOrderLocators.OpenSO_CreatePlusIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Ok Button Is Clicked");
				}
//				commonFunc.hardWait(3000);
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Validate Details PO Details
	 * 
	 * @author Deepan-GWL
	 * @param PONumber
	 * @param ExpCurrency
	 * @param POHType
	 * @param UOM
	 */
	public void verifyValidatedetailsPoDetails(String PONumber, String ExpCurrency, String POHType) {
		commonFunc.hardWait(1000);
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
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used to select forwarder in WorkApproval and click Ok button
	 * 
	 * @author Vimal - RMITS
	 * @param user
	 */
	public void subcontractwaitingForApprovalWithFordwarder(String user) {
		flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath, 5);
		if (flag) {
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(CommonUiLocators.WorkAppoval_ForwardLovBtn, user);
			webDB.switchToFrame(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.WorkAppoval_OkBtn_SCO, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CommonUiLocators.WorkAppoval_OkBtn_SCO, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getScoPurchaseNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
				assertTrue(flag);

				commonFunc.checkSuccessToastMessageDisappear();
				commonFunc.hardWait(3000);
			}
		} else {
			VariableHandler.setApprovalFlag(flag);
			flag = true;
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To ExtractStocktransferDocNumber
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public boolean ExtractStocktransferDocNumber(String text) {
		logs.logging("INFO", "Get Text From Note Alert Is :" + text);
		String regex = "SOU\\d-\\d{4}-\\d{4}";
		Pattern pattern = Pattern.compile(regex);
		Matcher SaleOrderDocNumber = pattern.matcher(text);
		if (SaleOrderDocNumber.find()) {
			String SONumber = SaleOrderDocNumber.group();
			VariableHandler.setSalesOrdertDocNumber(SONumber);
			logs.logging("INFO", "Sales Order number Is :" + VariableHandler.getSalesOrdertDocNumber());
		}
		return flag;
	}

	/**
	 * This Methods Is Used To ExtractStocktransferDocNumber
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public boolean ExtractStocktransferDocNumberForLocalSupplier() {
		flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
		if (flag) {
			String text = webDB.getTextFromElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			logs.logging("INFO", "Get Text From Note Alert Is :" + text);
			// Extract the document number starting with SOT8-
			String regex = "SOT8-\\d{4}-\\d{4}";
			Pattern pattern = Pattern.compile(regex);
			Matcher SaleOrderDocNumber = pattern.matcher(text);
			if (SaleOrderDocNumber.find()) {
				String SONumber = SaleOrderDocNumber.group();
				VariableHandler.setSalesOrdertDocNumber(SONumber);
				logs.logging("INFO", "Sales Order Number Is :" + VariableHandler.getSalesOrdertDocNumber());
			} else {
				logs.logging("INFO", "No match found for Sale Order Number in Extract Stock transfer.");
			}
		}
		return flag;
	}
}
