package commonfunctions.supplychainmanagement;

/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks March 04, 2025
 */
import static org.testng.Assert.assertTrue;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.qualitycontrol.InspectionDocumentsLocators;
import commonlocators.supplychainmanagement.MaterialIssuanceVoucherLocators;
import commonlocators.supplychainmanagement.MaterialReceiptVoucherLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class MaterialReceiptVoucherFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	private static StocksPageFunctions stockPageFunc = new StocksPageFunctions();
	private static StockTransactionPageFunctions stockTransactionPageFunc = new StockTransactionPageFunctions();
	DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();

	/**
	 * Verify Pending Inspection Documents
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 */
	public void verifyPendingMRV(String DocNo) {
		flag = webDB.waitForElement(CommonUiLocators.PendingReceiptVoucherButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Receipt Voucher Button Is Displayed");
			webDB.clickAnElement(CommonUiLocators.PendingReceiptVoucherButton, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.PendingIssuance, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Insurance IS Displayed");
				webDB.clickAnElement(MaterialReceiptVoucherLocators.PendingIssuance, ElementType.Xpath);
				flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Mrv Frame Is Displayed");
					webDB.switchToFrame(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
					commonFunc.hardWait(3000);
					flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MRVSearchButton, ElementType.Xpath);
					if (flag) {
						webDB.moveToElement(MaterialReceiptVoucherLocators.AllCheckBox, ElementType.Xpath);
						webDB.clickAnElement(MaterialReceiptVoucherLocators.AllCheckBox, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						webDB.moveToElement(MaterialReceiptVoucherLocators.AllCheckBox, ElementType.Xpath);
						webDB.clickAnElement(MaterialReceiptVoucherLocators.AllCheckBox, ElementType.Xpath);
						logs.logging("INFO", "Inspection Pending Icon IS Clicked");
						commonFunc.clearFiltersInFindPage();
						commonFunc.searchAndSelectValueInFilters(MaterialReceiptVoucherLocators.VoucherNo, DocNo);
						webDB.switchToFrame(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
						flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MRVSearchButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Go Button Is Displayed");
							webDB.clickAnElement(MaterialReceiptVoucherLocators.MRVSearchButton, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							flag = webDB.waitForElement(MaterialReceiptVoucherLocators.AllCheckBox, ElementType.Xpath);
							if (flag) {
								commonFunc.hardWait(3000);
								logs.logging("INFO", "Mrv CheckBox Is Displayed");
								webDB.moveToElement(MaterialReceiptVoucherLocators.AllCheckBox, ElementType.Xpath);
								webDB.clickAnElement(MaterialReceiptVoucherLocators.AllCheckBox, ElementType.Xpath);
								flag = webDB.waitForElement(MaterialReceiptVoucherLocators.CreateMrvBtn,
										ElementType.Xpath);
								if (flag) {
									commonFunc.hardWait(2000);
									logs.logging("INFO", "Create Qc Doc Is Displayed");
									webDB.clickAnElement(MaterialReceiptVoucherLocators.CreateMrvBtn,
											ElementType.Xpath);
									webDB.switchToDefaultContent();
									flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MRVNO,
											ElementType.Xpath);
									storeMRVDocumentsNumber();
									if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
										webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
												ElementType.Xpath);
									}
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
	 * This Method Used To Verify Store MRV Documents Number
	 * 
	 * @author Deepan-GWL
	 */
	public void storeMRVDocumentsNumber() {
		String Number = webDB.getTextFromElement(MaterialReceiptVoucherLocators.MRVNO, ElementType.Xpath);
		VariableHandler.setMrvNumber(Number);
	}

	/**
	 * This Method Used To Verify Receipt Locators And Put Away
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyReceiptLocatorsAndPutAway() {
		commonFunc.checkSuccessToastMessageDisappear();
		if (webDB.verifyIsElementDisplayed(MaterialReceiptVoucherLocators.RecptorLocators, ElementType.Xpath)) {
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.RecptorLocators, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Receptor Locators Is Displayed");
				webDB.moveToElement(MaterialReceiptVoucherLocators.RecptorLocators, ElementType.Xpath);
				webDB.clickAnElement(MaterialReceiptVoucherLocators.RecptorLocators, ElementType.Xpath);
				flag = webDB.waitForElement(MaterialReceiptVoucherLocators.TransferFrame, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Transfer Frame Is Displayed");
					webDB.switchToFrame(MaterialReceiptVoucherLocators.TransferFrame, ElementType.Xpath);
					flag = webDB.waitForElement(MaterialReceiptVoucherLocators.PutAway, ElementType.Xpath);
					if (flag) {
						webDB.moveToElement(MaterialReceiptVoucherLocators.PutAway, ElementType.Xpath);
						webDB.clickAnElement(MaterialReceiptVoucherLocators.PutAway, ElementType.Xpath);
						if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
						}
						webDB.switchToDefaultContent();
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(MaterialReceiptVoucherLocators.CloseIcon, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Put Amay Is Clicked");
							webDB.clickAnElement(MaterialReceiptVoucherLocators.CloseIcon, ElementType.Xpath);
							flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MRVNO, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Location Id Is Displayed");
							}
						}
					}
				}
			}
		} else {
			flag = !webDB.verifyIsElementDisplayed(MaterialReceiptVoucherLocators.RecptorLocators, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Receipt Locators Button Not Display for the Process...");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Click Tick And Post Icon
	 * 
	 * @author Deepan-GWL
	 */
	public void clickTickAndPostIcon() {
		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.HeaderTickIcon, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header Tick Icon Is Displayed");
			webDB.moveToElement(MaterialReceiptVoucherLocators.HeaderTickIcon, ElementType.Xpath);
			webDB.clickAnElement(MaterialReceiptVoucherLocators.HeaderTickIcon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForClickElement(MaterialReceiptVoucherLocators.HeaderPostIcon, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(MaterialReceiptVoucherLocators.HeaderPostIcon, ElementType.Xpath);
				logs.logging("INFO", "Header Post Icon Is Clicked");
				flag = (webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 30)
						|| webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath));
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Validate Inspection
	 * 
	 * @author Deepan-GWL
	 * @param MrvNo
	 * @param ExpectedStatus
	 */
	public void verifyValidateMaterialReceiptDocumentsStatus(String MrvNo, String ExpectedStatus) {
		commonFunc.searchAndSelectValueInFilters(MaterialReceiptVoucherLocators.VoucherNumberLovButton, MrvNo);
		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.SearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialReceiptVoucherLocators.SearchButton, ElementType.Xpath);
			logs.logging("INFO", "Search Button Is Clicked");
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MaterialReceiptStatusTableText(MrvNo),
					ElementType.Xpath);
			if (flag) {
				String Status = webDB.getTextFromElement(
						MaterialReceiptVoucherLocators.MaterialReceiptStatusTableText(MrvNo), ElementType.Xpath);
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
	 * This Method Used To Verify Post Material Receipt
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPostMaterialReceipt() {
		commonFunc.navigateSetupInventoryPage();
		flag = autoCreateMRVCheckboxIsSelected();
		commonFunc.navigateToMaterialReceiptVoucher();
		if (flag) {
			verifyOpenAlreadyCreatedMRVDocuments(VariableHandler.getGrnDocNumber());
		} else {
			verifyPendingMRV(VariableHandler.getGrnDocNumber());
		}
		verifyReceiptLocatorsAndPutAway();
		clickTickAndPostIcon();
		verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
				commonFunc.getDataFromTestDataSheet("IssuedStatus"));
	}

	/**
	 * This Method used to click Pending Issuance Button
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickPendingInssuanceBtn() {
		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.PendingIssuance, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialReceiptVoucherLocators.PendingIssuance, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to select voucher No and click create receipt button
	 * 
	 * @author Sakthi-GWL
	 * @param VoucherNo
	 */
	public void selectVoucherNoAndClickCreateReceipt(String VoucherNo) {
		switchMRVPendingIframe();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(7000);
		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MRVCheckBox(VoucherNo), ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Search Go Button Clicked and Filtered with " + VoucherNo);
			webDB.clickAnElement(MaterialReceiptVoucherLocators.MRVCheckBox(VoucherNo), ElementType.Xpath);
			webDB.waitForClickElement(MaterialReceiptVoucherLocators.CreateMrvBtn, ElementType.Xpath);
			flag = webDB.isElementEnabled(MaterialReceiptVoucherLocators.CreateMrvBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(MaterialReceiptVoucherLocators.CreateMrvBtn, ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(MaterialReceiptVoucherLocators.LocationId, ElementType.Xpath);
				if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				if (flag) {
					storeMRVDocumentsNumber();
					logs.logging("INFO", "Create Qc Doc is clicked");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method IS Used To Verify Post Material Request From MIV
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPostMaterialRequestFromMiv() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSetupInventoryPage();
		boolean createflag = autoCreateMRVCheckboxIsSelected();
		boolean postflag = autoPostMRVCheckboxIsSelected();
		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
//		commonFunc.navigateToMaterialReceiptVoucher();
//
//		if (createflag && !postflag) {
//			verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getMIVNumber());
//			createJournalAndViewJournal();
//			verifyTableDataMiv(webDB.getDataFromProperties("MRTMRVSheet"));
//			clickTickAndPostIcon();
//		} else if (!createflag && !postflag) {
//			verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
//			createJournalAndViewJournal();
//			verifyTableDataMiv(webDB.getDataFromProperties("MRTMRVSheet"));
//			clickTickAndPostIcon();
//		} else if (createflag && postflag) {
//
//		} else if (!createflag && postflag) {
//			verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
//		}
//
//		verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
//				commonFunc.getDataFromTestDataSheet("IssuedStatus"));
//		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryStocksTab();
		verifyAfterStockDataUpdation(commonFunc.getDataFromTestDataSheet("FGWareHouse"),
				webDB.getDataFromProperties("MRTMRVSheet"));
		commonFunc.logoutUser();
		stockPageFunc.validateStockCount(webDB.getDataFromProperties("MRTMRVSheet"));
		stockTransactionPageFunc.validateStockTransaction();
	}

	/**
	 * This Method Is Used To Verify After Stock Data Updation
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAfterStockDataUpdation(String WareHouse, String Sheet) {
		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, Sheet);
		for (int i = 1; i < itemmultiple.length; i++) {
			stockPageFunc.getItemAfterStockValue(itemmultiple[i][0], WareHouse);
			stockPageFunc.removeFilterStock();
			stockPageFunc.getItemAfterStockValue(itemmultiple[i][0], itemmultiple[i][1]);
			stockPageFunc.removeFilterStock();
			stockPageFunc.getItemAfterStockCost(itemmultiple[i][0], WareHouse);
			stockPageFunc.removeFilterStock();
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkRefreshCellsDisappear();
			if (VariableHandler.getAfterStockCost().get(itemmultiple[i][0])
					.equals(VariableHandler.getStockCost().get(itemmultiple[i][0]))) {
				logs.logging("INFO", "Verified Stock Cost Of Both WareHouse With Item " + itemmultiple[i][0]);
			}
		}
	}

	/**
	 * This Method used to switch pending MRV frame
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchMRVPendingIframe() {
		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
			flag = webDB.waitForClickElement(InspectionDocumentsLocators.PendingSearchField, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to search with voucher no in Pending MRV
	 * 
	 * @author Sakthi-GWL
	 * @param VoucherNo
	 */
	public void searchWithVoucherNumber(String VoucherNo) {
		switchMRVPendingIframe();
		commonFunc.hardWait(2000);
		logs.logging("INFO", "Material Receipt Voucher Frame Switched");
		webDB.sendTextToAnElement(InspectionDocumentsLocators.PendingSearchField, VoucherNo, ElementType.Xpath);
		flag = webDB.waitForClickElement(InspectionDocumentsLocators.GoButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(InspectionDocumentsLocators.GoButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MRVCheckBox(VoucherNo), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Go Button Clicked and Filtered with " + VoucherNo);
			}
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * This Method used to validate Process Recipient Warehouse with Completion
	 * Production Order No
	 * 
	 * @author Sakthi-GWL
	 * @param CompProOdrNo
	 * @param Process
	 * @param RecipientWH
	 */
	public void validateProcessRecipientWarehouseWithCompletionProdNo(String CompProOdrNo, String Process,
			String RecipientWH) {
		switchMRVPendingIframe();
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.PendingCreateMRV_TableProcess(CompProOdrNo),
				ElementType.Xpath);
		if (flag) {
			String DisplayProcess = webDB.getTextFromElement(
					MaterialReceiptVoucherLocators.PendingCreateMRV_TableProcess(CompProOdrNo), ElementType.Xpath);
			flag = DisplayProcess.equalsIgnoreCase(Process);
			logs.logging("INFO", "Expected Process: " + Process);
			logs.logging("INFO", "Displayed Status: " + DisplayProcess);
			if (flag) {
				logs.logging("INFO", "Verified Process Displayed in Create MRV Screen");
				flag = webDB.waitForElement(
						MaterialReceiptVoucherLocators.PendingCreateMRV_TableRecipientWH(CompProOdrNo),
						ElementType.Xpath);
				if (flag) {
					String DisplayWarehouse = webDB.getTextFromElement(
							MaterialReceiptVoucherLocators.PendingCreateMRV_TableRecipientWH(CompProOdrNo),
							ElementType.Xpath);
					flag = DisplayWarehouse.equalsIgnoreCase(RecipientWH);
					logs.logging("INFO", "Expected Consumption Warehouse: " + RecipientWH);
					logs.logging("INFO", "Displayed Consumption Warehouse: " + DisplayWarehouse);
					if (flag) {
						logs.logging("INFO", "Verified Recipient Warehouse Displayed in Create MRV Screen");
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Data MIV
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyTableDataMiv(String Sheet) {
		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, Sheet);
		for (int i = 1; i < itemmultiple.length; i++) {
			List<WebElement> TableList = webDB.getDriver()
					.findElements(By.xpath(MaterialReceiptVoucherLocators.ListItemTable));
			if (flag) {
				for (int j = 1; j <= TableList.size(); j++) {
					String ItemText = webDB.getTextFromElement(MaterialReceiptVoucherLocators.ItemTable(j),
							ElementType.Xpath);
					String ItemCost = webDB.getTextFromElement(MaterialReceiptVoucherLocators.UnitcostTable(j),
							ElementType.Xpath);
					if (ItemText.equals(itemmultiple[i][0])) {
						if (ItemCost.equals(VariableHandler.getStockCost().get(itemmultiple[i][0]))) {
							logs.logging("INFO", "Verified Item Cost With Item With Table Data");
							flag = true;
							break;
						} else {
							flag = false;
						}
					} else {
						flag = false;
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Create Journal And View Journal
	 * 
	 * @author Deepan-GWL
	 */
	public void createJournalAndViewJournal() {
		commonFunc.hardWait(3000);
		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.CreateJournal, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Create Journal Button Is Displayed");
			webDB.clickAnElement(MaterialReceiptVoucherLocators.CreateJournal, ElementType.Xpath);
			commonFunc.hardWait(3000);
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.ViewJournal, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "View Journal Is Displayed");
				webDB.clickAnElement(MaterialReceiptVoucherLocators.ViewJournal, ElementType.Xpath);
				flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.ViewJournalFrame, ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(MaterialIssuanceVoucherLocators.ViewJournalFrame, ElementType.Xpath);
					flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.DebitTable, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Debit Table Is Displayed");
						webDB.waitForElement(MaterialIssuanceVoucherLocators.DebitTable, ElementType.Xpath);
						String DebitValue = webDB.getTextFromElement(MaterialIssuanceVoucherLocators.DebitTable,
								ElementType.Xpath);
						String CreditValue = webDB.getTextFromElement(MaterialIssuanceVoucherLocators.CreditTable,
								ElementType.Xpath);
						logs.logging("INFO", "Debit Value Is: " + DebitValue);
						logs.logging("INFO", "Credit Value Is: " + CreditValue);
						flag = DebitValue.equals(CreditValue);
						if (flag) {
							logs.logging("INFO", "Verified Credit Value With Debit Value");
							String Credit = CreditValue.replace(",", "");
							flag = Credit.contains(VariableHandler.getTotalLineAmount().toString());
							if (flag) {
								logs.logging("INFO", "Verified Credit Value With Amount Calculated");
								webDB.switchToDefaultContent();
								flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.CloseJournal,
										ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Close Journal Button Is Displayed");
									webDB.clickAnElement(MaterialIssuanceVoucherLocators.CloseJournal,
											ElementType.Xpath);
									flag = webDB.waitForElement(MaterialReceiptVoucherLocators.LocationId,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Journal Is Closed");
									}
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
	 * Verify Pending Inspection Documents
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 */
	public void verifyPendingMRVUsingMIV(String DocNo) {
		flag = webDB.waitForElement(CommonUiLocators.PendingReceiptVoucherButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Receipt Voucher Button Is Displayed");
			webDB.moveToElement(CommonUiLocators.PendingReceiptVoucherButton, ElementType.Xpath);
			webDB.clickAnElement(CommonUiLocators.PendingReceiptVoucherButton, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.PendingIssuance, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Insurance Is Displayed");
				webDB.moveToElement(MaterialReceiptVoucherLocators.PendingIssuance, ElementType.Xpath);
				webDB.clickAnElement(MaterialReceiptVoucherLocators.PendingIssuance, ElementType.Xpath);
				flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Mrv Frame Is Displayed");
					webDB.switchToFrame(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
					webDB.waitForElement(MaterialReceiptVoucherLocators.MRVSearchButton, ElementType.Xpath);
					webDB.moveToElement(MaterialReceiptVoucherLocators.Pending_TableShowMyRecordBtn, ElementType.Xpath);
					webDB.clickAnElement(MaterialReceiptVoucherLocators.Pending_TableShowMyRecordBtn,
							ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(3000);
					flag = webDB.verifyIsElementDisplayed(MaterialReceiptVoucherLocators.Pending_TableNoDataSelected,
							ElementType.Xpath);
					if (!flag) {
						webDB.waitForElementUntilGivenTime(
								MaterialReceiptVoucherLocators.Pending_TableSelectAllCheckBox, ElementType.Xpath, 5);
						if (webDB.isElementSelected(MaterialReceiptVoucherLocators.Pending_TableSelectAllCheckBox,
								ElementType.Xpath)) {
							webDB.moveToElement(MaterialReceiptVoucherLocators.Pending_TableSelectAllCheckBox,
									ElementType.Xpath);
							webDB.clickAnElement(MaterialReceiptVoucherLocators.Pending_TableSelectAllCheckBox,
									ElementType.Xpath);
						}
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(3000);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.verifyIsElementDisplayed(
								MaterialReceiptVoucherLocators.Pending_TableNoDataSelected, ElementType.Xpath);
					}
					if (flag) {
						webDB.moveToElement(MaterialReceiptVoucherLocators.Pending_TableShowMyRecordBtnClicked,
								ElementType.Xpath);
						webDB.clickAnElement(MaterialReceiptVoucherLocators.Pending_TableShowMyRecordBtnClicked,
								ElementType.Xpath);
						commonFunc.clearFiltersInFindPage();
						commonFunc.searchAndSelectValueInFilters(MaterialReceiptVoucherLocators.MIVNumberLovButton,
								DocNo);
						flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
						if (flag) {
							webDB.switchToFrame(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
							flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MRVSearchButton,
									ElementType.Xpath);
							if (flag) {
								webDB.moveToElement(MaterialReceiptVoucherLocators.MRVSearchButton, ElementType.Xpath);
								webDB.clickAnElement(MaterialReceiptVoucherLocators.MRVSearchButton, ElementType.Xpath);
								commonFunc.checkRefreshCellsDisappear();
								flag = webDB.waitForElement(MaterialReceiptVoucherLocators.AllCheckBox,
										ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Mrv CheckBox Is Displayed");
									webDB.moveToElement(MaterialReceiptVoucherLocators.AllCheckBox, ElementType.Xpath);
									webDB.clickAnElement(MaterialReceiptVoucherLocators.AllCheckBox, ElementType.Xpath);
									flag = webDB.waitForElement(MaterialReceiptVoucherLocators.CreateMrvBtn,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Create Qc Doc Is Displayed");
										webDB.clickAnElement(MaterialReceiptVoucherLocators.CreateMrvBtn,
												ElementType.Xpath);
										webDB.switchToDefaultContent();
										flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MRVNO,
												ElementType.Xpath);
										storeMRVDocumentsNumber();
										if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon,
												ElementType.Xpath)) {
											webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
													ElementType.Xpath);
										}
									}
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
	 * This Methods Is Used To Create MRV Document from Pending Issuance Voucher
	 * From GRN Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToCreateMrvDocumentFromPendingMivWithGrnDocument() {
		flag = webDB.waitForElement(CommonUiLocators.PendingReceiptVoucherButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Receipt Voucher Button Is Displayed");
			webDB.clickAnElement(CommonUiLocators.PendingReceiptVoucherButton, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.PendingIssuance, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Insurance IS Displayed");
				webDB.clickAnElement(MaterialReceiptVoucherLocators.PendingIssuance, ElementType.Xpath);
				flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Mrv Frame Is Displayed");
					webDB.switchToFrame(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
					flag = webDB.waitForElement(MaterialReceiptVoucherLocators.VoucherNoLov, ElementType.Xpath);
					if (flag) {
						commonFunc.searchAndSelectValueInFilters(MaterialReceiptVoucherLocators.VoucherNoLov,
								VariableHandler.getGrnDocNumber());
						webDB.switchToFrame(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
						flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvPendingVoucherSearchBtn,
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(MaterialReceiptVoucherLocators.MrvPendingVoucherSearchBtn,
									ElementType.Xpath);
							commonFunc.hardWait(4000);
							commonFunc.checkRefreshCellsDisappear();
							flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvSelectAllBtn,
									ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(MaterialReceiptVoucherLocators.MrvSelectAllBtn, ElementType.Xpath);
								commonFunc.hardWait(4000);
								commonFunc.checkRefreshCellsDisappear();
								flag = webDB.waitForElement(MaterialReceiptVoucherLocators.CreateMrvBtn,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(MaterialReceiptVoucherLocators.CreateMrvBtn,
											ElementType.Xpath);
									webDB.switchToDefaultContent();
									flag = webDB.waitForElement(
											MaterialReceiptVoucherLocators.MrvDocumentNumberToastAlert,
											ElementType.Xpath);
									if (flag) {
										String MRVDocumentNumber = webDB.getTextFromElement(
												MaterialReceiptVoucherLocators.MrvDocumentNumberToastAlert,
												ElementType.Xpath);
										VariableHandler.setMRVDocumentNumber(MRVDocumentNumber);
										logs.logging("INFO", "MRV Toast Alert:" + MRVDocumentNumber);
									}
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
	 * This Methods Is Used To Post With or Without Locator Item
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void verifyToPostWithOrWithoutLocator() {
		commonFunc.checkRefreshCellsDisappear();
//		if (webDB.waitForElementUntilGivenTime(MaterialReceiptVoucherLocators.ReceiptLocatorfield, ElementType.Xpath,
//				5)) {
		if (webDB.verifyIsElementDisplayed(MaterialReceiptVoucherLocators.ReceiptLocatorfield, ElementType.Xpath)) {
//			webDB.verifyIsElementDisplayed(MaterialReceiptVoucherLocators.ReceiptLocatorfield, ElementType.Xpath);
			verifyReceiptLocatorsAndPutAway();
		} else {
			System.out.println("Non Locator Item");
		}

		clickTickAndPostIcon();

	}

	/**
	 * This Methods Is To Post MRV Document From Pending Issuance Voucher for With
	 * And Without Locator
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyPostMaterialReceiptWithAndWithoutLocator() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToMaterialReceiptVoucherAdminUser();
		verifyToCreateMrvDocumentFromPendingMivWithGrnDocument();
		getMaterialReceiptVoucherNumberFromToastMessage();
		verifyToPostWithOrWithoutLocator();
		try {
			String alertText = VariableHandler.getMRVDocumentNumber();
			System.out.println("Alert text: " + alertText);
			String pattern = "MV100/\\d{5}/\\d{2}-\\d{2}\\s*to\\s*MV100/\\d{5}/\\d{2}-\\d{2}";
			if (alertText.matches(".*" + pattern + ".*")) {
				System.out.println("Specific receipt document alert found, proceeding with the process.");
				searchAndSelectLovForMrvDocumentFindPage();
				commonFunc.logoutUser();

			} else {
				System.out.println("Other alert detected, no action taken.");
				commonFunc.logoutUser();
			}
		} catch (Exception e) {
			System.out.println("No alert appeared or other error: " + e.getMessage());
		}
	}

	/**
	 * This Methods Is To Extract the Voucher Number
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void getMaterialReceiptVoucherNumberFromToastMessage() {
		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvDocumentNumberToastAlert, ElementType.Xpath);
		if (flag) {
			String MRVDocumentNumber = webDB
					.getTextFromElement(MaterialReceiptVoucherLocators.MrvDocumentNumberToastAlert, ElementType.Xpath);
			VariableHandler.setMRVDocumentNumber(MRVDocumentNumber);
			logs.logging("INFO", "MRV Toast Alert:" + MRVDocumentNumber);
			// Updated regex to match MV100 pattern
			String regex = "MV100/\\d{5}/\\d{2}-\\d{2}";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(MRVDocumentNumber);
			ArrayList<String> documentNumbers = new ArrayList<>();
			while (matcher.find()) {
				documentNumbers.add(matcher.group());
			}
			if (documentNumbers.size() == 2) {
				String fromDocument = documentNumbers.get(0);
				String toDocument = documentNumbers.get(1);
				System.out.println("From Document: " + fromDocument);
				System.out.println("To Document: " + toDocument);
				String fromNum = fromDocument.split("/")[1];
				String toNum = toDocument.split("/")[1];
				// Convert the document numbers to integers to calculate the range
				int startNum = Integer.parseInt(fromNum);
				int endNum = Integer.parseInt(toNum);
				ArrayList<String> allDocumentNumbers = new ArrayList<>();
				// Generate the list of document numbers in the range
				for (int i = startNum; i <= endNum; i++) {
					String generatedDocumentNumber = "MV100/" + String.format("%05d", i) + "/25-26";
					allDocumentNumbers.add(generatedDocumentNumber);
				}
				// Set the generated document numbers for further use
				VariableHandler.setAllDocumentNumber(allDocumentNumbers);
				System.out.println("Generated Document Numbers:");
				for (String documentNumber : allDocumentNumbers) {
					System.out.println(documentNumber);
				}
			} else {
				System.out.println("Invalid format, unable to extract From and To document numbers.");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is To Select Search And Select LOV for Find Page
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void searchAndSelectLovForMrvDocumentFindPage() {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		ArrayList<String> allDocumentNumbers = VariableHandler.getAllDocumentNumber();
		for (int i = 1; i < allDocumentNumbers.size(); i++) {
			String MRVdocumentNumber = allDocumentNumbers.get(i);
			commonFunc.hardWait(3000);
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvDocLovBtn, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(MaterialReceiptVoucherLocators.MrvDocLovBtn,
						MRVdocumentNumber);
				logs.logging("INFO", "MRV Document Number Is:" + MRVdocumentNumber);
				flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvDocNumberSearchBtn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(MaterialReceiptVoucherLocators.MrvDocNumberSearchBtn, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(3000);
					flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvDocNumberDrillDown,
							ElementType.Xpath);
					flag = webDB.clickAnElement(MaterialReceiptVoucherLocators.MrvDocNumberDrillDown,
							ElementType.Xpath);
					verifyToPostWithOrWithoutLocator();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Auto Creation Selected
	 * 
	 * @author Deepan-GWL
	 * @return booelan flag
	 */
	public boolean autoCreateMRVCheckboxIsSelected() {
		flag = webDB.isElementSelected(CommonUiLocators.AutoCreationMrvCheckBox, ElementType.Xpath);
		logs.logging("INFO", "Auto Creation MRV Flag: " + flag);
		autoPostMRVCheckboxIsSelected();
		return flag;
	}

	/**
	 * This Method Is USed To Verify Open ALready Created MRV Documents
	 * 
	 * @author Deepan-GWL
	 * @param VoucherNumber
	 */
	public void verifyOpenAlreadyCreatedMRVDocuments(String VoucherNumber) {
		flag = webDB.waitForElement(CommonUiLocators.PendingReceiptVoucherButton, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(MaterialReceiptVoucherLocators.SourceVoucher, VoucherNumber, ElementType.Xpath);
			logs.logging("INFO", "Send Text Voucher Number Is: " + VoucherNumber);
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.SearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(MaterialReceiptVoucherLocators.SearchButton, ElementType.Xpath);
				flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvNumber(VoucherNumber), ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Material Voucher Number Is Displayed");
					webDB.clickAnElement(MaterialReceiptVoucherLocators.MrvNumber(VoucherNumber), ElementType.Xpath);
					flag = webDB.waitForElement(MaterialReceiptVoucherLocators.LocationId, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Mrv Page Is Displayed");
						storeMRVDocumentsNumber();
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is USed To Verify Open ALready Created MRV Documents
	 * 
	 * @author Deepan-GWL
	 * @param VoucherNumber
	 */
	public void verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(String ProdNo) {
		flag = webDB.waitForElement(CommonUiLocators.PendingReceiptVoucherButton, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(MaterialReceiptVoucherLocators.FindProdNoLovBtn, ProdNo);
			logs.logging("INFO", "Send Text Voucher Number Is: " + ProdNo);
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.SearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(MaterialReceiptVoucherLocators.SearchButton, ElementType.Xpath);
				flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvNumber(ProdNo), ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Material Voucher Number Is Displayed");
					webDB.clickAnElement(MaterialReceiptVoucherLocators.MrvNumber(ProdNo), ElementType.Xpath);
					flag = webDB.waitForElement(MaterialReceiptVoucherLocators.LocationId, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Mrv Page Is Displayed");
						storeMRVDocumentsNumber();
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify CreditNote Post Material Receipt
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreditNotePostMaterialReceipt() {
		String ApplicationControlPostMrv = dbCommonFunctions.InAplctrl(commonFunc.getDataFromTestDataSheet("ControlBU"),
				commonFunc.getDataFromTestDataSheet("AutoPostMRV"));
		logs.logging("INFO", "AutoPost Flag Is " + ApplicationControlPostMrv);
		String ApplicationcontrolCreationMrv = dbCommonFunctions.InAplctrl(
				commonFunc.getDataFromTestDataSheet("ControlBU"),
				commonFunc.getDataFromTestDataSheet("AutoCreationMRV"));
		logs.logging("INFO", "Autocreation Flag Is " + ApplicationcontrolCreationMrv);
		if (!ApplicationControlPostMrv.equals("Y") && !ApplicationcontrolCreationMrv.equals("Y")) {
			commonFunc.navigateToMaterialReceiptVoucher();
			verifyPendingCReditNoteMRV(VariableHandler.getCNdocno());
			verifyReceiptLocatorsAndPutAway();
			clickTickAndPostIcon();
		}
//		verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
//				commonFunc.getDataFromTestDataSheet("IssuedStatus"));
	}

	/**
	 * Verify Pending Inspection Documents
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 */
	public void verifyPendingCReditNoteMRV(String DocNo) {
		flag = webDB.waitForElement(CommonUiLocators.PendingReceiptVoucherButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Receipt Voucher Button Is Displayed");
			webDB.clickAnElement(CommonUiLocators.PendingReceiptVoucherButton, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.PendingIssuance, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Insurance IS Displayed");
				webDB.clickAnElement(MaterialReceiptVoucherLocators.PendingIssuance, ElementType.Xpath);
				flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Mrv Frame Is Displayed");
					webDB.switchToFrame(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
					commonFunc.hardWait(3000);
					flag = webDB.waitForElement(InspectionDocumentsLocators.PendingSearchField, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Inspection Pending Icon IS Clicked");
						webDB.sendTextToAnElement(InspectionDocumentsLocators.PendingSearchField,
								VariableHandler.getCNdocno(), ElementType.Xpath);
						flag = webDB.waitForElement(InspectionDocumentsLocators.GoButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Go Button Is Displayed");
							webDB.clickAnElement(InspectionDocumentsLocators.GoButton, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MRVCheckBox(DocNo),
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Mrv CheckBox Is Displayed");
								webDB.clickAnElement(MaterialReceiptVoucherLocators.MRVCheckBox(DocNo),
										ElementType.Xpath);
								flag = webDB.waitForElement(MaterialReceiptVoucherLocators.CreateMrvBtn,
										ElementType.Xpath);
								if (flag) {
									commonFunc.hardWait(2000);
									logs.logging("INFO", "Create Qc Doc Is Displayed");
									webDB.clickAnElement(MaterialReceiptVoucherLocators.CreateMrvBtn,
											ElementType.Xpath);
									webDB.switchToDefaultContent();
									flag = webDB.waitForElement(MaterialReceiptVoucherLocators.LocationId,
											ElementType.Xpath);
									storeMRVDocumentsNumber();
									if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
										webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
												ElementType.Xpath);
									}
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
	 * This Method Is Used To Auto Post Selected
	 * 
	 * @author Deepan-GWL
	 * @return booelan flag
	 */
	public boolean autoPostMRVCheckboxIsSelected() {
		flag = webDB.isElementSelected(CommonUiLocators.AutoPostMRVCheckBox, ElementType.Xpath);
		logs.logging("INFO", "Auto Post MRV Flag: " + flag);
		VariableHandler.setAutoPostMRVFlag(flag);
		return flag;
	}

	/**
	 * This Method Used To Verify Rework Completion Post Material Receipt
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyReworkCompletionPostMaterialReceipt() {
		String ApplicationControlPostMrv = dbCommonFunctions.InAplctrl(commonFunc.getDataFromTestDataSheet("ControlBU"),
				commonFunc.getDataFromTestDataSheet("AutoPostMRV"));
		logs.logging("INFO", "AutoPost Flag Is " + ApplicationControlPostMrv);
		String ApplicationcontrolCreationMrv = dbCommonFunctions.InAplctrl(
				commonFunc.getDataFromTestDataSheet("ControlBU"),
				commonFunc.getDataFromTestDataSheet("AutoCreationMRV"));
		logs.logging("INFO", "Autocreation Flag Is " + ApplicationcontrolCreationMrv);
		if (!ApplicationControlPostMrv.equals("Y") && !ApplicationcontrolCreationMrv.equals("Y")) {
			commonFunc.navigateToMaterialReceiptVoucher();
			verifyPendingReworkCompletionMRV(VariableHandler.getRWCdocno());
			verifyReceiptLocatorsAndPutAway();
			clickTickAndPostIcon();
		}
//		verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
//				commonFunc.getDataFromTestDataSheet("IssuedStatus"));
	}

	/**
	 * Verify Pending Inspection Documents
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 */
	public void verifyPendingReworkCompletionMRV(String DocNo) {
		flag = webDB.waitForElement(CommonUiLocators.PendingReceiptVoucherButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Receipt Voucher Button Is Displayed");
			webDB.clickAnElement(CommonUiLocators.PendingReceiptVoucherButton, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.PendingIssuance, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Insurance IS Displayed");
				webDB.clickAnElement(MaterialReceiptVoucherLocators.PendingIssuance, ElementType.Xpath);
				flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Mrv Frame Is Displayed");
					webDB.switchToFrame(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
					commonFunc.hardWait(3000);
					flag = webDB.waitForElement(InspectionDocumentsLocators.PendingSearchField, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Inspection Pending Icon IS Clicked");
						webDB.sendTextToAnElement(InspectionDocumentsLocators.PendingSearchField,
								VariableHandler.getRWCdocno(), ElementType.Xpath);
						flag = webDB.waitForElement(InspectionDocumentsLocators.GoButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Go Button Is Displayed");
							webDB.clickAnElement(InspectionDocumentsLocators.GoButton, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MRVCheckBox(DocNo),
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Mrv CheckBox Is Displayed");
								webDB.clickAnElement(MaterialReceiptVoucherLocators.MRVCheckBox(DocNo),
										ElementType.Xpath);
								flag = webDB.waitForElement(MaterialReceiptVoucherLocators.CreateMrvBtn,
										ElementType.Xpath);
								if (flag) {
									commonFunc.hardWait(2000);
									logs.logging("INFO", "Create Qc Doc Is Displayed");
									webDB.clickAnElement(MaterialReceiptVoucherLocators.CreateMrvBtn,
											ElementType.Xpath);
									webDB.switchToDefaultContent();
									flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MRVNO,
											ElementType.Xpath);
									storeMRVDocumentsNumber();
									if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
										webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
												ElementType.Xpath);
									}
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
	 * This Method Is USed To Verify Open ALready Created MRV Documents
	 * 
	 * @author Deepan-GWL
	 * @param VoucherNumber
	 */
	public void verifyOpenAlreadyCreatedMRVDocumentsWithGRNScreen(String MRVNumber) {
		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvDocLovBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(MaterialReceiptVoucherLocators.MrvDocLovBtn, MRVNumber);
			logs.logging("INFO", "MRV Document Number Is:" + MRVNumber);
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvDocNumberSearchBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(MaterialReceiptVoucherLocators.MrvDocNumberSearchBtn, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvDocNumberDrillDown, ElementType.Xpath);
				flag = webDB.clickAnElement(MaterialReceiptVoucherLocators.MrvDocNumberDrillDown, ElementType.Xpath);
				verifyToPostWithOrWithoutLocator();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is To Post MRV Document From Pending Issuance Voucher for With
	 * And Without Locator
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyPostMaterialReceiptWithAndWithoutLocatorBasedOnApplicationControl() {
		commonFunc.navigateToMaterialReceiptVoucher();
		verifyToCreateMrvDocumentFromPendingMivWithGrnDocument();
		getMaterialReceiptVoucherNumberFromToastMessage();
		verifyToPostWithOrWithoutLocator();
		try {
			String alertText = VariableHandler.getMRVDocumentNumber();
			System.out.println("Alert text: " + alertText);
			String pattern = "MV100/\\d{5}/\\d{2}-\\d{2}\\s*to\\s*MV100/\\d{5}/\\d{2}-\\d{2}";
			if (alertText.matches(".*" + pattern + ".*")) {
				System.out.println("Specific receipt document alert found, proceeding with the process.");
				searchAndSelectLovForMrvDocumentFindPage();
//				commonFunc.logoutUser();
			} else {
				System.out.println("Other alert detected, no action taken.");
//				commonFunc.logoutUser();
			}
		} catch (Exception e) {
			System.out.println("No alert appeared or other error: " + e.getMessage());
		}
	}

	/**
	 * This Method Is USed To Verify Open ALready Created MRV Documents
	 * 
	 * @author Deepan-GWL
	 * @param VoucherNumber
	 */
	public void verifyOpenAlreadyCreatedMRVDocumentsWithGRNSCREEN(String MRVNumber) {
		commonFunc.navigateToMaterialReceiptVoucher();
		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvDocLovBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(MaterialReceiptVoucherLocators.MrvDocLovBtn, MRVNumber);
			logs.logging("INFO", "MRV Document Number Is:" + MRVNumber);
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvDocNumberSearchBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(MaterialReceiptVoucherLocators.MrvDocNumberSearchBtn, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvDocNumberDrillDown, ElementType.Xpath);
				flag = webDB.clickAnElement(MaterialReceiptVoucherLocators.MrvDocNumberDrillDown, ElementType.Xpath);
				verifyToPostWithOrWithoutLocator();
			}
		}

		assertTrue(flag);
	}

	/**
	 * This Methods Is To Post MRV Document From Pending Issuance Voucher for With
	 * And Without Locator
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void verifyPostMaterialReceiptWithAndWithoutLocatorBasedOnDocumentWiseCancel() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToMaterialReceiptVoucherAdminUser();
		verifyToCreateMrvDocumentFromPendingMivWithGrnDocument();
		getMaterialReceiptVoucherNumberFromToastMessage();
		verifyToCanceDocumentWise();
		try {
			String alertText = VariableHandler.getMRVDocumentNumber();
			System.out.println("Alert text: " + alertText);
			String pattern = "MV100/\\d{5}/\\d{2}-\\d{2}\\s*to\\s*MV100/\\d{5}/\\d{2}-\\d{2}";
			if (alertText.matches(".*" + pattern + ".*")) {
				System.out.println("Specific receipt document alert found, proceeding with the process.");
				searchAndSelectLovForMrvDocumentFindPage();
				commonFunc.logoutUser();

			} else {
				System.out.println("Other alert detected, no action taken.");
				commonFunc.logoutUser();
			}
		} catch (Exception e) {
			System.out.println("No alert appeared or other error: " + e.getMessage());
		}

	}

	/**
	 * This Methods Is Used To Post With or Without Locator Item
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void verifyToCanceDocumentWise() {
		commonFunc.checkRefreshCellsDisappear();
		if (webDB.waitForElement(MaterialReceiptVoucherLocators.MrvDocwiseCancelbutton, ElementType.Xpath)) {
			webDB.clickAnElement(MaterialReceiptVoucherLocators.MrvDocwiseCancelbutton, ElementType.Xpath);
			logs.logging("INFO", "MRV Document Successfully Cancelled");
		} else {

		}

	}

	/**
	 * This Method Is Used To PO Filter All Field Working
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreationOfGrnFilterAllFieldWorking() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToMaterialReceiptVoucherAdminUser();
		commonFunc.checkRefreshCellsDisappear();
		findPageShowMore();
		verifyDateAfterAndBefore();
		verifyAmendFilterSearchSOB(MaterialReceiptVoucherLocators.LocId, commonFunc.getDataFromTestDataSheet("Loc_100"),
				"Loc. ID", commonFunc.getDataFromTestDataSheet("HeaderSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(MaterialReceiptVoucherLocators.Unit, commonFunc.getDataFromTestDataSheet("Unit_100"),
				"Unit", commonFunc.getDataFromTestDataSheet("HeaderSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(MaterialReceiptVoucherLocators.MRVNumber,
				commonFunc.getDataFromTestDataSheet("MRVNumber"), "MRV No.",
				commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(MaterialReceiptVoucherLocators.ProdordNumber,
				commonFunc.getDataFromTestDataSheet("ProdNumber"), "Prod. Ord. No.",
				commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(MaterialReceiptVoucherLocators.SourceVoucherNumber,
				commonFunc.getDataFromTestDataSheet("GrnSourceVoucherNumber"), "Source Vou. No.",
				commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("SendTextInput"));
		verifyAmendFilterSearchSOB(MaterialReceiptVoucherLocators.ReceiverWHID,
				commonFunc.getDataFromTestDataSheet("ReceiverWH"), "Receiver W/H ID",
				commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(MaterialReceiptVoucherLocators.ReceiverWHDesc,
				commonFunc.getDataFromTestDataSheet("ReceiverWHDesc"), "Receiver W/H Desc.",
				commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("SendTextInput"));
		verifyAmendFilterSearchSOB(MaterialReceiptVoucherLocators.ItemId,
				commonFunc.getDataFromTestDataSheet("MrvItem"), "Item",
				commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(MaterialReceiptVoucherLocators.ItemDesc,
				commonFunc.getDataFromTestDataSheet("MrvItemDesc"), "Item Desc.",
				commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("SendTextInput"));
		verifyAmendFilterSearchSOB(MaterialReceiptVoucherLocators.MRVStatus,
				commonFunc.getDataFromTestDataSheet("Issued"), "Status",
				commonFunc.getDataFromTestDataSheet("HeaderSearchType"),
				commonFunc.getDataFromTestDataSheet("SelectType"));
		commonFunc.logoutUser();

	}

	/**
	 * This Method Is Used To Verify Filter Search Purchase Order
	 * 
	 * @author Deepan-GWL
	 * @param LovButton
	 * @param Value
	 * @param i
	 */
	@SuppressWarnings("unlikely-arg-type")
	public void verifyAmendFilterSearchSOB(String LovButton, String Value, String i, String Type, String selectflag) {
		webDB.scrollToAnElement(LovButton, ElementType.Xpath);
		if (selectflag.equals(commonFunc.getDataFromTestDataSheet("LovButton"))) {
			commonFunc.searchAndSelectValueInFilters(LovButton, Value);
		} else if (selectflag.equals(commonFunc.getDataFromTestDataSheet("SelectType"))) {
			webDB.selectDropDownByText(LovButton, Value, ElementType.Xpath);
		} else if (selectflag.equals(commonFunc.getDataFromTestDataSheet("SendTextInput"))) {
			webDB.sendTextToAnElement(LovButton, Value, ElementType.Xpath);
		}
		if (Type.equals(commonFunc.getDataFromTestDataSheet("HeaderSearchType"))) {
			clickSearchButton();
			commonFunc.hardWait(3000);
			verifyAmendTableValue(i, Value);
		} else if (Type.equals(commonFunc.getDataFromTestDataSheet("LineSearchType"))) {
			clickLineSearchButton();
			commonFunc.hardWait(3000);
			verifyLineAmendTableValue(i, Value);
		}
		commonFunc.expandFindTable();
		clearSearchFilter();
	}

	/**
	 * This Method Is Used To Click Search Button
	 * 
	 * @author Deepan-GWL
	 */
	/**
	 * This Method Is Used To Click Search Button
	 * 
	 * @author Deepan-GWL
	 */
	public void clickSearchButton() {
		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.HeaderCheckBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header CheckBox Is Displayed");
			webDB.clickAnElement(MaterialReceiptVoucherLocators.HeaderCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.OpenPoSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(MaterialReceiptVoucherLocators.OpenPoSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Search Button Is Clicked");

				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvFindTable, ElementType.Xpath);
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
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(MaterialReceiptVoucherLocators.MRVFindTableData(i)));
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				flag = element.getText().contains(Value);
				if (!flag) {
					break;
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click Search Button
	 * 
	 * @author Deepan-GWL
	 */
	public void clickLineSearchButton() {
		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.LineCheckBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Line CheckBox Is Displayed");
			webDB.clickAnElement(MaterialReceiptVoucherLocators.LineCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.OpenPoSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(MaterialReceiptVoucherLocators.OpenPoSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Search Button Is Clicked");

				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvLineFindTable, ElementType.Xpath);
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
	public void verifyLineAmendTableValue(String i, String Value) {
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(MaterialReceiptVoucherLocators.MRVLineFindTableData(i)));
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				flag = element.getText().contains(Value);
				if (!flag) {
					break;
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Date From And To
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDateAfterAndBefore() {
		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MRVdateFrom, ElementType.Xpath);
		webDB.sendTextToAnElement(MaterialReceiptVoucherLocators.MRVdateFrom,
				commonFunc.getDataFromTestDataSheet("EffFrom"), ElementType.Xpath);
		clickSearchButton();
		commonFunc.hardWait(3000);
		verifyAmendTableValueAfterDate(4, commonFunc.getDataFromTestDataSheet("EffFrom"));
		commonFunc.expandFindTable();
		clearSearchFilter();
		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MRVdateTo, ElementType.Xpath);
		webDB.sendTextToAnElement(MaterialReceiptVoucherLocators.MRVdateTo,
				commonFunc.getDataFromTestDataSheet("EffTo"), ElementType.Xpath);
		clickSearchButton();
		commonFunc.hardWait(3000);
		verifyAmendTableValueBeforeDate(4, commonFunc.getDataFromTestDataSheet("EffTo"));
		commonFunc.expandFindTable();
		clearSearchFilter();
	}

	/**
	 * This Method Is Used To Verify Table Value
	 * 
	 * @author Deepan-GWL
	 * @param i
	 * @param Value
	 */
	public void verifyAmendTableValueAfterDate(int i, String Value) {
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(MaterialReceiptVoucherLocators.AmendFindTableData(i)));
		System.out.println(1);
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate date1 = LocalDate.parse(element.getText(), formatter);
				LocalDate date2 = LocalDate.parse(Value, formatter);
				flag = date1.isAfter(date2);
				System.out.println("flag " + date1 + date2 + flag);
				if (!flag) {
					if (date1.equals(date2)) {
						flag = true;
					} else {
						break;
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify GRN Subcontract Material Receipt
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyGRNSubcontractForMaterialReceipt() {
		String ApplicationControlPostMrv = dbCommonFunctions.InAplctrl(commonFunc.getDataFromTestDataSheet("ControlBU"),
				commonFunc.getDataFromTestDataSheet("AutoPostMRV"));
		logs.logging("INFO", "AutoPost Flag Is " + ApplicationControlPostMrv);
		String ApplicationcontrolCreationMrv = dbCommonFunctions.InAplctrl(
				commonFunc.getDataFromTestDataSheet("ControlBU"),
				commonFunc.getDataFromTestDataSheet("AutoCreationMRV"));
		logs.logging("INFO", "Autocreation Flag Is " + ApplicationcontrolCreationMrv);
		if (!ApplicationControlPostMrv.equals("Y") && !ApplicationcontrolCreationMrv.equals("Y")) {
			commonFunc.navigateToMaterialReceiptVoucher();
			verifyGRNSubcontractMRV(VariableHandler.getScoGrnNumber());
			verifyReceiptLocatorsAndPutAway();
			clickTickAndPostIcon();
		}
	}

	/**
	 * Verify GRN Subcontract Material receipt voucher
	 * 
	 * @author Vimal - RMITS
	 * @param DocNo
	 */
	public void verifyGRNSubcontractMRV(String DocNo) {
		flag = webDB.waitForElement(CommonUiLocators.PendingReceiptVoucherButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Receipt Voucher Button Is Displayed");
			webDB.clickAnElement(CommonUiLocators.PendingReceiptVoucherButton, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.PendingIssuance, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Insurance IS Displayed");
				webDB.clickAnElement(MaterialReceiptVoucherLocators.PendingIssuance, ElementType.Xpath);
				flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Mrv Frame Is Displayed");
					webDB.switchToFrame(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
					commonFunc.hardWait(3000);
					commonFunc.expandFindTable();
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(InspectionDocumentsLocators.PendingSearchField, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Inspection Pending Icon IS Clicked");
						webDB.sendTextToAnElement(InspectionDocumentsLocators.PendingSearchField,
								VariableHandler.getScoGrnNumber(), ElementType.Xpath);
						flag = webDB.waitForElement(InspectionDocumentsLocators.GoButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Go Button Is Displayed");
							webDB.clickAnElement(InspectionDocumentsLocators.GoButton, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MRVCheckBox(DocNo),
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Mrv CheckBox Is Displayed");
								webDB.clickAnElement(MaterialReceiptVoucherLocators.MRVCheckBox(DocNo),
										ElementType.Xpath);
								flag = webDB.waitForElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);
								if (flag) {
									commonFunc.hardWait(2000);
									logs.logging("INFO", "Create Qc Doc Is Displayed");
									webDB.clickAnElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);
									webDB.switchToDefaultContent();
									flag = webDB.waitForElement(MaterialReceiptVoucherLocators.LocationId,
											ElementType.Xpath);
									storeMRVDocumentsNumber();
									if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
										webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
												ElementType.Xpath);
									}
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
	 * This Method Is Used To Verify Clear Search Filter
	 * 
	 * @author Deepan-GWL
	 */
	public void clearSearchFilter() {
		commonFunc.hardWait(1000);
		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.ClearButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialReceiptVoucherLocators.ClearButton, ElementType.Xpath);
			logs.logging("INFO", "Clear Button Is Clicked");
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
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
	public void verifyAmendTableValueBeforeDate(int i, String Value) {
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(MaterialReceiptVoucherLocators.AmendFindTableData(i)));
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate date1 = LocalDate.parse(element.getText(), formatter);
				LocalDate date2 = LocalDate.parse(Value, formatter);
				flag = date1.isBefore(date2);
				if (!flag) {
					if (date1.equals(date2)) {
						flag = true;
					} else {
						break;
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Validate Inspection
	 * 
	 * @author Sakthi-GWL
	 * @param MrvNo
	 * @param ExpectedStatus
	 */
	public void verifyValidateMRVStatusWithProdOrderNo(String ProdNo, String ExpectedStatus) {
		commonFunc.searchAndSelectValueInFilters(MaterialReceiptVoucherLocators.FindProdNoLovBtn, ProdNo);
		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.SearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialReceiptVoucherLocators.SearchButton, ElementType.Xpath);
			logs.logging("INFO", "Search Button Is Clicked");
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MaterialReceiptStatus(ProdNo),
					ElementType.Xpath);
			if (flag) {
				String Status = webDB.getTextFromElement(MaterialReceiptVoucherLocators.MaterialReceiptStatus(ProdNo),
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
	 * This Methods Is Used To Post With or Without Locator Item
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void findPageShowMore() {

		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.ShowMore, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Show More Button Is Displayed");
			webDB.clickAnElement(MaterialReceiptVoucherLocators.ShowMore, ElementType.Xpath);
			if (flag) {
				flag = webDB.waitForElement(MaterialReceiptVoucherLocators.ItemId, ElementType.Xpath);
				logs.logging("INFO", "MRV Document Find Page Show More Successfully Clicked");
			}
		}
		assertTrue(flag);
	}
}
