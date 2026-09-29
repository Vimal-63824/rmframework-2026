package commonfunctions.supplychainmanagement;

/**
 * @author Durai
 * @company Roadmap May 02, 2025
 */

import static org.testng.Assert.assertTrue;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import commonlocators.supplychainmanagement.RfqLocators;
import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.ApprovalPageLocators;
import commonlocators.supplychainmanagement.DirectGrnPageLocators;
import commonlocators.supplychainmanagement.PricelistPageLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import commonlocators.supplychainmanagement.PurchaseRequestLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class RfqPageFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static PurchaseRequestFunctions PrFunc = new PurchaseRequestFunctions();
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	private static DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfPRGeneralRfq() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePurchaseRequestPage();
		clickAddIconFillDetailsOfPurchaseRequestRfq(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		verifyMultipleLinesPR();
		saveHeaderAndClickPost();
		waitingForApproval();
		commonFunc.hardWait(3000);
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPRNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of Pending RFQ Domestic
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfPendingRfqDomestic() {
		verifyCreationOfPRGeneralRfq();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateRfqPage();
		pendingRfq();
		pendingRfqPRNumber(VariableHandler.getPRNumber());
		createRfq();
		verifyRfqSupplierAddDomestic();
		verifyRfqSendRfq();
		verifyNavigateToSupplierTab();
		verifysSupplierCost1(commonFunc.getDataFromTestDataSheet("Cost_Value1"), getTextCurrencyInLine1(),
				commonFunc.getDataFromTestDataSheet("Trd_Discount"),
				commonFunc.getDataFromTestDataSheet("Spcl_Discount"),
				commonFunc.getDataFromTestDataSheet("Cash_Discount"), commonFunc.getDataFromTestDataSheet("Hsn_code"));
		validateRfqLinesItemDomestic();
		verifysSupplierCost2(commonFunc.getDataFromTestDataSheet("Cost_Value2"), getTextCurrencyInLine2(),
				commonFunc.getDataFromTestDataSheet("Trd_Discount"),
				commonFunc.getDataFromTestDataSheet("Spcl_Discount"),
				commonFunc.getDataFromTestDataSheet("Cash_Discount"), commonFunc.getDataFromTestDataSheet("Hsn_code"));
		validateRfqLinesItemDomestic1();
		verifyRfqRateFinalize();
		verifysRfqSobButton();
		waitingForApproval();
		commonFunc.hardWait(3000);
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getRfqDocNum(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PRFQ Domestic and Import
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfPendingRfqDomesticAndImport() {
		verifyCreationOfPRGeneralRfq();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateRfqPage();
		pendingRfq();
		pendingRfqPRNumber(VariableHandler.getPRNumber());
		createRfq();
		verifyRfqSupplierAddDomesticAndImport();
		verifyRfqSendRfq();
		verifyNavigateToSupplierTab();
		verifysSupplierCost1(commonFunc.getDataFromTestDataSheet("Cost_Value1"), getTextCurrencyInLine1(),
				commonFunc.getDataFromTestDataSheet("Trd_Discount"),
				commonFunc.getDataFromTestDataSheet("Spcl_Discount"),
				commonFunc.getDataFromTestDataSheet("Cash_Discount"), commonFunc.getDataFromTestDataSheet("Hsn_code"));
		validateRfqLinesItemImport();
		verifysSupplierCost2(commonFunc.getDataFromTestDataSheet("Cost_Value2"), getTextCurrencyInLine2(),
				commonFunc.getDataFromTestDataSheet("Trd_Discount"),
				commonFunc.getDataFromTestDataSheet("Spcl_Discount"),
				commonFunc.getDataFromTestDataSheet("Cash_Discount"), commonFunc.getDataFromTestDataSheet("Hsn_code"));
		validateRfqLinesItemImport1();
		verifyRfqRateFinalize();
		verifysRfqSobButton();
		waitingForApproval();
		commonFunc.hardWait(3000);
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getRfqDocNum(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of Pending Po From Rfq
	 * 
	 * @author Durai-RMITS
	 * @return
	 */
	public boolean verifyCreationOfPendingPOFromRfq() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		verifyCreationOfPendingRfqDomestic();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePurchaseOrderPage();
		verifyClickingPndPo();
		return flag;
	}

	/**
	 * This Methods Is Used To Verify PO From Rfq POst
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyPoRfqPost() {
		commonFunc.hardWait(4000);
		flag = webDB.waitForElement(RfqLocators.PORfqPost, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(RfqLocators.PORfqPost, ElementType.Xpath);
			logs.logging("INFO", "Po Rfq Document Is Posted");
			flag = webDB.waitForElement(RfqLocators.RfqWorkflowFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Frame is Finded");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Clicking Pending PO
	 * 
	 * @author Durai-RMITS
	 */

	public void verifyClickingPndPo() {
		for (int i = 1; i < 1; i++) {
		flag = webDB.waitForElement(RfqLocators.PendingPo, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(RfqLocators.PendingPo, ElementType.Xpath);
			logs.logging("INFO", "Pending PO Is Clicked");
			flag = webDB.waitForElement(RfqLocators.PendingRfq, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(RfqLocators.PendingRfq, ElementType.Xpath);
				logs.logging("INFO", "Pending RFQ Is Clicked");
				verifySelectTheRfqDocument(VariableHandler.getRfqDocNum());
				selectTheDocumentRfq();
				createPOButton();
				getDocNumPoFromRfqMulti();
				verifyRfqNumInPoDetails(i);
				verifyPoRfqPost();
				waitingForApproval();
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
				flag = commonFunc.logoutUser();
				if (flag) {
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
					commonFunc.navigatePurchaseOrderPage();
					verifyPoRefind();
				}
			}
		}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Clicking Purchase Order Refind
	 * 
	 * @author Durai-RMITS
	 */

	public void verifyPoRefind() {
		ArrayList<String> PONumbers = VariableHandler.getPONumbers();
		logs.logging("INFO", "PO Number Is:" + PONumbers.size());
		for (int i = 1; i < PONumbers.size(); i++) {
			String PORfqNum = PONumbers.get(i);
			PONumbers.get(i);
			flag = webDB.waitForElement(RfqLocators.FindPoSearchField, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(RfqLocators.FindPoSearchField, PORfqNum);
				logs.logging("INFO", "PONumbers Is Entered:" + PORfqNum);
				flag = webDB.waitForElement(RfqLocators.POSearchBtn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(RfqLocators.POSearchBtn, ElementType.Xpath);
					logs.logging("INFO", "Search Button Is Clicked");
					flag = webDB.waitForElement(RfqLocators.PODrillDown, ElementType.CssSelector);
					if (flag) {
						webDB.clickAnElement(RfqLocators.PODrillDown, ElementType.CssSelector);
						logs.logging("INFO", "Document Is Moved ");
						verifyRfqNumInPoDetails(i);
						verifyPoRfqPost();
						waitingForApproval();
						forwardApprovalScreen();
						filterDocumentsForPriceListApproval(VariableHandler.getPONumber());
						verifyApproveWithDocNumber(VariableHandler.getPONumber());
						commonFunc.logoutUser();
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Entering Purchase Number
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyEnteringPoNumber() {
		ArrayList<String> PONumbers = new ArrayList<>();
		for (int i = 0; i < PONumbers.size(); i++) {
			PONumbers.get(i);
			flag = webDB.waitForElement(RfqLocators.FindPoSearchField, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(RfqLocators.FindPoSearchField, ElementType.Xpath);
				webDB.sendTextToAnElement(RfqLocators.FindPoSearchField, PONumbers.get(1), ElementType.Xpath);
				logs.logging("INFO", "PONumbers Is Entered:" + PONumbers);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Select the RFQ Document
	 * 
	 * @author Durai-RMITS
	 * @param Number
	 */
	public void verifySelectTheRfqDocument(String Number) {
		flag = webDB.waitForElement(RfqLocators.RfqPOPendingFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(RfqLocators.RfqPOPendingFrame, ElementType.Xpath);
			webDB.clickAnElement(RfqLocators.PendingRfqShowMore, ElementType.Xpath);
			logs.logging("INFO", "Switched To Frame");
			flag = webDB.waitForElement(RfqLocators.RfqPendingRfqNolov, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(RfqLocators.RfqPendingRfqNolov, Number);
				logs.logging("INFO", "Document Number Is :" + Number);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Select the Document in Pending
	 * 
	 * @author Durai-RMITS
	 */
	public void selectTheDocumentRfq() {
		flag = webDB.waitForElement(RfqLocators.RfqPOPendingFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(RfqLocators.RfqPOPendingFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Frame");
			flag = webDB.waitForElement(RfqLocators.PORfqPendingSearchbutton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(RfqLocators.PORfqPendingSearchbutton, ElementType.Xpath);
				logs.logging("INFO", "Search Button Is Clicked");
				flag = webDB.waitForElement(RfqLocators.PoRfqPendingSelectall, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(RfqLocators.PoRfqPendingSelectall, ElementType.Xpath);
					logs.logging("INFO", "Select All Is Checked");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Create PO from RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void createPOButton() {
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(RfqLocators.CreatePoBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(RfqLocators.CreatePoBtn, ElementType.Xpath);
			logs.logging("INFO", "PO is Created");
			commonFunc.hardWait(2000);
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Rfq Number in Purchase order Details
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyRfqNumInPoDetails(int i) {
		commonFunc.hardWait(2000);
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		VariableHandler.setPONumber(PONumbervalue);
		logs.logging("INFO", "Get PO Number Is: " + VariableHandler.getPONumber());
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PurchaseOrderLineDetail(i), ElementType.CssSelector);
		if (flag) {
//			webDB.clickAnElement(RfqLocators.PoDetailsBtn, ElementType.Xpath);
			webDB.clickAnElement(PurchaseOrderPageLocators.PurchaseOrderLineDetail(i), ElementType.CssSelector);
			logs.logging("INFO", "PO Details Button is Clicked");
			
			flag = webDB.waitForElement(PurchaseOrderPageLocators.PoDetails, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseOrderPageLocators.PoDetails, ElementType.Xpath);
				logs.logging("INFO", "PO Details field clicked successfully");
				
			webDB.switchToFrame(RfqLocators.RfqPODetailsFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched to Frame");
			flag = webDB.waitForElement(RfqLocators.PoLineRfqDetails, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(RfqLocators.PoLineRfqDetails, ElementType.Xpath);
				logs.logging("INFO", "DETAILS 2 is Clicked");
				flag = webDB.waitForElement(RfqLocators.DetailsRfqNum, ElementType.Xpath);
				if (flag) {
					String DisplayText = webDB.getDomPropertyFromElement(RfqLocators.DetailsRfqNum, ElementType.Xpath,
							commonFunc.getDataFromTestDataSheet("Value"));
					logs.logging("INFO",
							"Expected Text: " + VariableHandler.getRfqDocNum() + " Displayed: " + DisplayText);
					flag = DisplayText.equalsIgnoreCase(VariableHandler.getRfqDocNum());
					if (flag) {
						logs.logging("INFO", "verified Displayed  Rfq Num Header: " + DisplayText);
					} else {
						logs.logging("INFO", "Verification failed In Rfq Document Num");
						logs.logging("INFO",
								"Expected Text: " + VariableHandler.getRfqDocNum() + " Displayed: " + DisplayText);
					}
					commonFunc.hardWait(5000);
					webDB.pressEscapeKey();
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(RfqLocators.PORfqPost, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Post Button  Is Searched");
					}
				}
			}
		}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Get Document Number From Alert message
	 * 
	 * @author Durai-RMITS
	 */
	public void getDocNumPoFromRfq() {
		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(RfqLocators.PoFromRfqDocNum, ElementType.Xpath);
		String successMessage = webDB.getTextFromElement(RfqLocators.PoFromRfqDocNum, ElementType.Xpath);
		Pattern pattern = Pattern.compile("PO\\d+/\\d+/\\d{2}-\\d{2}");
		Matcher matcher = pattern.matcher(successMessage);
		while (matcher.find()) {
			logs.logging("INFO", "Extracted Document Number: " + matcher.group());
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Get Document Number From Alert message
	 * 
	 * @author Durai-RMITS
	 */
	public void getDocNumPoFromRfqMulti() {
		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(RfqLocators.PoFromRfqDocNum, ElementType.Xpath);
		String PoRfqNumtext = webDB.getTextFromElement(RfqLocators.PoFromRfqDocNum, ElementType.Xpath);
		String regex = "PO\\d{3}/\\d{5}/\\d{2}-\\d{2,3}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(PoRfqNumtext);
		ArrayList<String> PONumbers = new ArrayList<>();
		while (matcher.find()) {
			PONumbers.add(matcher.group());
		}
		logs.logging("INFO", "PO Number Is:" + PONumbers);
		VariableHandler.setPONumbers(PONumbers);
		PONumbers.size();
		assertTrue(flag);
	}

	/**
	 * This method used to verify Adding Supplier
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyRfqSupplierAddDomesticAndImport() {
		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(RfqLocators.RfqDocNum, ElementType.Xpath);
		if (flag) {
			String RfqDocNum = webDB.getDomPropertyFromElement(RfqLocators.RfqDocNum, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			VariableHandler.setRfqDocNum(RfqDocNum);
			logs.logging("INFO", "RFQ Document number Is:" + VariableHandler.getRfqDocNum());
			commonFunc.hardWait(1000);
			List<WebElement> LineNumber = webDB.getDriver().findElements(By.xpath(RfqLocators.RfqLineNum));
			logs.logging("INFO", "Line Number Is:" + LineNumber.size());
			for (int i = 1; i <= LineNumber.size(); i++) {
				flag = webDB.waitForElement(RfqLocators.ItemName(i), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(RfqLocators.ItemName(i), ElementType.Xpath);
					logs.logging("INFO", "Item Field Is Clicked");
					commonFunc.hardWait(1000);
					String[][] Suppliermultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
					ExcelUtils excelUtils = new ExcelUtils();
					String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
							+ "ItemDetails.xlsx";
					Suppliermultiple = excelUtils.readAllDataInExcel(Filepath,
							webDB.getDataFromProperties("Suppliermultiple"));
					for (int j = 1; j < Suppliermultiple.length; j++) {
						flag = webDB.waitForElement(RfqLocators.ItemName(i), ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(RfqLocators.ItemName(i), ElementType.Xpath);
							String Supplier = Suppliermultiple[j][0];
							commonFunc.hardWait(2000);
							flag = webDB.waitForElement(RfqLocators.RfqSupplierAdd, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(RfqLocators.RfqSupplierAdd, ElementType.Xpath);
								logs.logging("INFO", "Supplier Add Button Is Clicked");
								flag = webDB.waitForElement(RfqLocators.RfqlineSupplierfield, ElementType.Xpath);
								if (flag) {
									webDB.doubleClickAnElement(RfqLocators.RfqlineSupplierfield, ElementType.Xpath);
									logs.logging("INFO", "Supplier Field Is Clicked");
									flag = webDB.waitForElement(RfqLocators.RfqSupplierLov, ElementType.Xpath);
									if (flag) {
										commonFunc.searchAndSelectValueInFilters(RfqLocators.RfqSupplierLov, Supplier);
										logs.logging("INFO", "Supplier Is Entered:" + Supplier);
										webDB.waitForElement(RfqLocators.RfqlineSupplierSaveButton, ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(RfqLocators.RfqlineSupplierSaveButton,
													ElementType.Xpath);
											logs.logging("INFO", "Supplier Is Saved");
											commonFunc.hardWait(3000);
										}
									}
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
	 * This method used to verify Adding Supplier Domestic
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyRfqSupplierAddDomestic() {
		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(RfqLocators.RfqDocNum, ElementType.Xpath);
		if (flag) {
			String RfqDocNum = webDB.getDomPropertyFromElement(RfqLocators.RfqDocNum, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			VariableHandler.setRfqDocNum(RfqDocNum);
			logs.logging("INFO", "RFQ Document number Is:" + VariableHandler.getRfqDocNum());
			commonFunc.hardWait(1000);
			List<WebElement> LineNumber = webDB.getDriver().findElements(By.xpath(RfqLocators.RfqLineNum));
			logs.logging("INFO", "Line Number Is:" + LineNumber.size());
			for (int i = 1; i <= LineNumber.size(); i++) {
				flag = webDB.waitForElement(RfqLocators.ItemName(i), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(RfqLocators.ItemName(i), ElementType.Xpath);
					logs.logging("INFO", "Item Field Is Clicked");
					commonFunc.hardWait(1000);
					String[][] SuppliermultipleDOM = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
					ExcelUtils excelUtils = new ExcelUtils();
					String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
							+ "ItemDetails.xlsx";
					SuppliermultipleDOM = excelUtils.readAllDataInExcel(Filepath,
							webDB.getDataFromProperties("SuppliermultipleDOM"));
					for (int j = 1; j < SuppliermultipleDOM.length; j++) {
						flag = webDB.waitForElement(RfqLocators.ItemName(i), ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(RfqLocators.ItemName(i), ElementType.Xpath);
							String Supplier = SuppliermultipleDOM[j][0];
							commonFunc.hardWait(2000);
							flag = webDB.waitForElement(RfqLocators.RfqSupplierAdd, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(RfqLocators.RfqSupplierAdd, ElementType.Xpath);
								logs.logging("INFO", "Supplier Add Button Is Clicked");
								flag = webDB.waitForElement(RfqLocators.RfqlineSupplierfield, ElementType.Xpath);
								if (flag) {
									webDB.doubleClickAnElement(RfqLocators.RfqlineSupplierfield, ElementType.Xpath);
									logs.logging("INFO", "Supplier field Is Clicked");
									flag = webDB.waitForElement(RfqLocators.RfqSupplierLov, ElementType.Xpath);
									if (flag) {
										commonFunc.searchAndSelectValueInFilters(RfqLocators.RfqSupplierLov, Supplier);
										logs.logging("INFO", "Supplier Is Entered:" + Supplier);
										commonFunc.hardWait(4000);
										flag = webDB.waitForElement(RfqLocators.RfqSupplierfield, ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(RfqLocators.RfqSupplierfield, ElementType.Xpath);
											logs.logging("INFO", "SupplierField Is Clicked");
											webDB.waitForElement(RfqLocators.RfqlineSupplierSaveButton,
													ElementType.Xpath);
											if (flag) {
												webDB.clickAnElement(RfqLocators.RfqlineSupplierSaveButton,
														ElementType.Xpath);
												logs.logging("INFo", "Supplier Is Saved");
												commonFunc.hardWait(3000);
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
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Create Rate Finalize In RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyRfqRateFinalize() {
		commonFunc.hardWait(3000);
		flag = webDB.waitForElement(RfqLocators.RfqRateFinalize, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(RfqLocators.RfqRateFinalize, ElementType.Xpath);
			logs.logging("INFO", "Rate Finalized Button Is Clicked");
			flag = webDB.waitForElement(RfqLocators.RateFinalMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "SOB Button Is Enabled");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Create SOB In RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifysRfqSobButton() {
		flag = webDB.waitForElement(RfqLocators.RateFinalMessage, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(RfqLocators.RateFinalMessage, ElementType.Xpath);
			logs.logging("INFO", "Alert Success Message Is Closed");
		}
		flag = webDB.waitForElementUntilGivenTime(RfqLocators.RfqHeaderSOB, ElementType.Xpath, 50);
		if (flag) {
			webDB.clickAnElement(RfqLocators.RfqHeaderSOB, ElementType.Xpath);
			logs.logging("INFO", "SOB Button Is Clicked");
			verifySobPercentageInRfq(commonFunc.getDataFromTestDataSheet("Perc_1"));
			verifySobPercentageSave();
			verifySobPagePost();
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Giving Share of Buisnesss Percentage For Supplier
	 * 
	 * @author Durai-RMITS
	 * @param Percent
	 */
	public void verifySobPercentageInRfq(String Percent) {
		flag = webDB.switchToFrame(RfqLocators.RfqSobFrame, ElementType.Xpath);
		logs.logging("INFO", "Switched To Frame");
		for (int i = 1; i <= 2; i++) {
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(RfqLocators.SobItemField(i), ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(RfqLocators.SobItemField(i), ElementType.Xpath);
				logs.logging("INFO", "SOB Header Item Is Clicked");
				commonFunc.hardWait(500);
				flag = webDB.waitForElement(RfqLocators.RfqSobSupplierField1, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(RfqLocators.RfqSobSupplierField1, ElementType.Xpath);
					logs.logging("INFO", "Supplier Field In SOB Is Clicked");
					flag = webDB.waitForElement(RfqLocators.RfqSobPercField1, ElementType.Xpath);
					if (flag) {
						webDB.doubleClickAnElement(RfqLocators.RfqSobPercField1, ElementType.Xpath);
						logs.logging("INFO", "SOB Percentage Field Is Clicked");
						webDB.clearText(RfqLocators.RfqSobPercInput, ElementType.Xpath);
						webDB.sendTextToAnElement(RfqLocators.RfqSobPercInput, Percent, ElementType.Xpath);

						flag = webDB.waitForElement(RfqLocators.RfqSobSupplierField2, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(RfqLocators.RfqSobSupplierField2, ElementType.Xpath);
							logs.logging("INFO", "Supplier Field In SOB Is Clicked");
							commonFunc.hardWait(500);
							flag = webDB.waitForElement(RfqLocators.RfqSobPercField2, ElementType.Xpath);
							if (flag) {
								webDB.doubleClickAnElement(RfqLocators.RfqSobPercField2, ElementType.Xpath);
								logs.logging("INFO", "SOB Percentage Field Is Clicked");
								webDB.clearText(RfqLocators.RfqSobPercInput2, ElementType.Xpath);
								webDB.sendTextToAnElement(RfqLocators.RfqSobPercInput2, Percent, ElementType.Xpath);
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Giving Share of Buisnesss Percentage For Supplier1
	 * 
	 * @author Durai-RMITS
	 * @param Percent
	 */
	public void verifySobPercentageinRfqSupplier1(String Percent) {
		flag = webDB.waitForElement(RfqLocators.RfqSobSupplierField1, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(RfqLocators.RfqSobSupplierField1, ElementType.Xpath);
			logs.logging("INFO", "Supplier Field In SOB Is Clicked");
			flag = webDB.waitForElement(RfqLocators.RfqSobPercField1, ElementType.Xpath);
			if (flag) {
				webDB.doubleClickAnElement(RfqLocators.RfqSobPercField1, ElementType.Xpath);
				logs.logging("INFO", "SOB Percentage Field Is Clicked");
				webDB.clearText(RfqLocators.RfqSobPercInput, ElementType.Xpath);
				webDB.sendTextToAnElement(RfqLocators.RfqSobPercInput, Percent, ElementType.Xpath);
			}
		}

		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Giving Share of Business Percentage For Supplier2
	 * 
	 * @author Durai-RMITS
	 * @param Percent
	 */
	public void verifySobPercentageinRfqSupplier2(String Percent) {
		flag = webDB.waitForElement(RfqLocators.RfqSobSupplierField2, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(RfqLocators.RfqSobSupplierField2, ElementType.Xpath);
			logs.logging("INFO", "Supplier Field In SOB Is Clicked");
			flag = webDB.waitForElement(RfqLocators.RfqSobPercField2, ElementType.Xpath);
			if (flag) {
				webDB.doubleClickAnElement(RfqLocators.RfqSobPercField2, ElementType.Xpath);
				logs.logging("INFO", "SOB Percentage Field Is Clicked");
				webDB.clearText(RfqLocators.RfqSobPercInput2, ElementType.Xpath);
				webDB.sendTextToAnElement(RfqLocators.RfqSobPercInput2, Percent, ElementType.Xpath);
			}
		}

		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Giving Share of Business Post
	 * 
	 * @author Durai-RMITS
	 */
	public void verifySobPagePost() {
		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(RfqLocators.RfqPost, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(RfqLocators.RfqPost, ElementType.Xpath);
			logs.logging("INFO", "DOcument Posted Successfully");
			flag = webDB.waitForElement(RfqLocators.RfqWorkflowFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Frame Is Displayed");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Giving Share of Business's Percentage Save
	 * 
	 * @author Durai-RMITS
	 */
	public void verifySobPercentageSave() {
		flag = webDB.waitForElement(RfqLocators.RfqSobSave, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(RfqLocators.RfqSobSave, ElementType.Xpath);
			logs.logging("INFO", "SOB line Is saved");
			commonFunc.hardWait(2000);
			webDB.pressEscapeKey();

		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Giving Cost For Item in line wise
	 * 
	 * @author Durai-RMITS
	 * @param Cost1
	 * @param Currency
	 * @param TrdDiscount
	 * @param SpclDiscount
	 * @param CashDiscount
	 */
	public void verifysSupplierCost1(String Cost1, String Currency, String TrdDiscount, String SpclDiscount,
			String CashDiscount, String HSN) {
		flag = webDB.waitForElement(RfqLocators.RfqInsideSupplierField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(RfqLocators.RfqInsideSupplierField, ElementType.Xpath);
			logs.logging("INFO", "Supplier Field 1 Is Clicked");
			getTextCurrencyInLine1();
			logs.logging("INFO", "Currency is:" + Currency);
			commonFunc.hardWait(1000);
			List<WebElement> LineNumber = webDB.getDriver().findElements(By.xpath(RfqLocators.SupLineNum));
			logs.logging("INFO", "Line Number Is:" + LineNumber.size());
			for (int i = 1; i <= LineNumber.size(); i++) {
				webDB.moveToElement(RfqLocators.BtmPage, ElementType.Xpath);
				flag = webDB.waitForElement(RfqLocators.ItemNameInside(i), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(RfqLocators.ItemNameInside(i), ElementType.Xpath);
					logs.logging("INFO", "Inside Item Field Is Clicked");
					webDB.moveToElement(RfqLocators.BtmPage, ElementType.Xpath);
					commonFunc.hardWait(2000);
					webDB.moveToElement(RfqLocators.QuoteQty, ElementType.Xpath);
					webDB.moveToElement(RfqLocators.SupCostField(i), ElementType.Xpath);
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(RfqLocators.SupCostField(i), ElementType.Xpath);
					if (flag) {
						commonFunc.hardWait(2000);
						webDB.doubleClickAnElement(RfqLocators.SupCostField(i), ElementType.Xpath);
						commonFunc.hardWait(2000);
						webDB.clickAnElement(RfqLocators.SupCostField(i), ElementType.Xpath);
						System.out.println("Waiting for Scroll");
						webDB.clearTextField(RfqLocators.RfqSupplierCostInput, ElementType.Xpath);
						webDB.doubleClickAnElement(RfqLocators.SupCostField(i), ElementType.Xpath);
						webDB.sendTextToAnElement(RfqLocators.RfqSupplierCostInput, Cost1, ElementType.Xpath);
						logs.logging("INFO", "Cost Is entered:" + Cost1);
						flag = webDB.waitForElement(RfqLocators.QuoteQty, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(RfqLocators.QuoteQty, ElementType.Xpath);
							logs.logging("INFO", "Quote Qty Is Clicked");
							commonFunc.hardWait(700);
							chooseTradeDiscForParticularSupllierItem(
									commonFunc.getDataFromTestDataSheet("Trd_Discount"), i);
							chooseSpclDiscForParticularSupllierItem(
									commonFunc.getDataFromTestDataSheet("Spcl_Discount"), i);
							chooseCashDiscForParticularSupllierItem(
									commonFunc.getDataFromTestDataSheet("Cash_Discount"), i);
							System.out.println("HSN Field");
							if (Currency.equals(commonFunc.getDataFromTestDataSheet("Cur_Inr"))) {
								chooseHsnForParticularSupllierItem(commonFunc.getDataFromTestDataSheet("Hsn_code"), i);
								
								verifysSupplierLineSave();
								commonFunc.hardWait(1000);
							} else {
								verifysSupplierLineSave();
								commonFunc.hardWait(1000);
							}
						}
					}
				}
			}
		}

		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Save Line
	 * 
	 * @author Durai-RMITS
	 */
	public void verifysSupplierLineSave() {
		flag = webDB.waitForElement(RfqLocators.Rfqsuplinesave, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(RfqLocators.Rfqsuplinesave, ElementType.Xpath);
			logs.logging("INFO", "Supplier1 Line Is Saved");
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(RfqLocators.RfqOverallSave, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Over All Save Is Checked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Giving Cost For Item in line wise
	 * 
	 * @author Durai-RMITS
	 * @param Cost2
	 * @param Currency
	 * @param TrdDiscount
	 * @param SpclDiscount
	 * @param CashDiscount
	 */
	public void verifysSupplierCost2(String Cost2, String Currency, String TrdDiscount, String SpclDiscount,
			String CashDiscount, String HSN) {
		commonFunc.hardWait(1000);
		flag = webDB.waitForElement(RfqLocators.RfqInsideSupplierField2, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(RfqLocators.RfqInsideSupplierField2, ElementType.Xpath);
			logs.logging("INFO", "Supplier Field 2 Is Clicked");
			Currency = getTextCurrencyInLine2();
			logs.logging("INFO", "Currency is:" + Currency);
			List<WebElement> LineNumber = webDB.getDriver().findElements(By.xpath(RfqLocators.SupLineNum));
			logs.logging("INFO", "Line Number Is:" + LineNumber.size());
			for (int i = 1; i <= LineNumber.size(); i++) {
				webDB.moveToElement(RfqLocators.BtmPage, ElementType.Xpath);
				flag = webDB.waitForElement(RfqLocators.ItemNameInside(i), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(RfqLocators.ItemNameInside(i), ElementType.Xpath);
					logs.logging("INFO", "Inside Item Field Is Clicked");
					webDB.moveToElement(RfqLocators.BtmPage, ElementType.Xpath);
					webDB.pressDownArrowKey();
					webDB.pressDownArrowKey();
					webDB.pressDownArrowKey();
					webDB.moveToElement(RfqLocators.BtmPage, ElementType.Xpath);
					commonFunc.hardWait(2000);
					webDB.moveToElement(RfqLocators.QuoteQty, ElementType.Xpath);
					webDB.moveToElement(RfqLocators.SupCostField(i), ElementType.Xpath);
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(RfqLocators.SupCostField(i), ElementType.Xpath);
					if (flag) {
						commonFunc.hardWait(2000);
						webDB.doubleClickAnElement(RfqLocators.SupCostField(i), ElementType.Xpath);
						commonFunc.hardWait(2000);
						webDB.clickAnElement(RfqLocators.SupCostField(i), ElementType.Xpath);
						System.out.println("Waiting for Scroll");
						webDB.clearTextField(RfqLocators.RfqSupplierCostInput, ElementType.Xpath);
						webDB.doubleClickAnElement(RfqLocators.SupCostField(i), ElementType.Xpath);
						webDB.sendTextToAnElement(RfqLocators.RfqSupplierCostInput, Cost2, ElementType.Xpath);
						logs.logging("INFO", "Cost Is entered:" + Cost2);
						flag = webDB.waitForElement(RfqLocators.QuoteQty, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(RfqLocators.QuoteQty, ElementType.Xpath);
							logs.logging("INFO", "Quote Qty Is Clicked");
							commonFunc.hardWait(700);
							chooseTradeDiscForParticularSupllierItem(
									commonFunc.getDataFromTestDataSheet("Trd_Discount"), i);
							chooseSpclDiscForParticularSupllierItem(
									commonFunc.getDataFromTestDataSheet("Spcl_Discount"), i);
							chooseCashDiscForParticularSupllierItem(
									commonFunc.getDataFromTestDataSheet("Cash_Discount"), i);
							if (Currency.equals(commonFunc.getDataFromTestDataSheet("Cur_Inr"))) {
								chooseHsnForParticularSupllierItem(commonFunc.getDataFromTestDataSheet("Hsn_code"), i);
								verifysSupplierLineSave();
								commonFunc.hardWait(1000);
							} else {
								verifysSupplierLineSave();
								commonFunc.hardWait(1000);
							}

						}
					}
				}
			}
		}
		Assert.assertTrue(flag);

	}

	/**
	 * This method used to Giving Trade Discount
	 * 
	 * @author Durai-RMITS
	 * @param TrdDiscount
	 */
	public void chooseTradeDiscForParticularSupllierItem(String TrdDiscount, int i) {
		flag = webDB.waitForElement(RfqLocators.TrdDiscField(i), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(RfqLocators.TrdDiscField(i), ElementType.Xpath);
			logs.logging("INFO", "Trade Discount Field Is Clicked");
			webDB.clearText(RfqLocators.RfqsuplineTrdDisinput, ElementType.Xpath);
			webDB.sendTextToAnElement(RfqLocators.RfqsuplineTrdDisinput, TrdDiscount, ElementType.Xpath);
			logs.logging("INFO", "Entered Trade Discount Is: " + TrdDiscount);
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Giving Trade Discount
	 * 
	 * @author Durai-RMITS
	 * @param SpclDiscount
	 */
	public void chooseSpclDiscForParticularSupllierItem(String SpclDiscount, int i) {
		flag = webDB.waitForElement(RfqLocators.SpclDiscField(i), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(RfqLocators.SpclDiscField(i), ElementType.Xpath);
			logs.logging("INFO", "Special Discount Field Is Clicked");
			webDB.clearText(RfqLocators.RfqsuplineSpclDisinput, ElementType.Xpath);
			webDB.sendTextToAnElement(RfqLocators.RfqsuplineSpclDisinput, SpclDiscount, ElementType.Xpath);
			logs.logging("INFO", "Entered Special Discount Is: " + SpclDiscount);
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Giving Trade Discount
	 * 
	 * @author Durai-RMITS
	 * @param CashDiscount
	 */
	public void chooseCashDiscForParticularSupllierItem(String CashDiscount, int i) {
		flag = webDB.waitForElement(RfqLocators.CashDiscField(i), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(RfqLocators.CashDiscField(i), ElementType.Xpath);
			logs.logging("INFO", "Cash Discount Field Is Clicked");
			webDB.clearText(RfqLocators.RfqsuplineCashlDisinput, ElementType.Xpath);
			webDB.sendTextToAnElement(RfqLocators.RfqsuplineCashlDisinput, CashDiscount, ElementType.Xpath);
			logs.logging("INFO", "Entered Cash Discount Is: " + CashDiscount);
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Giving HSN Code
	 * 
	 * @author Durai-RMITS
	 * @param HSN
	 */
	public void chooseHsnForParticularSupllierItem(String HSN, int i) {
		flag = webDB.waitForElement(RfqLocators.ItemNameInside(i), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(RfqLocators.ItemNameInside(i), ElementType.Xpath);
			flag = webDB.waitForElement(RfqLocators.BaseLineField(i), ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(RfqLocators.BaseLineField(i), ElementType.Xpath);
				flag = webDB.waitForElement(RfqLocators.HsnField(i), ElementType.Xpath);
				if (flag) {
					webDB.doubleClickAnElement(RfqLocators.HsnField(i), ElementType.Xpath);
					webDB.clickAnElement(RfqLocators.HsnField(i), ElementType.Xpath);
					webDB.doubleClickAnElement(RfqLocators.HsnInput, ElementType.Xpath);
					logs.logging("INFO", "Supplier HSN Field Is Clicked");
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(RfqLocators.RfqsuplineHsnlov, ElementType.Xpath);
					if (flag) {
						commonFunc.searchAndSelectValueInFilters(RfqLocators.RfqsuplineHsnlov, HSN);
						logs.logging("INFO", "Entered Hsn Code Is:" + HSN);
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Navigate Supplier Tab
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyNavigateToSupplierTab() {
		flag = webDB.waitForElement(RfqLocators.RfqSupplierTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(RfqLocators.RfqSupplierTab, ElementType.Xpath);
			logs.logging("INFO", "Supplier Tab Moved Sucessfully");
			flag = webDB.waitForElement(RfqLocators.RfqOverallSave, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Over All Save Is Finded");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Giving Send RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyRfqSendRfq() {
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(RfqLocators.RfqHeaderSendRfq, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(RfqLocators.RfqHeaderSendRfq, ElementType.Xpath);
			logs.logging("INFO", "RFQ Send Succesfully");
			commonFunc.hardWait(3000);
			flag = webDB.waitForElement(RfqLocators.RfqRateFinalize, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Rfq Rate Finalize Button is Checked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Clicking Pending Rfq Icon
	 * 
	 * @author Durai-RMITS
	 */
	public void pendingRfq() {
		flag = webDB.waitForElement(RfqLocators.RfqPending, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(RfqLocators.RfqPending, ElementType.Xpath);
			logs.logging("INFO", "Pending Button is Clicked");

			flag = webDB.waitForElement(RfqLocators.RfqPendingFrame, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(RfqLocators.RfqPendingFrame, ElementType.Xpath);
				logs.logging("INFO", "Switched to Frame");
				commonFunc.expandFindTable();
				flag = webDB.waitForElement(RfqLocators.RfqpendPRNumField, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "PR Number Field Is Searched");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to clicking Pending Rfq Icon and giving Purchase Request
	 * Number
	 * 
	 * @author Durai-RMITS
	 * @param Number
	 */
	public void pendingRfqPRNumber(String Number) {
//		commonFunc.expandFindTable();
		flag = webDB.waitForElement(RfqLocators.RfqpendPRNumField, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(RfqLocators.RfqpendPRNumField, Number, ElementType.Xpath);
			logs.logging("INFO", "PR number is:" + Number);
			flag = webDB.waitForElement(RfqLocators.RfqPendingSearchbutton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(RfqLocators.RfqPendingSearchbutton, ElementType.Xpath);
				logs.logging("INFO", "Search Button Is Clicked");
			}
		}

		Assert.assertTrue(flag);
	}

	/**
	 * This method used to clicking Create Rfq
	 * 
	 * @author Durai-RMITS
	 */
	public void createRfq() {
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(RfqLocators.RfqPendingSelectall, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(RfqLocators.RfqPendingSelectall, ElementType.Xpath);
			logs.logging("INFO", "Select all Is Checked");
			commonFunc.hardWait(2000);
			flag = webDB.waitForElementUntilGivenTime(RfqLocators.CreateRfq, ElementType.Xpath, 70);
			if (flag) {
				webDB.clickAnElement(RfqLocators.CreateRfq, ElementType.Xpath);
				logs.logging("INFO", "Create Rfq Is Clicked");
				flag = webDB.waitForElement(RfqLocators.CreateRfqDateField, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(RfqLocators.CreateRfqDateField, ElementType.Xpath);
					logs.logging("INFO", "Date Field Is Clicked");
					flag = webDB.waitForElement(RfqLocators.CreateRfqOK, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(RfqLocators.CreateRfqOK, ElementType.Xpath);
						logs.logging("INFO", "Rfq Is Created");
//						commonFunc.checkRefreshCellsDisappear();
//						commonFunc.hardWait(2000);
//						flag = webDB.waitForElement(RfqLocators.RfqSupplierAdd, ElementType.Xpath);
//						if (flag) {
//							logs.logging("INFO", "Rfq Send Button Is Checked");
//						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Click Add Icon and Fill The details Of Purchase Request
	 * 
	 * @author Durai-RMITS
	 * @param Location
	 * @param Unit
	 * @param PRType
	 * @param Prefix
	 * @param CPCCode
	 * @param Department
	 * @param Requester
	 * 
	 */
	public void clickAddIconFillDetailsOfPurchaseRequestRfq(String Location, String Unit, String PRType, String Prefix,
			String CPCCode, String Department, String Requester) {
		clickCreateIcon();
		VerifyClickingPendingCreatePRBtn();
		clickFrame();
		commonFunc.hardWait(2000);
		commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.PRLocationButton, Location);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		clickFrame();
		commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.PRCPCCodeButton, CPCCode);
		commonFunc.checkRefreshCellsDisappear();
		clickFrame();
		clickTickIconCreateRequestRfq();

	}

	/**
	 * This method used to click frame
	 * 
	 * @author Durai-RMITS
	 */
	private void clickFrame() {
		flag = webDB.waitForElement(PurchaseRequestLocators.PRAddFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(PurchaseRequestLocators.PRAddFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Frame");
			flag = webDB.waitForElement(CommonUiLocators.CreateTickIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Switched To Frame");
			}
		}
		Assert.assertTrue(flag);

	}

	/**
	 * This method used to click tick icon after fill form
	 * 
	 * @author Durai-RMITS
	 */
	private void clickTickIconCreateRequestRfq() {
		flag = webDB.waitForElement(CommonUiLocators.CreateTickIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.CreateTickIcon, ElementType.Xpath);
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(PurchaseRequestLocators.PurchaseIDHeader, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Create Tick Icon Clicked");
				String PRNum = webDB.getTextFromElement(PurchaseRequestLocators.PurchaseIDHeader, ElementType.Xpath);
				VariableHandler.setPRNumber(PRNum);
				logs.logging("INFO", "Purchase Request ID: " + VariableHandler.getPRNumber());
				if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to fill Item Quantity cost and Save line in Purchase Request
	 * 
	 * @author Durai-RMITS
	 * @param Item
	 * @param Qnty
	 * @param Cost
	 * @param addIcon
	 */
	public void fillItemsQuantityCostAndSaveLine(String Item, String Qnty, String Cost, boolean... addIcon) {
		if (addIcon != null) {
			flag = webDB.waitForElement(PurchaseRequestLocators.AddLineIcon, ElementType.Xpath);
			if (flag) {
				flag = webDB.clickAnElement(PurchaseRequestLocators.AddLineIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Add Lines Icon Clicked.");
				}
			}
		}
		searchAndSelectItems(Item);
		enterQuantityForLastItem(Qnty);
		enterCostForLastItem(Cost);
		saveLinesEntry();
	}

	/**
	 * This method used to Search and Select Item
	 * 
	 * @author Durai-RMITS
	 * @param Item
	 */
	private void searchAndSelectItems(String Item) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseRequestLocators.ItemDataLastRow(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseRequestLocators.ItemDataLastRow(), ElementType.Xpath);
			flag = webDB.waitForElementUntilGivenTime(PurchaseRequestLocators.ItemDataListIcon, ElementType.Xpath, 60);
			if (flag) {
				webDB.clickAnElement(PurchaseRequestLocators.ItemDataListIcon, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseRequestLocators.ItemDataPopupSearchBox, ElementType.Xpath);
				if (flag) {
					commonFunc.checkRefreshCellsDisappear();
					webDB.clearTextField(PurchaseRequestLocators.ItemDataPopupSearchBox, ElementType.Xpath);
					webDB.sendTextToAnElement(PurchaseRequestLocators.ItemDataPopupSearchBox, Item, ElementType.Xpath);
					flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.convertXPath(Item), ElementType.Xpath,
							60);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.clickAnElement(CommonUiLocators.convertXPath(Item), ElementType.Xpath);
						String value = webDB.getAttributeFromElement(PurchaseRequestLocators.ItemDataLastRowInput,
								ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
						logs.logging("INFO", "Get Text Item Name Value from Table Is: " + value);
						flag = value.contains(Item);
						if (flag) {
							logs.logging("INFO", Item + "- Item Searched and Selected");
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to giving Quantity
	 * 
	 * @author Durai-RMITS
	 * @param Quantity
	 */
	private void enterQuantityForLastItem(String Quantity) {
		commonFunc.checkRefreshCellsDisappear();
		JavascriptExecutor js = (JavascriptExecutor) webDB.getDriver();
		flag = webDB.waitForClickElement(PurchaseRequestLocators.QuantityDataLastRow(), ElementType.Xpath);
		if (flag) {
			webDB.scrollToAnElement(PurchaseRequestLocators.QuantityDataLastRow(), ElementType.Xpath);
			webDB.clickAnElement(PurchaseRequestLocators.QuantityDataLastRow(), ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseRequestLocators.QuantityDataLastRowInput, ElementType.Xpath);
			if (flag) {
				WebElement element = webDB.getDriver()
						.findElement(By.xpath(PurchaseRequestLocators.QuantityDataLastRowInput));
				try {
					js.executeScript("arguments[0].value='" + Quantity + "';", element);
					String value = webDB.getAttributeFromElement(PurchaseRequestLocators.QuantityDataLastRowInput,
							ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
					logs.logging("INFO", "Get Text Quantity Name Value from Table Is: " + value);
					flag = value.contains(Quantity);
					if (flag) {
						logs.logging("INFO", "Quantity Entered: " + Quantity);
					}
				} catch (Exception e) {
					flag = false;
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Giving Cost
	 * 
	 * @author Durai-RMITS
	 * @param Cost
	 */
	private void enterCostForLastItem(String Cost) {
		commonFunc.checkRefreshCellsDisappear();
		JavascriptExecutor js = (JavascriptExecutor) webDB.getDriver();
		flag = webDB.waitForClickElement(PurchaseRequestLocators.UnitCostDataLastRow(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseRequestLocators.UnitCostDataLastRow(), ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseRequestLocators.UnitCostDataLastRowInput, ElementType.Xpath);
			if (flag) {
				WebElement element1 = webDB.getDriver()
						.findElement(By.xpath(PurchaseRequestLocators.UnitCostDataLastRowInput));
				try {
					js.executeScript("arguments[0].value='" + Cost + "';", element1);
					String value = webDB.getAttributeFromElement(PurchaseRequestLocators.UnitCostDataLastRowInput,
							ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
					logs.logging("INFO", "Get Text Cost Name Value from Table Is: " + value);
					flag = value.contains(Cost);
					if (flag) {
						logs.logging("INFO", "Cost Entered: " + Cost);
					}
				} catch (Exception e) {
					flag = false;
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Save Lines Purchase Request
	 * 
	 * @author Durai-RMITS
	 */
	private void saveLinesEntry() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseRequestLocators.SaveLineBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseRequestLocators.SaveLineBtn, ElementType.Xpath);
			webDB.clickAnElement(PurchaseRequestLocators.SaveLineBtn, ElementType.Xpath);
			if (webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath)) {
				flag = webDB.javaScriptClickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				if (flag)
					logs.logging("INFO", "Save Item Entry Successfully");
			} else {
				flag = webDB.isElementDisplayed(CommonUiLocators.FailedNotifyMessage, ElementType.Xpath);
				if (flag) {
					flag = false;
					logs.logging("INFO", "Save Entry Item failed Notification Displayed");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Save Header and Post Purchase Request
	 * 
	 * @author Durai-RMITS
	 */
	public void saveHeaderAndClickPost() {
		commonFunc.hardWait(4000);
		flag = webDB.waitForElement(PurchaseRequestLocators.SaveHeaderBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseRequestLocators.SaveHeaderBtn, ElementType.Xpath);
			logs.logging("INFO", "Header Saved Successfully.");
			flag = webDB.waitForClickElement(PurchaseRequestLocators.PostBtn, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
//				webDB.moveToElement(PurchaseRequestLocators.PostBtn, ElementType.Xpath);
				webDB.clickAnElement(PurchaseRequestLocators.PostBtn, ElementType.Xpath);
				if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Waiting for Approval Dialog Box
	 * 
	 * @author Durai-RMITS
	 */
	public void waitingForApproval() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(RfqLocators.RfqWorkflowFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(RfqLocators.RfqWorkflowFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			flag = webDB.waitForElement(RfqLocators.PRForwardlov, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Lov Button Is Finded");
				commonFunc.searchAndSelectValueInFilters(RfqLocators.PRForwardlov,
						commonFunc.getDataFromTestDataSheet("AshokValue"));
				commonFunc.hardWait(2000);
				webDB.switchToFrame(RfqLocators.RfqWorkflowFrame, ElementType.Xpath);
				logs.logging("INFO", "Switched To WorkFlow Frame");
				flag = webDB.waitForElement(RfqLocators.PRworkflowOK, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(RfqLocators.PRworkflowOK, ElementType.Xpath);
					logs.logging("INFO", "Ok Button Is Clicked");
					webDB.switchToDefaultContent();
					commonFunc.hardWait(7000);
				}
			} else {
				flag = false;
				VariableHandler.setApprovalFlag(flag);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Forward Screen is Opening
	 * 
	 * @author Durai-RMITS
	 */
	public void forwardApprovalScreen() {
		commonFunc.hardWait(1000);
		commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
		approvalFunc.navigateToApprovals();
		flag = webDB.waitForElement(ApprovalPageLocators.UserNotifyIcon, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "User Notify Icon Is Clicked");
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Approve the document
	 * 
	 * @author Durai-RMITS
	 * @param Number
	 */
	public void filterDocumentsForPriceListApproval(String Number) {
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
	 * @author Durai-RMITS
	 * @param Number
	 */
	public void verifyApproveWithDocNumber(String Number) {
		commonFunc.hardWait(1000);
		flag = webDB.waitForClickElement(PricelistPageLocators.PRLISTSelectCheckBox(Number), ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(PricelistPageLocators.PRLISTSelectCheckBox(Number), ElementType.Xpath);
			logs.logging("INFO", "Select Box Is Checked");
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

	/**
	 * This method used to Save Header and Post Purchase Request
	 * 
	 * @author Durai-RMITS
	 */
	public String getTextCurrencyInLine1() {
		String HeaderCurrency1 = null;
		flag = webDB.waitForElement(RfqLocators.RfqSupplcurrField1, ElementType.Xpath);
		if (flag) {
			HeaderCurrency1 = webDB.getTextFromElement(RfqLocators.RfqSupplcurrField1, ElementType.Xpath);
			logs.logging("INFO", "Get Text Header Currency Is : " + HeaderCurrency1);
		}
		return HeaderCurrency1;
	}

	/**
	 * This method used to Save Header and Post Purchase Request
	 * 
	 * @author Durai-RMITS
	 */
	public String getTextCurrencyInLine2() {
		String HeaderCurrency2 = null;
		flag = webDB.waitForElement(RfqLocators.RfqSupplcurrField2, ElementType.Xpath);
		if (flag) {
			HeaderCurrency2 = webDB.getTextFromElement(RfqLocators.RfqSupplcurrField2, ElementType.Xpath);
			logs.logging("INFO", "Get Text Header Currency Is : " + HeaderCurrency2);
		}
		return HeaderCurrency2;
	}

	/**
	 * This method used to Add Lines Purchase Request
	 * 
	 * @author Durai-RMITS
	 */
	private void verifyMultipleLinesPR() {

		System.out.println("TEST");
		flag = webDB.waitForElement(RfqLocators.PRHeaderUnit, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(RfqLocators.PRHeaderUnit, ElementType.Xpath);
			logs.logging("INFO", "Unit Is Clicked");
			flag = webDB.waitForElement(RfqLocators.PurchaseIDHeader, ElementType.Xpath);
			if (flag) {
				String PRNum = webDB.getTextFromElement(RfqLocators.PurchaseIDHeader, ElementType.Xpath);
				VariableHandler.setPRNumber(PRNum);
				logs.logging("INFO", "Purchase Request ID: " + VariableHandler.getPRNumber());
				String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
				ExcelUtils excelUtils = new ExcelUtils();
				String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
						+ "ItemDetails.xlsx";
				PRMultipleline = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("PRMultipleline"));
				for (int i = 1; i < PRMultipleline.length; i++) {
					String ITEM = PRMultipleline[i][0];
					String QTY = PRMultipleline[i][1];
					if (!ITEM.equals(""))
						flag = webDB.waitForElement(RfqLocators.PRAdd, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(RfqLocators.PRAdd, ElementType.Xpath);
						logs.logging("INFO", "Add Button Is Clicked");
						flag = webDB.waitForElement(RfqLocators.ItemDataLastRow(), ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(RfqLocators.ItemDataLastRow(), ElementType.Xpath);
							commonFunc.searchAndSelectValueInFilters(RfqLocators.PRItemLov, ITEM);
							logs.logging("INFO", "Item Is Entered:" + ITEM);
							commonFunc.hardWait(4000);
							flag = webDB.waitForElement(RfqLocators.PRQtyField, ElementType.Xpath);
							if (flag) {
								webDB.doubleClickAnElement(RfqLocators.PRQtyField, ElementType.Xpath);
								logs.logging("INFO", "QTY Field Is Clicked");
								webDB.clearText(RfqLocators.PRQtyInput, ElementType.Xpath);
								webDB.sendTextToAnElement(RfqLocators.PRQtyInput, QTY, ElementType.Xpath);
								logs.logging("INFO", "Entered Qty Is:" + QTY);
								commonFunc.hardWait(2000);
								webDB.pressTabKey();
								commonFunc.hardWait(2000);
								flag = webDB.waitForElement(RfqLocators.PRLineSave, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(RfqLocators.PRLineSave, ElementType.Xpath);
									logs.logging("INFO", "Line Is Saved");
									commonFunc.hardWait(3000);
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
	 * PriceList Screen Validation For Location
	 * 
	 * @author Durai-RMITS
	 * @param location
	 */
	public void validateRfqLinesItemDomestic() {
		String[][] RFQLines = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "RFQ.xlsx";
		RFQLines = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("Domestic"));
		for (int i = 3; i < RFQLines.length; i++) {
			String ITEM = RFQLines[i][1];
			String SUPPLIER = RFQLines[i][2];
			String QTY = RFQLines[i][5];
			String Cost = RFQLines[i][6];
			String TD = RFQLines[i][8];
			String TDAmount = RFQLines[i][12];
			String TDValue = RFQLines[i][11];
			String SPCL = RFQLines[i][9];
			String SPCLAmount = RFQLines[i][14];
			String SPCLValue = RFQLines[i][13];
			String CASH = RFQLines[i][10];
			String CASHAmount = RFQLines[i][16];
			String CASHValue = RFQLines[i][15];
			String SeqNumber = Integer.toString(i - 2);
			if (!ITEM.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbItemColumnName")), ITEM);
				logs.logging("INFO", "Header Items Details Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbItemQtyColumnName")), QTY);
				logs.logging("INFO", "Header Quantity Details Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbItemCostColumnName")), Cost);
				logs.logging("INFO", "Header Cost Details Matched With DataBase Values");
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbTradeDiscountColumnName")), TD);
				logs.logging("INFO", "Header Trade Discount Details Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbTradeDisAmountColumnName")),
						TDAmount);
				logs.logging("INFO", "Header Trade Discount Amount Details Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbTradeAssValueColumnName")),
						TDValue);
				logs.logging("INFO", "Header Trade Assesable Value Details Matched With DataBase Values");
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbSpclDiscountColumnName")), SPCL);
				logs.logging("INFO", "Header Special Discount Details Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbSpclDisAmountColumnName")),
						SPCLAmount);
				logs.logging("INFO", "Header Special Discount Amount Details Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbSpclAssValueColumnName")),
						SPCLValue);
				logs.logging("INFO", "Header Special Assesable Value Details Matched With DataBase Values");

				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbCashDiscountColumnName")), CASH);
				logs.logging("INFO", "Header Cash Discount Details Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbCashDisAmountColumnName")),
						CASHAmount);
				logs.logging("INFO", "Header Cash Discount Amount Details Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbCashAssValueColumnName")),
						CASHValue);
				logs.logging("INFO", "Header Cash Assesable Value Details Matched With DataBase Values");
			} else {
				break;
			}
		}
	}

	/**
	 * PriceList Screen Validation For Location
	 * 
	 * @author Durai-RMITS
	 * @param location
	 */
	public void validateRfqLinesItemDomestic1() {
		String[][] RFQLines = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "RFQ.xlsx";
		RFQLines = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("Domestic"));
		for (int j = 1; j < RFQLines.length; j++) {
			if (RFQLines[j][0].equals(commonFunc.getDataFromTestDataSheet("Domestic2"))) {
				for (int i = j + 2; i < RFQLines.length; i++) {
					String ITEM = RFQLines[i][1];
					String SUPPLIER = RFQLines[i][2];
					String QTY = RFQLines[i][5];
					String Cost = RFQLines[i][6];
					String TD = RFQLines[i][8];
					String TDAmount = RFQLines[i][12];
					String TDValue = RFQLines[i][11];
					String SPCL = RFQLines[i][9];
					String SPCLAmount = RFQLines[i][14];
					String SPCLValue = RFQLines[i][13];
					String CASH = RFQLines[i][10];
					String CASHAmount = RFQLines[i][16];
					String CASHValue = RFQLines[i][15];

					String SeqNumber = Integer.toString(i - 10);
					if (!ITEM.equals("")) {
						commonFunc.checkRefreshCellsDisappear();
						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbItemColumnName")),
								ITEM);
						logs.logging("INFO", "Header Items Details Matched With DataBase Values");

						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbItemQtyColumnName")),
								QTY);
						logs.logging("INFO", "Header Quantity Details Matched With DataBase Values");

						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbItemCostColumnName")),
								Cost);
						logs.logging("INFO", "Header Cost Details Matched With DataBase Values");
						commonFunc.hardWait(2000);
						commonFunc.checkRefreshCellsDisappear();
						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbTradeDiscountColumnName")),
								TD);
						logs.logging("INFO", "Header Trade Discount Details Matched With DataBase Values");

						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbTradeDisAmountColumnName")),
								TDAmount);
						logs.logging("INFO", "Header Trade Discount Amount Details Matched With DataBase Values");

						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbTradeAssValueColumnName")),
								TDValue);
						logs.logging("INFO", "Header Trade Assesable Value Details Matched With DataBase Values");
						commonFunc.checkRefreshCellsDisappear();
						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbSpclDiscountColumnName")),
								SPCL);
						logs.logging("INFO", "Header Special Discount Details Matched With DataBase Values");

						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbSpclDisAmountColumnName")),
								SPCLAmount);
						logs.logging("INFO", "Header Special Discount Amount Details Matched With DataBase Values");

						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbSpclAssValueColumnName")),
								SPCLValue);
						logs.logging("INFO", "Header Special Assesable Value Details Matched With DataBase Values");

						commonFunc.checkRefreshCellsDisappear();
						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbCashDiscountColumnName")),
								CASH);
						logs.logging("INFO", "Header Cash Discount Details Matched With DataBase Values");

						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbCashDisAmountColumnName")),
								CASHAmount);
						logs.logging("INFO", "Header Cash Discount Amount Details Matched With DataBase Values");

						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbCashAssValueColumnName")),
								CASHValue);
						logs.logging("INFO", "Header Cash Assesable Value Details Matched With DataBase Values");
					} else {
						break;
					}
				}
			}
		}
	}

	/**
	 * PriceList Screen Validation For Location
	 * 
	 * @author Durai-RMITS
	 * @param location
	 */
	public void validateRfqLinesItemImport() {
		String[][] RFQLines = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "RFQ.xlsx";
		RFQLines = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("Import"));
		for (int i = 3; i < RFQLines.length; i++) {
			String ITEM = RFQLines[i][1];
			String SUPPLIER = RFQLines[i][2];
			String QTY = RFQLines[i][5];
			String Cost = RFQLines[i][6];
			String TD = RFQLines[i][8];
			String TDAmount = RFQLines[i][12];
			String TDValue = RFQLines[i][11];
			String SPCL = RFQLines[i][9];
			String SPCLAmount = RFQLines[i][14];
			String SPCLValue = RFQLines[i][13];
			String CASH = RFQLines[i][10];
			String CASHAmount = RFQLines[i][16];
			String CASHValue = RFQLines[i][15];

			String SeqNumber = Integer.toString(i - 2);
			if (!ITEM.equals("")) {
				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbItemColumnName")), ITEM);
				logs.logging("INFO", "Header Items Details Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbItemQtyColumnName")), QTY);
				logs.logging("INFO", "Header Quantity Details Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbItemCostColumnName")), Cost);
				logs.logging("INFO", "Header Cost Details Matched With DataBase Values");
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbTradeDiscountColumnName")), TD);
				logs.logging("INFO", "Header Trade Discount Details Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbTradeDisAmountColumnName")),
						TDAmount);
				logs.logging("INFO", "Header Trade Discount Amount Details Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbTradeAssValueColumnName")),
						TDValue);
				logs.logging("INFO", "Header Trade Assesable Value Details Matched With DataBase Values");
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbSpclDiscountColumnName")), SPCL);
				logs.logging("INFO", "Header Special Discount Details Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbSpclDisAmountColumnName")),
						SPCLAmount);
				logs.logging("INFO", "Header Special Discount Amount Details Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbSpclAssValueColumnName")),
						SPCLValue);
				logs.logging("INFO", "Header Special Assesable Value Details Matched With DataBase Values");

				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbCashDiscountColumnName")), CASH);
				logs.logging("INFO", "Header Cash Discount Details Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbCashDisAmountColumnName")),
						CASHAmount);
				logs.logging("INFO", "Header Cash Discount Amount Details Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(),
						SeqNumber, SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbCashAssValueColumnName")),
						CASHValue);
				logs.logging("INFO", "Header Cash Assesable Value Details Matched With DataBase Values");
			} else {
				break;
			}
		}
	}

	/**
	 * PriceList Screen Validation For Location
	 * 
	 * @author Durai-RMITS
	 * @param location
	 */
	public void validateRfqLinesItemImport1() {
		String[][] RFQLines = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "RFQ.xlsx";
		RFQLines = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("Import"));
		for (int k = 1; k < RFQLines.length; k++) {
			if (RFQLines[k][0].equals(commonFunc.getDataFromTestDataSheet("Import2"))) {
				for (int i = k + 2; i < RFQLines.length; i++) {
					String ITEM = RFQLines[i][1];
					String SUPPLIER = RFQLines[i][2];
					String QTY = RFQLines[i][5];
					String Cost = RFQLines[i][6];
					String TD = RFQLines[i][8];
					String TDAmount = RFQLines[i][12];
					String TDValue = RFQLines[i][11];
					String SPCL = RFQLines[i][9];
					String SPCLAmount = RFQLines[i][14];
					String SPCLValue = RFQLines[i][13];
					String CASH = RFQLines[i][10];
					String CASHAmount = RFQLines[i][16];
					String CASHValue = RFQLines[i][15];
					String SeqNumber = Integer.toString(i - 10);
					if (!ITEM.equals("")) {
						commonFunc.checkRefreshCellsDisappear();
						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbItemColumnName")),
								ITEM);
						logs.logging("INFO", "Header Items Details Matched With DataBase Values");

						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbItemQtyColumnName")),
								QTY);
						logs.logging("INFO", "Header Quantity Details Matched With DataBase Values");

						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbItemCostColumnName")),
								Cost);
						logs.logging("INFO", "Header Cost Details Matched With DataBase Values");
						commonFunc.hardWait(2000);
						commonFunc.checkRefreshCellsDisappear();
						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbTradeDiscountColumnName")),
								TD);
						logs.logging("INFO", "Header Trade Discount Details Matched With DataBase Values");

						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbTradeDisAmountColumnName")),
								TDAmount);
						logs.logging("INFO", "Header Trade Discount Amount Details Matched With DataBase Values");

						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbTradeAssValueColumnName")),
								TDValue);
						logs.logging("INFO", "Header Trade Assesable Value Details Matched With DataBase Values");
						commonFunc.checkRefreshCellsDisappear();
						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbSpclDiscountColumnName")),
								SPCL);
						logs.logging("INFO", "Header Special Discount Details Matched With DataBase Values");

						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbSpclDisAmountColumnName")),
								SPCLAmount);
						logs.logging("INFO", "Header Special Discount Amount Details Matched With DataBase Values");

						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbSpclAssValueColumnName")),
								SPCLValue);
						logs.logging("INFO", "Header Special Assesable Value Details Matched With DataBase Values");

						commonFunc.checkRefreshCellsDisappear();
						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbCashDiscountColumnName")),
								CASH);
						logs.logging("INFO", "Header Cash Discount Details Matched With DataBase Values");

						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbCashDisAmountColumnName")),
								CASHAmount);
						logs.logging("INFO", "Header Cash Discount Amount Details Matched With DataBase Values");

						verifyAssertionBothValues(
								dbCommonFunctions.RFQQueryForItemSupplier(VariableHandler.getRfqDocNum(), SeqNumber,
										SUPPLIER, commonFunc.getDataFromTestDataSheet("RFQDbCashAssValueColumnName")),
								CASHValue);
						logs.logging("INFO", "Header Cash Assesable Value Details Matched With DataBase Values");
					} else {
						break;
					}
				}
			}
		}
	}

	/**
	 * Check Assertion Both Values
	 * 
	 * @author Durai-RMITS
	 * @param Actual
	 * @param Expected
	 */
	public void verifyAssertionBothValues(String Actual, String Expected) {
		logs.logging("INFO", "Actual DataBase Value Is: " + Actual);
		logs.logging("INFO", "Expected Value Is: " + Expected);
		flag = Actual.equals(Expected);
		if (flag) {
			logs.logging("INFO", "Verified Actual DataBase Value : " + Actual + " And Expected Values : " + Expected);
		} else {
			logs.logging("INFO",
					"Mismatch With Actual DataBase Value : " + Actual + " And Expected Values " + Expected);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To PO Filter All Field Working
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreationOfRfqFilterAllFieldWorking() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateRfqPage();
//		verifyDateAfterAndBefore();
		verifyAmendFilterSearchSOB(RfqLocators.LocId, commonFunc.getDataFromTestDataSheet("Loc_100"), "Loc. ID",
				commonFunc.getDataFromTestDataSheet("HeaderSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(RfqLocators.Unit, commonFunc.getDataFromTestDataSheet("Unit_100"), "Unit",
				commonFunc.getDataFromTestDataSheet("HeaderSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(RfqLocators.RFQNO, commonFunc.getDataFromTestDataSheet("RFQNumber"), "RFQ  No.",
				commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
//		verifyAmendFilterSearchSOB(RfqLocators.SupplierNameLovButton,
//				commonFunc.getDataFromTestDataSheet("ARAVIND AGENCY"), "Supplier Name",
//				commonFunc.getDataFromTestDataSheet("LineSearchType"),
//				commonFunc.getDataFromTestDataSheet("LovButton"));
//		verifyAmendFilterSearchSOB(RfqLocators.ItemId, commonFunc.getDataFromTestDataSheet("Rfqitem"), "Item ID",
//				commonFunc.getDataFromTestDataSheet("LineSearchType"),
//				commonFunc.getDataFromTestDataSheet("LovButton"));
//		verifyAmendFilterSearchSOB(RfqLocators.ItemDesc, commonFunc.getDataFromTestDataSheet("Rfqitem"), "Item Desc.",
//				commonFunc.getDataFromTestDataSheet("LineSearchType"),
//				commonFunc.getDataFromTestDataSheet("SendTextInput"));
		verifyAmendFilterSearchSOB(RfqLocators.RFQ_Status, commonFunc.getDataFromTestDataSheet("ApproveStatus"), "Status",
				commonFunc.getDataFromTestDataSheet("HeaderSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
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
	 * Verify Date From And To
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDateAfterAndBefore() {
		flag = webDB.waitForElement(DirectGrnPageLocators.GRNDtFrom, ElementType.Xpath);
		webDB.sendTextToAnElement(DirectGrnPageLocators.GRNDtFrom, commonFunc.getDataFromTestDataSheet("EffFrom"),
				ElementType.Xpath);
		clickSearchButton();
		commonFunc.hardWait(3000);
		verifyAmendTableValueAfterDate(5, commonFunc.getDataFromTestDataSheet("EffFrom"));
		clearSearchFilter();
		flag = webDB.waitForElement(DirectGrnPageLocators.GRNDtTo, ElementType.Xpath);
		webDB.sendTextToAnElement(DirectGrnPageLocators.GRNDtTo, commonFunc.getDataFromTestDataSheet("EffTo"),
				ElementType.Xpath);
		clickSearchButton();
		commonFunc.hardWait(3000);
		verifyAmendTableValueBeforeDate(5, commonFunc.getDataFromTestDataSheet("EffTo"));
		clearSearchFilter();
//		flag = webDB.waitForElement(PurchaseOrderPageLocators.RequestDtFrom, ElementType.Xpath);
//		webDB.sendTextToAnElement(PurchaseOrderPageLocators.RequestDtFrom,
//				commonFunc.getDataFromTestDataSheet("EffFrom"), ElementType.Xpath);
//		clickLineSearchButton();
//		commonFunc.hardWait(3000);
//		verifyLineAmendTableValueAfterDate(23, commonFunc.getDataFromTestDataSheet("EffFrom"));
//		clearSearchFilter();
//		flag = webDB.waitForElement(PurchaseOrderPageLocators.RequestDtTo, ElementType.Xpath);
//		webDB.sendTextToAnElement(PurchaseOrderPageLocators.RequestDtTo, commonFunc.getDataFromTestDataSheet("EffTo"),
//				ElementType.Xpath);
//		clickLineSearchButton();
//		commonFunc.hardWait(3000);
//		verifyLineAmendTableValueBeforeDate(23, commonFunc.getDataFromTestDataSheet("EffTo"));
//		clearSearchFilter();
	}

	/**
	 * This Method Is Used To Click Search Button
	 * 
	 * @author Deepan-GWL
	 */
	public void clickSearchButton() {
		flag = webDB.waitForElement(RfqLocators.HeaderCheckBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header CheckBox Is Displayed");
			webDB.clickAnElement(RfqLocators.HeaderCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(RfqLocators.RFQSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(RfqLocators.RFQSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Search Button Is Clicked");

				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(RfqLocators.AmendFindTable, ElementType.Xpath);
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
		flag = webDB.waitForElement(RfqLocators.LineCheckBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Line CheckBox Is Displayed");
			webDB.clickAnElement(RfqLocators.LineCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(RfqLocators.RFQSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(RfqLocators.RFQSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Search Button Is Clicked");
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(RfqLocators.AmendLineFindTable, ElementType.Xpath);
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
		List<WebElement> elements = webDB.getDriver().findElements(By.xpath(RfqLocators.RFQAmendFindTableData(i)));
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
	 * This Method Is Used To Verify Table Value
	 * 
	 * @author Deepan-GWL
	 * @param i
	 * @param Value
	 */
	public void verifyLineAmendTableValue(String i, String Value) {
		List<WebElement> elements = webDB.getDriver().findElements(By.xpath(RfqLocators.RFQAmendLineFindTableData(i)));
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
	 * This Method Is Used To Verify Clear Search Filter
	 * 
	 * @author Deepan-GWL
	 */
	public void clearSearchFilter() {
		commonFunc.hardWait(1000);
		System.out.println("Test2");
		flag = webDB.waitForElement(RfqLocators.ClearButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(RfqLocators.ClearButton, ElementType.Xpath);
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
	public void verifyAmendTableValueAfterDate(int i, String Value) {
		List<WebElement> elements = webDB.getDriver().findElements(By.xpath(RfqLocators.AmendFindTableData(i)));
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate date1 = LocalDate.parse(element.getText(), formatter);
				LocalDate date2 = LocalDate.parse(Value, formatter);
				flag = date1.isAfter(date2);
				if (!flag) {
					if (date1.equals(date2)) {
					} else {
						break;
					}
				}
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
	public void verifyAmendTableValueBeforeDate(int i, String Value) {
		List<WebElement> elements = webDB.getDriver().findElements(By.xpath(RfqLocators.AmendFindTableData(i)));
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate date1 = LocalDate.parse(element.getText(), formatter);
				LocalDate date2 = LocalDate.parse(Value, formatter);
				flag = date1.isBefore(date2);
				if (!flag) {
					if (date1.equals(date2)) {

					} else {
						break;
					}
				}
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
	public void verifyLineAmendTableValueAfterDate(int i, String Value) {
		List<WebElement> elements = webDB.getDriver().findElements(By.xpath(RfqLocators.AmendLineFindTableData(i)));
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate date1 = LocalDate.parse(element.getText(), formatter);
				LocalDate date2 = LocalDate.parse(Value, formatter);
				flag = date1.isAfter(date2);
				if (!flag) {
					if (date1.equals(date2)) {
					} else {
						break;
					}
				}
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
	public void verifyLineAmendTableValueBeforeDate(int i, String Value) {
		List<WebElement> elements = webDB.getDriver().findElements(By.xpath(RfqLocators.AmendLineFindTableData(i)));
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate date1 = LocalDate.parse(element.getText(), formatter);
				LocalDate date2 = LocalDate.parse(Value, formatter);
				flag = date1.isBefore(date2);
				if (!flag) {
					if (date1.equals(date2)) {

					} else {
						break;
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click create icon - left add icon
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickCreateIcon() {
		flag = webDB.waitForElement(CommonUiLocators.PurchaseRequestAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PurchaseRequestAddIcon, ElementType.Xpath);
			logs.logging("INFO", "Left Side Add Icon is Displayed and Clicked");
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used to click create icon - left add icon
	 * 
	 * @author Sakthi-GWL
	 */
	private void VerifyClickingPendingCreatePRBtn() {
		flag = webDB.waitForElement(PurchaseRequestLocators.PendingPR, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseRequestLocators.PendingPR, ElementType.Xpath);
			logs.logging("INFO", "Pending PR Button Is clicked");
		}

	}
}
