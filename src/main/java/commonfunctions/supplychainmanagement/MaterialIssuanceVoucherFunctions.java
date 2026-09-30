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
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.MaterialIssuanceVoucherLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class MaterialIssuanceVoucherFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();
	private static DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();

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
		commonFunc.checkRefreshCellsDisappear();
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
				webDB.moveToElement(MaterialIssuanceVoucherLocators.SearchResult_ProdOrderNo_TableData(MRNo),
						ElementType.Xpath);
				String DisplProdNo = webDB.getTextFromElement(
						MaterialIssuanceVoucherLocators.SearchResult_ProdOrderNo_TableData(MRNo), ElementType.Xpath);
				logs.logging("INFO", "Displayed Production Order No: " + DisplProdNo);
				flag = ProdNo.equalsIgnoreCase(DisplProdNo);
				if (flag) {
					logs.logging("INFO", "Production Order Validated Successfully In Material Issuance Voucher");
					webDB.moveToElement(MaterialIssuanceVoucherLocators.SearchResult_FGItem_TableData(MRNo),
							ElementType.Xpath);
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
		webDB.moveToElement(MaterialIssuanceVoucherLocators.CheckBox, ElementType.Xpath);
		webDB.clickAnElement(MaterialIssuanceVoucherLocators.CheckBox, ElementType.Xpath);
		commonFunc.checkRefreshCellsDisappear();
		webDB.waitForClickElement(MaterialIssuanceVoucherLocators.PendingMRCreateMIBtn, ElementType.Xpath);
		flag = webDB.isElementEnabled(MaterialIssuanceVoucherLocators.PendingMRCreateMIBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			logs.logging("INFO", "CheckBox Clicked for MR No: " + MRNo);
			webDB.doubleClickAnElement(MaterialIssuanceVoucherLocators.PendingMRCreateMIBtn, ElementType.Xpath);
			webDB.switchToDefaultContent();
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.DisplayMIVNo, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Create MI Button Clicked and Created Successfully");
				String MIVNo = webDB.getTextFromElement(MaterialIssuanceVoucherLocators.DisplayMIVNo,
						ElementType.Xpath);
				VariableHandler.setMIVNumber(MIVNo);
				logs.logging("INFO", "Displayed MIV No: " + VariableHandler.getMIVNumber());
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
					commonFunc.checkRefreshCellsDisappear();
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
		flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.MIVNo, ElementType.Xpath);
		if (flag) {
			String Number = webDB.getTextFromElement(MaterialIssuanceVoucherLocators.MIVNo, ElementType.Xpath);
			logs.logging("INFO", "Get Text MIV Number Is: " + Number);
			VariableHandler.setMIVNumber(Number);
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
							String Credit = CreditValue.replace(",", commonFunc.getDataFromTestDataSheet(""));
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
	 * This Method Used To Verify Header Save Header In MIV
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickheaderSaveIconInMIV() {
		flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.HeaderTickIcon, ElementType.Xpath);
		if (flag) {
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			webDB.moveToElement(MaterialIssuanceVoucherLocators.HeaderTickIcon, ElementType.Xpath);
			flag = webDB.clickAnElement(MaterialIssuanceVoucherLocators.HeaderTickIcon, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				logs.logging("INFO", "Header Save Icon Is Clicked");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
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
			webDB.moveToElement(MaterialIssuanceVoucherLocators.HeaderTickIcon, ElementType.Xpath);
			webDB.clickAnElement(MaterialIssuanceVoucherLocators.HeaderTickIcon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.HeaderPostIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Header Post Icon Is Displayed");
				webDB.clickAnElement(MaterialIssuanceVoucherLocators.HeaderPostIcon, ElementType.Xpath);
				getDeliveryhallanNumber();
				commonFunc.checkRefreshCellsDisappear();
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				commonFunc.checkSuccessToastMessageDisappear();
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
		flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.ToastMessagePopup, ElementType.Xpath, 60);
		if (flag) {
			String MessagePopup = webDB.getTextFromElement(CommonUiLocators.ToastMessagePopup, ElementType.Xpath);
			System.out.println(MessagePopup);
			Pattern pattern = Pattern.compile("DC Created.-([A-Z0-9/-]+)");
			Matcher matcher = pattern.matcher(MessagePopup.trim());
			if (matcher.find()) {
				System.out.println("Extracted Number: " + matcher.group(1));
				ChallanNo = matcher.group(1);
				logs.logging("INFO", "Delivery Challan Number Is: " + ChallanNo);
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
		commonFunc.clearFiltersInFindPage();
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
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToMaterialIssuanceVoucher();
		CreatePendingMIVDocument(VariableHandler.getMRNumber());
//		createJournalAndViewJournal();
//		verifyTableDataMiv();
//		headerValidationMiv(commonFunc.getDataFromTestDataSheet("Loc_100"),
//				commonFunc.getDataFromTestDataSheet("Warehouse100"),
//				commonFunc.getDataFromTestDataSheet("IntraTransfer"));
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
			switchMaterialRequestLinesIframe();
			commonFunc.checkShowMyRecordAndUnselectAll(MaterialIssuanceVoucherLocators.Pending_TableShowMyRecordBtn,
					MaterialIssuanceVoucherLocators.Pending_TableShowMyRecordBtnClicked,
					MaterialIssuanceVoucherLocators.CheckBox);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.expandFindTable();
			commonFunc.clearFiltersInFindPage();
			commonFunc.searchAndSelectValueInFilters(MaterialIssuanceVoucherLocators.MRLovButton, MRNumber);
			switchMaterialRequestLinesIframe();
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.SearchButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button Is Displayed");
				webDB.clickAnElement(MaterialIssuanceVoucherLocators.SearchButton, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.expandFindTable();
				flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.CheckBox, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "CheckBox IS Displayed");
					webDB.moveToElement(MaterialIssuanceVoucherLocators.CheckBox, ElementType.Xpath);
					webDB.javaScriptClickAnElement(MaterialIssuanceVoucherLocators.CheckBox, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.CreateMiButton, ElementType.Xpath);
					if (flag) {
						System.out.println(webDB.isElementEnabled(MaterialIssuanceVoucherLocators.CreateMiButton,
								ElementType.Xpath));
						logs.logging("INFO", "Create MIV Button Is Displayed");
						webDB.moveToElement(MaterialIssuanceVoucherLocators.CreateMiButton, ElementType.Xpath);
						webDB.clickAnElement(MaterialIssuanceVoucherLocators.CreateMiButton, ElementType.Xpath);
						webDB.switchToDefaultContent();
						switchMaterialRequestLinesIframe();
						flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.CreateMIVButton, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(MaterialIssuanceVoucherLocators.CreateMIVButton, ElementType.Xpath);
							logs.logging("INFO", "MIV Documents Created Successfully");
							webDB.switchToDefaultContent();
							storeMaterialIssuanceNumber();
						}
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
	 * This method used to switch to line details iframe in MIV
	 *
	 * @author Sakthi-GWL
	 */
	public void switchToLineDetailsFrameInMIV() {
		flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.LineDetailsIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(MaterialIssuanceVoucherLocators.LineDetailsIframe, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.LineDetailsSaveIcon, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click line details in MIV with Item Name
	 *
	 * @author Sakthi-GWL
	 * @param ItemName
	 */
	public void clickLineDetailsIconWithItemName(String ItemName) {
		flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.LinesTableViewDetailsIcon(ItemName),
				ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(MaterialIssuanceVoucherLocators.LinesTableViewDetailsIcon(ItemName), ElementType.Xpath);
			webDB.clickAnElement(MaterialIssuanceVoucherLocators.LinesTableViewDetailsIcon(ItemName),
					ElementType.Xpath);
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.LineDetailsIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Line Details Button Clicked for Item: " + ItemName);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to enter Machine Name in Line Details and Save Line Details
	 *
	 * @author Sakthi-GWL
	 * @param Machine
	 */
	public void selectMachineInMIVLineDetails(String Machine) {
		switchToLineDetailsFrameInMIV();
		webDB.moveToElement(MaterialIssuanceVoucherLocators.LineDetailsMachineLovBtn, ElementType.Xpath);
		commonFunc.searchAndSelectValueInFilters(MaterialIssuanceVoucherLocators.LineDetailsMachineLovBtn, Machine);
		logs.logging("INFO", "Machine Selected in Line Details: " + Machine);
		switchToLineDetailsFrameInMIV();
		webDB.moveToElement(MaterialIssuanceVoucherLocators.LineDetailsSaveIcon, ElementType.Xpath);
		webDB.clickAnElement(MaterialIssuanceVoucherLocators.LineDetailsSaveIcon, ElementType.Xpath);
		webDB.switchToDefaultContent();
//		flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
//		if (flag) {
		logs.logging("INFO", "Line Details Saved Successfully in MIV");
		if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
			webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
		}
		flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.LineDetailsIframeCloseIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialIssuanceVoucherLocators.LineDetailsIframeCloseIcon, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.HeaderTickIcon, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	public void verifyClickCreateMivButton() {
		flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.CreateMIVButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialIssuanceVoucherLocators.CreateMIVButton, ElementType.Xpath);
		}

	}

	/**
	 * This Method Is Used To Create The Pending MIV Documents
	 * 
	 * @author Vimal - RMITS
	 */
	public void CreateThePendingMIVDocumentFromMaterialRequest(String MRNumber) {
		flag = webDB.waitForElement(CommonUiLocators.PendingMIV, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending MIV Button Is Displayed");
			webDB.clickAnElement(CommonUiLocators.PendingMIV, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.PendingMrIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending MR Iframe Is Displayed");
				webDB.switchToFrame(MaterialIssuanceVoucherLocators.PendingMrIframe, ElementType.Xpath);
				commonFunc.hardWait(2000);
				commonFunc.expandFindTable();
				commonFunc.hardWait(2000);
				commonFunc.searchAndSelectValueInFilters(MaterialIssuanceVoucherLocators.MRLovButton, MRNumber);
				webDB.switchToFrame(MaterialIssuanceVoucherLocators.PendingMrIframe, ElementType.Xpath);
				commonFunc.hardWait(2000);
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
							webDB.switchToFrame(MaterialIssuanceVoucherLocators.PendingMrIframe, ElementType.Xpath);
							flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.PendingCreateMIV,
									ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(MaterialIssuanceVoucherLocators.PendingCreateMIV,
										ElementType.Xpath);
								webDB.switchToDefaultContent();
								commonFunc.hardWait(4000);
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

	/**
	 * This method used to validate the Header and Lines Details in Material
	 * Issuance Voucher
	 * 
	 * @author Sakthi-GWL
	 * @param FileName
	 * @param SheetName
	 * @param StartIndex
	 */
	public void verifyHeaderAndLinesValidationInMaterialIssuanceVoucherWithDB(String FileName, String SheetName,
			int StartIndex) {
		logs.logging("INFO", "****Material Issuance Voucher DB Validation - Raw Materials & Headers****");
		logs.logging("INFO", "Start Index: " + StartIndex);
		String SeqNo, Process, RMName, UOM, SourceWH, IssnceQnty, StockQnty, ReqQnty, UnitCost = null;
		String[][] TotalData = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		TotalData = excelUtils.readAllDataInExcel(Filepath, SheetName);
		a: for (int i = StartIndex; i < TotalData.length; i++) {
			String OprnNo = TotalData[i][0];
			SeqNo = TotalData[i][1];
			Process = TotalData[i][2];
			RMName = TotalData[i][4];
			UOM = TotalData[i][5];
			SourceWH = TotalData[i][6];
			IssnceQnty = TotalData[i][8];
			StockQnty = TotalData[i][9];
			ReqQnty = TotalData[i][10];
			UnitCost = TotalData[i][11];
			if (OprnNo.equals("") || OprnNo == null || OprnNo.isBlank() || OprnNo.isEmpty()
					|| OprnNo.equalsIgnoreCase("SFG") || OprnNo.equalsIgnoreCase("Completion")) {
				break a;
			}
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateMaterialIssuanceVoucherLinesDetails(SeqNo,
					commonFunc.getDataFromTestDataSheet("MaterialIssVoucherSeqNo")), SeqNo);
			logs.logging("INFO", "Verified SeqNo and DB Value Matched: " + SeqNo);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateMaterialIssuanceVoucherLinesDetails(SeqNo,
					commonFunc.getDataFromTestDataSheet("MaterialIssVoucherProcess")), Process);
			logs.logging("INFO", "Verified Process and DB Value Matched: " + Process);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateMaterialIssuanceVoucherLinesDetails(SeqNo,
					commonFunc.getDataFromTestDataSheet("MaterialIssVoucherRMName")), RMName);
			logs.logging("INFO", "Verified RMName and DB Value Matched: " + RMName);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateMaterialIssuanceVoucherLinesDetails(SeqNo,
					commonFunc.getDataFromTestDataSheet("MaterialIssVoucherUOM")), UOM);
			logs.logging("INFO", "Verified UOM and DB Value Matched: " + UOM);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateMaterialIssuanceVoucherLinesDetails(SeqNo,
					commonFunc.getDataFromTestDataSheet("MaterialIssVoucherSourceWH")), SourceWH);
			logs.logging("INFO", "Verified SourceWH and DB Value Matched: " + SourceWH);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateMaterialIssuanceVoucherLinesDetails(SeqNo,
					commonFunc.getDataFromTestDataSheet("MaterialIssVoucherAllocQnty")), IssnceQnty);
			logs.logging("INFO", "Verified IssnceQnty and DB Value Matched: " + IssnceQnty);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateMaterialIssuanceVoucherLinesDetails(SeqNo,
					commonFunc.getDataFromTestDataSheet("MaterialIssVoucherAcceptQnty")), StockQnty);
			logs.logging("INFO", "Verified StockQnty and DB Value Matched: " + StockQnty);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateMaterialIssuanceVoucherLinesDetails(SeqNo,
					commonFunc.getDataFromTestDataSheet("MaterialIssVoucherRqstQnty")), ReqQnty);
			logs.logging("INFO", "Verified ReqQnty and DB Value Matched: " + ReqQnty);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateMaterialIssuanceVoucherLinesDetails(SeqNo,
					commonFunc.getDataFromTestDataSheet("MaterialIssVoucherUnitCost")), UnitCost);
			logs.logging("INFO", "Verified UnitCost and DB Value Matched: " + UnitCost);
			String ProdNo = VariableHandler.getProductionOrderNumber();
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateMaterialIssuanceVoucherLinesDetails(SeqNo,
					commonFunc.getDataFromTestDataSheet("MaterialIssVoucherProdNo")), ProdNo);
			logs.logging("INFO", "Verified ProdNo and DB Value Matched: " + ProdNo);
			String MRNo = VariableHandler.getMaterialRequestNo();
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateMaterialIssuanceVoucherLinesDetails(SeqNo,
					commonFunc.getDataFromTestDataSheet("MaterialIssVoucherMRNo")), MRNo);
			logs.logging("INFO", "Verified Material Request and DB Value Matched: " + MRNo);
		}
	}

	/**
	 * This method used to validate stock transaction with Miv number
	 * 
	 * @author Sakthi-GWL
	 * @param FileName
	 * @param SheetName
	 */
	public void verifyStockTransactionValidationInMIVForRawMaterials(String FileName, String SheetName,
			int StartIndex) {
		logs.logging("INFO", "****Material Issuance Voucher Stock Transaction DB Validation****");
		logs.logging("INFO", "Start Index: " + StartIndex);
		String[][] TotalData = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		TotalData = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = StartIndex; i <= (StartIndex + 6); i++) {
			String QOHValue = TotalData[i][16];
			String SITValue = TotalData[i][17];
			String RMItem = TotalData[i][4];
			String RMWH = TotalData[i][6];
			if (QOHValue.equals("")) {
				break;
			}
			String DBQOHValve = dbCommonFunctions.validateStockTransactionWithRMInMIV(RMItem,
					commonFunc.getDataFromTestDataSheet("QOHValue"),
					commonFunc.getDataFromTestDataSheet("TransQuantityHeader"));
			commonFunc.verifyAssertionBothValues(DBQOHValve, QOHValue);
			logs.logging("INFO", "Verified QOH Value in Stock Transaction and DB Value Matched: " + QOHValue);
			String DBSITValve = dbCommonFunctions.validateStockTransactionWithRMInMIV(RMItem,
					commonFunc.getDataFromTestDataSheet("SITValue"),
					commonFunc.getDataFromTestDataSheet("TransQuantityHeader"));
			commonFunc.verifyAssertionBothValues(DBSITValve, SITValue);
			logs.logging("INFO", "Verified SIT Value in Stock Transaction and DB Value Matched: " + SITValue);
			String DBStoreValve = dbCommonFunctions.validateStockTransactionWithRMInMIV(RMItem,
					commonFunc.getDataFromTestDataSheet("SITValue"),
					commonFunc.getDataFromTestDataSheet("StockTransactionStoreId"));
			commonFunc.verifyAssertionBothValues(DBStoreValve, RMWH);
			logs.logging("INFO", "Verified Store Id in Stock Transaction and DB Value Matched: " + RMWH);
			String DBRMName = dbCommonFunctions.validateStockTransactionWithRMInMIV(RMItem,
					commonFunc.getDataFromTestDataSheet("SITValue"),
					commonFunc.getDataFromTestDataSheet("StockTransactionProdId"));
			commonFunc.verifyAssertionBothValues(DBRMName, RMItem);
			logs.logging("INFO", "Verified Prod Id in Stock Transaction and DB Value Matched: " + RMItem);
		}
	}

	/**
	 * This method used to write lot quantity in test data sheet
	 * 
	 * @author Sakthi-GWL
	 * @param FileName
	 * @param SheetName
	 */
	public void verifyWriteBeforeLotQuantityInLotForRawMaterials(String FileName, String SheetName, int StartIndex) {
		logs.logging("INFO", "****Writing Before Lot Serial Quantity DB Validation in MIV****");
		logs.logging("INFO", "Start Index: " + StartIndex);
		String[][] TotalData = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		TotalData = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = StartIndex; i < TotalData.length; i++) {
			if (TotalData[i][0].equalsIgnoreCase("Completion")) {
				break;
			}
			String RMItem = TotalData[i][4];
			String RMWH = commonFunc.getDataFromTestDataSheet("RM WH");
			String LotQuantity = dbCommonFunctions.validateLotSerialWithRMItemWarehouse(RMItem, RMWH,
					commonFunc.getDataFromTestDataSheet("LotSerialQtyHand"));
			logs.logging("INFO", "For RMItem: " + RMItem + " Lot Quanity Before = " + LotQuantity);
			excelUtils.writeDataInExcel(Filepath, SheetName, i, 14, LotQuantity);
		}
	}

	/**
	 * This method used to validate after lot serial quantity in test data sheet
	 * 
	 * @author Sakthi-GWL
	 * @param FileName
	 * @param SheetName
	 */
	public void verifyAfterLotQuantityInLotSerialValidationForRawMaterials(String FileName, String SheetName,
			int StartIndex) {
		logs.logging("INFO", "****After Validation Lot Serial Quantity DB Validation in MIV****");
		logs.logging("INFO", "Start Index: " + StartIndex);
		String[][] TotalData = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		TotalData = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = StartIndex; i <= TotalData.length; i++) {
			if (TotalData[i][14].equalsIgnoreCase("")) {
				break;
			}
			String SheetValue = TotalData[i][15];
			String RMItem = TotalData[i][4];
			String RMWH = commonFunc.getDataFromTestDataSheet("RM WH");
			String LotQuantity = dbCommonFunctions.validateLotSerialWithRMItemWarehouse(RMItem, RMWH,
					commonFunc.getDataFromTestDataSheet("LotSerialQtyHand"));
			commonFunc.verifyAssertionBothValues(LotQuantity, SheetValue);
			logs.logging("INFO", "Verified RMItem: " + RMItem + " Lot Quanity After = " + LotQuantity);

		}
	}

	/**
	 * This method used to validate after lot serial quantity in test data sheet
	 * 
	 * @author Deepan-GWL
	 * @param FileName
	 * @param SheetName
	 */
	public void verifyAfterLotQuantityInLotSerialValidationForRawMaterials(String FileName, String SheetName,
			int StartIndex, String Warehouse) {
		logs.logging("INFO", "****After Validation Lot Serial Quantity DB Validation in MIV****");
		String[][] TotalData = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		TotalData = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = StartIndex; i < StartIndex + 7; i++) {
			if (TotalData[i][14].equalsIgnoreCase("")) {
				break;
			}
			String SheetValue = TotalData[i][15];
			String RMItem = TotalData[i][4];
			String RMWH = Warehouse;
			String LotQuantity = dbCommonFunctions.validateLotSerialWithRMItemWarehouse(RMItem, RMWH,
					commonFunc.getDataFromTestDataSheet("LotSerialQtyHand"));
			System.out.println("Lot  Qty " + LotQuantity + " sheetvalue " + SheetValue);
			commonFunc.verifyAssertionBothValues(LotQuantity, SheetValue);
			logs.logging("INFO", "Verified RMItem: " + RMItem + " Lot Quanity After = " + LotQuantity);

		}
	}

	/**
	 * This method used to write lot quantity in test data sheet
	 * 
	 * @author Deepan-GWL
	 * @param FileName
	 * @param SheetName
	 */
	public void verifyWriteBeforeLotQuantityInLotForRawMaterials(String FileName, String SheetName, int StartIndex,
			String WareHouse) {
		logs.logging("INFO", "****Writing Before Lot Serial Quantity DB Validation in MIV****");
		String[][] TotalData = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		TotalData = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = StartIndex; i < TotalData.length; i++) {
//			if (TotalData[i][0].equalsIgnoreCase("SFG")) {
//				break;
//			}
			String RMItem = TotalData[i][4];
			String RMWH = WareHouse;
			String LotQuantity = dbCommonFunctions.validateLotSerialWithRMItemWarehouse(RMItem, RMWH,
					commonFunc.getDataFromTestDataSheet("LotSerialQtyHand"));
			logs.logging("INFO", "For RMItem: " + RMItem + " Lot Quanity Before = " + LotQuantity);
			excelUtils.writeDataInExcel(Filepath, SheetName, i, 14, LotQuantity);
		}
	}
}
