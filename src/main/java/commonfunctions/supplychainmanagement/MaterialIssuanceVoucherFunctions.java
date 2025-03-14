package commonfunctions.supplychainmanagement;

/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks March 04, 2025
 */
import static org.testng.Assert.assertTrue;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.MaterialIssuanceVoucherLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class MaterialIssuanceVoucherFunctions {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();

	/**
	 * This Method used to switch Material Request Lines Frame
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchMaterialRequestLinesIframe() {
		flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.PendingMRLinesIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(MaterialIssuanceVoucherLocators.PendingMRLinesIframe, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.PendingMRSearchBar, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to search and filter with MR No
	 * 
	 * @author Sakthi-GWL
	 * @param MRNo
	 */
	public void searchParticularDocumentWithMRNo(String MRNo) {
		switchMaterialRequestLinesIframe();
		flag = webDB.sendTextToAnElement(MaterialIssuanceVoucherLocators.PendingMRSearchBar, MRNo, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(MaterialIssuanceVoucherLocators.PendingMRSearchGoBtn, ElementType.Xpath);
			webDB.clickAnElement(MaterialIssuanceVoucherLocators.PendingMRSearchGoBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.SearchResult_MRNo_TableData(MRNo),
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Succesffuly Searched Document With Material Request Number: " + MRNo);
			}
		}
		if (flag) {
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.PendingMRIframeCloseIcon, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to validate Item Warehouse and Production Order No and FG
	 * Item with MR No in search result table
	 * 
	 * @author Sakthi-GWL
	 * @param MRNo
	 * @param Item
	 * @param Warehouse
	 * @param ProdNo
	 * @param FGItem
	 */
	public void validateItemWHProdNoFGItemWithMRNo(String MRNo, String Item, String Warehouse, String ProdNo,
			String FGItem) {
		switchMaterialRequestLinesIframe();
		String DisplItem = webDB.getTextFromElement(MaterialIssuanceVoucherLocators.SearchResult_Item_TableData(MRNo),
				ElementType.Xpath);
		logs.logging("INFO", "Displayed Item: " + DisplItem);
		flag = Item.equalsIgnoreCase(DisplItem);
		if (flag) {
			logs.logging("INFO", "Item Validated Successfully In Material Issuance Voucher");
			String DisplWH = webDB.getTextFromElement(
					MaterialIssuanceVoucherLocators.SearchResult_Warehouse_TableData(MRNo), ElementType.Xpath);
			logs.logging("INFO", "Displayed WareHouse: " + DisplWH);
			flag = Warehouse.equalsIgnoreCase(DisplWH);
			if (flag) {
				logs.logging("INFO", "Warehouse Validated Successfully In Material Issuance Voucher");
				String DisplProdNo = webDB.getTextFromElement(
						MaterialIssuanceVoucherLocators.SearchResult_ProdOrderNo_TableData(MRNo), ElementType.Xpath);
				logs.logging("INFO", "Displayed Production Order No: " + DisplProdNo);
				flag = ProdNo.equalsIgnoreCase(DisplProdNo);
				if (flag) {
					logs.logging("INFO", "Production Order Validated Successfully In Material Issuance Voucher");
					String DisplFGItem = webDB.getTextFromElement(
							MaterialIssuanceVoucherLocators.SearchResult_FGItem_TableData(MRNo), ElementType.Xpath);
					logs.logging("INFO", "Displayed FG Item: " + DisplFGItem);
					flag = FGItem.equalsIgnoreCase(DisplFGItem);
					if (flag) {
						logs.logging("INFO", "FG Item Validated Successfully In Material Issuance Voucher");
					}
				}
			}
		}
		if (flag) {
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.PendingMRIframeCloseIcon, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to select MRNo and click Create MI button
	 * 
	 * @author Sakthi-GWL
	 * @param MRNo
	 */
	public void clickCheckBoxWithMRNoAndCreateMI(String MRNo) {
		switchMaterialRequestLinesIframe();
		webDB.moveToElement(MaterialIssuanceVoucherLocators.SearchResult_TableSelectBox(MRNo), ElementType.Xpath);
		webDB.clickAnElement(MaterialIssuanceVoucherLocators.SearchResult_TableSelectBox(MRNo), ElementType.Xpath);
		commonFunc.checkRefreshCellsDisappear();
		webDB.waitForClickElement(MaterialIssuanceVoucherLocators.PendingMRCreateMIBtn, ElementType.Xpath);
		flag = webDB.isElementEnabled(MaterialIssuanceVoucherLocators.PendingMRCreateMIBtn, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "CheckBox Clicked for MR No: " + MRNo);
			webDB.clickAnElement(MaterialIssuanceVoucherLocators.PendingMRCreateMIBtn, ElementType.Xpath);
			webDB.switchToDefaultContent();
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.DisplayMIVNo, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Create MI Button Clicked and Created Successfully");
				String MIVNo = webDB.getTextFromElement(MaterialIssuanceVoucherLocators.DisplayMIVNo,
						ElementType.Xpath);
				VariableHandler.setMaterialIssuanceVoucherNo(MIVNo);
				logs.logging("INFO", "Displayed MIV No: " + VariableHandler.getMaterialIssuanceVoucherNo());
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to save header and click post
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickSaveHeaderAndPostBtnInMIV() {
		commonFunc.checkSuccessToastMessageDisappear();
		flag = webDB.waitForClickElement(MaterialIssuanceVoucherLocators.SaveHeaderIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(MaterialIssuanceVoucherLocators.SaveHeaderIcon, ElementType.Xpath);
			webDB.clickAnElement(MaterialIssuanceVoucherLocators.SaveHeaderIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Save Header Clicked and Saved Toast Displayed");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.MIVPostBtn, ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(MaterialIssuanceVoucherLocators.MIVPostBtn, ElementType.Xpath);
					webDB.clickAnElement(MaterialIssuanceVoucherLocators.MIVPostBtn, ElementType.Xpath);
					flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Post Button Clicked and Toast Message Displayed");
						if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);

						}
					}
				}
			}

		}
		assertTrue(flag);
	}

	/**
	 * Check Status Of Material Issuance Voucher
	 * 
	 * @author Deepan-GWL, Sakthi-GWL
	 * @param MrNo
	 * @param ExpectedStatus
	 */
	public void checkStatusOfMaterialIssuanceVoucher(String MrNo, String ExpectedStatus) {
		commonFunc.searchAndSelectValueInFilters(MaterialIssuanceVoucherLocators.VoucherNoLovButton, MrNo);
		flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.MivSearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialIssuanceVoucherLocators.MivSearchButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.PostedMiv(MrNo), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button clicked and MR No: " + MrNo + " Displayed");
				String Status = webDB.getTextFromElement(MaterialIssuanceVoucherLocators.PostedMiv(MrNo),
						ElementType.Xpath);
				logs.logging("INFO", "MR Number: '" + MrNo + "' searched and displaying status: " + Status);
				flag = Status.equalsIgnoreCase(ExpectedStatus);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Store Material Issuance Number
	 * 
	 * @author Deepan-GWL
	 */
	public void storeMaterialIssuanceNumber() {
		String Number = webDB.getTextFromElement(MaterialIssuanceVoucherLocators.MIVNo, ElementType.Xpath);
		logs.logging("INFO", "Get Text MIV Number Is: " + Number);
		VariableHandler.setMIVNumber(Number);
	}

	/**
	 * This Method Is Used To Create Journal And View Journal
	 * 
	 * @author Deepan-GWL
	 */
	public void createJournalAndViewJournal() {
		commonFunc.hardWait(3000);
		flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.CreateJnl, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Create Journal Button Is Displayed");
			webDB.clickAnElement(MaterialIssuanceVoucherLocators.CreateJnl, ElementType.Xpath);
			commonFunc.hardWait(3000);
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.ViewJournal, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "View Journal Button Is Displayed");
				webDB.clickAnElement(MaterialIssuanceVoucherLocators.ViewJournal, ElementType.Xpath);
				flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.ViewJournalFrame, ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(MaterialIssuanceVoucherLocators.ViewJournalFrame, ElementType.Xpath);
					flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.DebitTable, ElementType.Xpath);
					if (flag) {
						webDB.waitForElement(MaterialIssuanceVoucherLocators.DebitTable, ElementType.Xpath);
						String DebitValue = webDB.getTextFromElement(MaterialIssuanceVoucherLocators.DebitTable,
								ElementType.Xpath);
						String CreditValue = webDB.getTextFromElement(MaterialIssuanceVoucherLocators.CreditTable,
								ElementType.Xpath);
						logs.logging("INFO", "Debit Value Is: " + DebitValue);
						logs.logging("INFO", "Credit Value Is: " + CreditValue);
						flag = DebitValue.equals(CreditValue);
						if (flag) {
							logs.logging("INFO", "Verfied Credit Value With Debit Value");
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
									flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.MILocationId,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Location Id Is Displayed");
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
	 * This Method Is Used To Verify Table Data MIV
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyTableDataMiv() {
		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("MRTMRVSheet"));
		for (int i = 1; i < itemmultiple.length; i++) {
			List<WebElement> TableList = webDB.getDriver()
					.findElements(By.xpath(MaterialIssuanceVoucherLocators.ListElements));
			if (flag) {
				for (int j = 1; j <= TableList.size(); j++) {
					String ItemText = webDB.getTextFromElement(MaterialIssuanceVoucherLocators.ItemTable(j),
							ElementType.Xpath);
					String ItemCost = webDB.getTextFromElement(MaterialIssuanceVoucherLocators.ItemCostTable(j),
							ElementType.Xpath);
					if (ItemText.equals(itemmultiple[i][0])) {
						if (ItemCost.equals(purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnString(
								new BigDecimal(VariableHandler.getStockCost().get(itemmultiple[i][0]))))) {
							logs.logging("INFO",
									"Verified Item " + ItemText + "  With Item Cost " + ItemCost + " With Table Data");
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
	 * This Method Used To Verify Header Tick And Post Icon
	 * 
	 * @author Deepan-GWL
	 */
	public void headerTickAndPostIcon() {
		flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.HeaderTickIcon, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header Tick Icon Is Displayed");
			webDB.clickAnElement(MaterialIssuanceVoucherLocators.HeaderTickIcon, ElementType.Xpath);
			commonFunc.hardWait(3000);
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.HeaderPostIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Header Post Icon Is Displayed");
				webDB.clickAnElement(MaterialIssuanceVoucherLocators.HeaderPostIcon, ElementType.Xpath);
				getDeliveryhallanNumber();
				if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {

					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				flag = webDB.waitForElement(CommonUiLocators.PendingMIV, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Pending MIV Button Is Displayed");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Get Delivery Challan Number
	 * 
	 * @author Deepan-GWL
	 */
	public void getDeliveryhallanNumber() {
		String ChallanNo = null;
		flag = webDB.waitForElement(CommonUiLocators.ToastMessagePopup, ElementType.Xpath);
		if (flag) {
			String MessagePopup = webDB.getTextFromElement(CommonUiLocators.ToastMessagePopup, ElementType.Xpath);
			Pattern pattern = Pattern.compile("DC\\d{2}-\\d{4}-\\d{5}");
			Matcher matcher = pattern.matcher(MessagePopup.trim());
			if (matcher.find()) {
				System.out.println("Extracted Number: " + matcher.group());
				ChallanNo = matcher.group();
				VariableHandler.setDCNumber(ChallanNo);
			} else {
				System.out.println("Pattern not found!");
			}
		}

	}

	/**
	 * This Method Is Used To Header Validation
	 * 
	 * @author Deepan-GWL
	 * @param Location
	 * @param ReceiverWH
	 */
	public void headerValidationMiv(String Location, String ReceiverWH, String Type) {
		purchaseOrderFunc.verifyGetTextValidation(MaterialIssuanceVoucherLocators.TypeText, Type);
		purchaseOrderFunc.verifyGetTextValidation(MaterialIssuanceVoucherLocators.MILocationId, Location);
		purchaseOrderFunc.verifyGetTextValidation(MaterialIssuanceVoucherLocators.ReceiverWH, ReceiverWH);
	}

	/**
	 * Check Status Of Material Issuance Number
	 * 
	 * @author Deepan-GWL
	 * @param MrNo
	 * @param ExpectedStatus
	 */
	public void checkStatusOfMaterialIssuanceNumber(String MrNo, String ExpectedStatus) {
		commonFunc.searchAndSelectValueInFilters(MaterialIssuanceVoucherLocators.VoucherNoLovButton, MrNo);
		flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.MivSearchButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "MIV Search Button IS Displayed");
			webDB.clickAnElement(MaterialIssuanceVoucherLocators.MivSearchButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.PostedMiv(MrNo), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button clicked and MR No: " + MrNo + " Displayed");
				String Status = webDB.getTextFromElement(MaterialIssuanceVoucherLocators.PostedMiv(MrNo),
						ElementType.Xpath);
				logs.logging("INFO", "MR Number: '" + MrNo + "' searched and displaying status: " + Status);
				flag = Status.equalsIgnoreCase(ExpectedStatus);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Table Data MIV
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyTableDataMivForWip() {
		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("MRToMRVWipSheet"));
		for (int i = 1; i < itemmultiple.length; i++) {
			List<WebElement> TableList = webDB.getDriver()
					.findElements(By.xpath(MaterialIssuanceVoucherLocators.ListElements));
			if (flag) {
				for (int j = 1; j <= TableList.size(); j++) {
					String ItemText = webDB.getTextFromElement(MaterialIssuanceVoucherLocators.ItemTable(j),
							ElementType.Xpath);
					String ItemCost = webDB.getTextFromElement(MaterialIssuanceVoucherLocators.ItemCostTable(j),
							ElementType.Xpath);
					if (ItemText.equals(itemmultiple[i][0])) {
						if (ItemCost.equals(purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnString(
								new BigDecimal(VariableHandler.getStockCost().get(itemmultiple[i][0]))))) {
							logs.logging("INFO",
									"Verified Item " + ItemText + "  With Item Cost " + ItemCost + " With Table Data");
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
	 * This Method Is Used To Create Material Issuance Voucher
	 * 
	 * @author Deepan-GWL
	 */
	public void createMaterialIssuanceVoucher() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToMaterialIssuanceVoucher();
		CreatePendingMIVDocument(VariableHandler.getMRNumber());
		createJournalAndViewJournal();
		verifyTableDataMiv();
		headerValidationMiv(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Warehouse100"),
				commonFunc.getDataFromTestDataSheet("IntraTransfer"));
		headerTickAndPostIcon();
		checkStatusOfMaterialIssuanceNumber(VariableHandler.getMIVNumber(),
				commonFunc.getDataFromTestDataSheet("PostedStatus"));
		commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To Create Pending MIV Documents
	 * 
	 * @author Deepan-GWL
	 */
	public void CreatePendingMIVDocument(String MRNumber) {
		flag = webDB.waitForElement(CommonUiLocators.PendingMIV, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending MIV Button Is Displayed");
			webDB.clickAnElement(CommonUiLocators.PendingMIV, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.PendingMrIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending MR Iframe Is Displayed");
				webDB.switchToFrame(MaterialIssuanceVoucherLocators.PendingMrIframe, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(MaterialIssuanceVoucherLocators.MRLovButton, MRNumber);
				webDB.switchToFrame(MaterialIssuanceVoucherLocators.PendingMrIframe, ElementType.Xpath);
				flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.SearchButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Search Button Is Displayed");
					webDB.clickAnElement(MaterialIssuanceVoucherLocators.SearchButton, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.CheckBox, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "CheckBox IS Displayed");
						commonFunc.hardWait(3000);
						webDB.clickAnElement(MaterialIssuanceVoucherLocators.CheckBox, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(3000);
						flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.CreateMiButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Create Mi Button Is Displayed");
							webDB.clickAnElement(MaterialIssuanceVoucherLocators.CreateMiButton, ElementType.Xpath);
							webDB.switchToDefaultContent();
							flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.MILocationId,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "MIV Documents Created Successfully");
								storeMaterialIssuanceNumber();
							}
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

}
