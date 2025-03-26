package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import commonfunctions.CommonFunctions;
import commonfunctions.ItemDetailsHandler;
import commonfunctions.VariableHandler;
import commonfunctions.qualitycontrol.InspectionDocumentFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.GrnLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import commonlocators.supplychainmanagement.SalesInvoiceLocators;
import commonlocators.supplychainmanagement.SalesOrderLocators;
import utils.ExcelUtils;
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
	static ItemDetailsHandler itemDetailHandler = new ItemDetailsHandler();
	static InspectionDocumentFunctions qcFunc = new InspectionDocumentFunctions();
	static MaterialReceiptVoucherFunctions mrvFunc = new MaterialReceiptVoucherFunctions();
	static SalesInvoiceFunctions saleInvoiceFunc = new SalesInvoiceFunctions();
	static SalesOrderFunctions saleOrderFunc = new SalesOrderFunctions();

	/**
	 * This Methods Is Used To Click Pending PO and Search PO Number in Header
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void clickPendingPoAndSearchPoNo() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(GrnLocators.ClickPendingButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(GrnLocators.ClickPendingButton, ElementType.Xpath);
			webDB.clickAnElement(GrnLocators.ClickPendingButton, ElementType.Xpath);
			logs.logging("INFO", "Pending Button Clicked");
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(GrnLocators.PendingPO, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.PendingPO, ElementType.Xpath);
				logs.logging("INFO", "Pending PO Button Clicked");
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(GrnLocators.PendingPOIframe, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(GrnLocators.PendingPOHeaderPONumberInput,
							VariableHandler.getPONumber());
					logs.logging("INFO", "PO Number Succesfully search and Selected");
					commonFunc.hardWait(2000);
					webDB.switchToFrame(GrnLocators.PendingPOIframe, ElementType.Xpath);
					flag = webDB.waitForElement(GrnLocators.PendingPOHeaderSearchButton, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(GrnLocators.PendingPOHeaderSearchButton, ElementType.Xpath);
						logs.logging("INFO", "Document Searched successfully");
						webDB.switchToDefaultContent();
						flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Able Select the Document");
						} else {
							logs.logging("INFO", "Not Able Select the Document");
						}
					}

				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * This Methods Is Used To Click Fully selected
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void clickSelectAllButton() {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(GrnLocators.PendingPOIframe, ElementType.Xpath);
			List<WebElement> CheckBox = webDB.getDriver()
					.findElements(By.xpath(PurchaseOrderPageLocators.PendingPR_SelectAllCheckBox));
			for (WebElement element : CheckBox) {
				try {
					if (element.isDisplayed()) {
						element.click();
						flag = true;
						break;
					}
				} catch (Exception e) {

				}
			}
			logs.logging("INFO", " Document Fully Selected");
			flag = webDB.waitForElement(GrnLocators.PendingPOCreateGRN, ElementType.Xpath);
			if (flag) {
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", " Document Fully Selected And Create GRN Button Shown");
				} else {
					logs.logging("INFO", " Document Not Selected And Create GRN Button Not Shown");
				}
			}
		}

		assertTrue(flag);

	}

	/**
	 * This Methods Is Used After select the document to create GRN
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void createGrn() {
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(GrnLocators.PendingPOIframe, ElementType.Xpath);
			flag = webDB.waitForElement(GrnLocators.PendingPOCreateGRN, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				webDB.clickAnElement(GrnLocators.PendingPOCreateGRN, ElementType.Xpath);
				logs.logging("INFO", "GRN Document Created");
				flag = webDB.waitForElement(GrnLocators.PendingPOClickSupplierBillnumber, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Supplier Bill Number And DC Field Shown");
				} else {
					logs.logging("INFO", "Supplier Bill Number And DC Field Not Shown");

				}
			}
		}

		assertTrue(flag);
	}

	/**
	 * This Methods Is Used After create the GRN to Give Supplier Bill Number And DC
	 * Number
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void supplierBillNumberAndDcNumber() {
		flag = webDB.waitForElement(GrnLocators.PendingPOClickSupplierBillnumber, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(GrnLocators.PendingPOClickSupplierBillnumber, VariableHandler.getPONumber(),
					ElementType.Xpath);
			logs.logging("INFO", "Supplier Bill Number Successfully entered");
			flag = webDB.waitForElement(GrnLocators.PendingPOClickDCNumber, ElementType.Xpath);
			if (flag) {
				webDB.sendTextToAnElement(GrnLocators.PendingPOClickDCNumber, VariableHandler.getPONumber(),
						ElementType.Xpath);
				logs.logging("INFO", "Supplier DC Number Successfully entered");
				flag = webDB.waitForElement(GrnLocators.PendingPOClickOKButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(GrnLocators.PendingPOClickOKButton, ElementType.Xpath);
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
				}
			}
		}

		assertTrue(flag);
	}

	/**
	 * This Methods Is Used After Give all Details to Click overall save and Forward
	 * the Approval User
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void overAllSaveAndPost() {
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());
			flag = webDB.waitForElement(GrnLocators.OverAllSave, ElementType.Xpath);
			if (flag)
				webDB.clickAnElement(GrnLocators.OverAllSave, ElementType.Xpath);
			logs.logging("INFO", "Document Successfully Saved");
			commonFunc.hardWait(2000);
			flag = webDB.waitForClickElement(GrnLocators.PostButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.PostButton, ElementType.Xpath);
				logs.logging("INFO", "Document Successfully posted");
				flag = (webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 10)
						|| webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath));
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Add Lot and Serial Number for Multiple Item
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void multipleLotAndSerialItem() throws IOException {
		flag = webDB.waitForElement(GrnLocators.LotSerailButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Successfully Created GRN and Entered GRN Transaction Screen");
			commonFunc.hardWait(3000);
			webDB.clickAnElement(GrnLocators.LotSerailButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			logs.logging("INFO", "LOT/SERIAL Button Clicked");
			flag = webDB.waitForElement(GrnLocators.LotSerialIframe, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(GrnLocators.LotSerialIframe, ElementType.Xpath);
				String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
				ExcelUtils excelUtils = new ExcelUtils();
				String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
						+ "ItemDetails.xlsx";
				itemmultiple = excelUtils.readAllDataInExcel(Filepath,
						webDB.getDataFromProperties("POMultipleItemDetailsSheetName"));
				for (int i = 1; i < itemmultiple.length; i++) {
					String ITEM_NAME = itemmultiple[i][0];
					String LOT_SERIAL = itemmultiple[i][3];
					String LOT_NUMBER = itemmultiple[i][4];
					String SERIAL_NUMBER = itemmultiple[i][5];
					if (!ITEM_NAME.equals("")) {
						if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Na"))) {
							continue;
						}
						commonFunc.hardWait(5000);
						flag = webDB.waitForElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(3000);
							flag = webDB.waitForElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
								webDB.switchToDefaultContent();
								flag = webDB.waitForElement(GrnLocators.LotserialitemsInput, ElementType.Xpath);
								if (flag) {
									if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Lot"))) {
										inputLotNumber(ITEM_NAME, LOT_NUMBER);
									} else if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Serial"))) {
										inputSerialNumber(ITEM_NAME, SERIAL_NUMBER);
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
	 * This Methods Method to handle Toast Message Closed
	 * 
	 * @author PremKumar.K-RMITS
	 */
	private void closeToastIfDisplayed() {
		commonFunc.hardWait(3000);
		if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
			webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			logs.logging("INFO", "Toast Alert Successfully Catched");
		}
	}

	/**
	 * This Methods Method to handle Apportion LotSerial Quantity
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void lotSerialQtyApportion() {
		if (flag) {
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(GrnLocators.LotSerialQtyField, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.LotSerialQtyField, ElementType.Xpath);
				logs.logging("INFO", "LotSerial Qty Field Clicked Successfully");
				commonFunc.hardWait(3000);
				flag = webDB.waitForElement(GrnLocators.ApportionLSQty, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(GrnLocators.ApportionLSQty, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(1000);
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
					if (flag) {
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Multiple Lot serial Item for Rate Contract Item
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void multpleLotSerialWithRateContractItem() {
		flag = webDB.waitForElement(GrnLocators.LotSerailButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Successfully Created GRN and Entered GRN Transaction Screen");
			commonFunc.hardWait(3000);
			webDB.clickAnElement(GrnLocators.LotSerailButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (flag) {
				logs.logging("INFO", "LOT/SERIAL Button Clicked");
				flag = webDB.waitForElement(GrnLocators.LotSerialIframe, ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(GrnLocators.LotSerialIframe, ElementType.Xpath);
					String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
					ExcelUtils excelUtils = new ExcelUtils();
					String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
							+ "ItemDetails.xlsx";
					itemmultiple = excelUtils.readAllDataInExcel(Filepath,
							webDB.getDataFromProperties("POwithRateContractItemGeneral"));
					for (int i = 1; i < itemmultiple.length; i++) {
						String ITEM_NAME = itemmultiple[i][0];
						String LOT_SERIAL = itemmultiple[i][4];
						String LOT_NUMBER = itemmultiple[i][5];
						String SERIAL_NUMBER = itemmultiple[i][6];
						if (!ITEM_NAME.equals("")) {
							if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Na"))) {
								continue;
							}
							flag = webDB.waitForElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
								commonFunc.checkRefreshCellsDisappear();
								commonFunc.hardWait(3000);
								flag = webDB.waitForElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
									webDB.switchToDefaultContent();
									flag = webDB.waitForElement(GrnLocators.LotserialitemsInput, ElementType.Xpath);
									if (flag) {
										if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Lot"))) {
											inputLotNumber(ITEM_NAME, LOT_NUMBER);
										} else if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Serial"))) {
											inputSerialNumber(ITEM_NAME, SERIAL_NUMBER);
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
	 * This Methods Method to handle Multiple Lot serial RateContract Item Material
	 * Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void multipleLotSerialItemWithMaterialTypeForRatecontractItem() {
		flag = webDB.waitForElement(GrnLocators.LotSerailButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Successfully Created GRN and Entered GRN Transaction Screen");
			commonFunc.hardWait(3000);
			webDB.clickAnElement(GrnLocators.LotSerailButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			logs.logging("INFO", "LOT/SERIAL Button Clicked");
			flag = webDB.waitForElement(GrnLocators.LotSerialIframe, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(GrnLocators.LotSerialIframe, ElementType.Xpath);
				String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
				ExcelUtils excelUtils = new ExcelUtils();
				String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
						+ "ItemDetails.xlsx";
				itemmultiple = excelUtils.readAllDataInExcel(Filepath,
						webDB.getDataFromProperties("POWithRateContractItemMaterial"));
				for (int i = 1; i < itemmultiple.length; i++) {
					String ITEM_NAME = itemmultiple[i][0];
					String LOT_SERIAL = itemmultiple[i][4];
					String LOT_NUMBER = itemmultiple[i][5];
					String SERIAL_NUMBER = itemmultiple[i][6];
					if (!ITEM_NAME.equals("")) {
						if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Na"))) {
							continue;
						}
						flag = webDB.waitForElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(3000);
							flag = webDB.waitForElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
								webDB.switchToDefaultContent();
								flag = webDB.waitForElement(GrnLocators.LotserialitemsInput, ElementType.Xpath);
								if (flag) {
									if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Lot"))) {
										inputLotNumber(ITEM_NAME, LOT_NUMBER);
									} else if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Serial"))) {
										inputSerialNumber(ITEM_NAME, SERIAL_NUMBER);
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
	 * This Methods Method to handle Multiple Lot serial Item for General Type
	 * Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void multipleLotandSerialWithGeneralType() {
		flag = webDB.waitForElement(GrnLocators.LotSerailButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Successfully Created GRN and Entered GRN Transaction Screen");
			commonFunc.hardWait(3000);
			webDB.clickAnElement(GrnLocators.LotSerailButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			logs.logging("INFO", "LOT/SERIAL Button Clicked");
			flag = webDB.waitForElement(GrnLocators.LotSerialIframe, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(GrnLocators.LotSerialIframe, ElementType.Xpath);
				String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
				ExcelUtils excelUtils = new ExcelUtils();
				String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
						+ "ItemDetails.xlsx";
				itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POMultipleGen"));
				for (int i = 1; i < itemmultiple.length; i++) {
					String ITEM_NAME = itemmultiple[i][0];
					String LOT_SERIAL = itemmultiple[i][3];
					String LOT_NUMBER = itemmultiple[i][4];
					String SERIAL_NUMBER = itemmultiple[i][5];
					if (!ITEM_NAME.equals("")) {
						if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Na"))) {
							continue;
						}
						flag = webDB.waitForElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(3000);
							flag = webDB.waitForElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
								webDB.switchToDefaultContent();
								flag = webDB.waitForElement(GrnLocators.LotserialitemsInput, ElementType.Xpath);
								if (flag) {
									if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Lot"))) {
										inputLotNumber(ITEM_NAME, LOT_NUMBER);
									} else if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Serial"))) {
										inputSerialNumber(ITEM_NAME, SERIAL_NUMBER);
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
	 * This Methods Method to handle Validate the PO Location and GRN Document
	 * Location
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void validateLocationInGrnHeader(String LocationId) {
		flag = webDB.waitForElement(GrnLocators.GrnHeaderLocation, ElementType.Xpath);
		if (flag) {
			String DisplayText = webDB.getTextFromElement(GrnLocators.GrnHeaderLocation, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Expected Text: " + LocationId + " Displayed: " + DisplayText);
			}
			flag = DisplayText.equalsIgnoreCase(LocationId);
			if (flag) {
				logs.logging("INFO", "verified Displayed Location Header: " + DisplayText);
			} else {
				logs.logging("INFO", "Verification failed in Location GRN Header");
				logs.logging("INFO", "Expected Text: " + LocationId + " Displayed: " + DisplayText);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Give Lot Number Details
	 * 
	 * @author PremKumar.K-RM IT
	 */

	public boolean AddLotNumber(String ITEM, String LotNumber) {
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
								webDB.sendTextToAnElement(GrnLocators.ExpiryDate,
										commonFunc.getDataFromTestDataSheet("ExpiryDate"), ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Date Insert Successfully");
									flag = webDB.waitForElement(GrnLocators.LotSerialSave, ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(GrnLocators.LotSerialSave, ElementType.Xpath);
										if (flag) {
											logs.logging("INFO", "Lot Item Successfully Saved");
											commonFunc.hardWait(5000);
											if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon,
													ElementType.Xpath)) {
												webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
														ElementType.Xpath);
												commonFunc.hardWait(3000);
												flag = webDB.waitForElement(GrnLocators.ApportionLSQty,
														ElementType.Xpath);
												webDB.clickAnElement(GrnLocators.ApportionLSQty, ElementType.Xpath);
												webDB.switchToDefaultContent();
												commonFunc.checkRefreshCellsDisappear();
												commonFunc.hardWait(1000);
												webDB.switchToDefaultContent();
												flag = webDB.waitForElement(GrnLocators.GrnDocNumber,
														ElementType.Xpath);
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
	 * This Methods Is Used for Approve the Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void waitingForApproval() {
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
				flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(3000);
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("AshokUsername"),
							System.getProperty("AshokPassword"));
					approvalFunc.navigateToApprovals();
					approvalFunc.filterDocumentsforApproval(VariableHandler.getGrnDocNumber());
					approvalFunc.verifyApproveWithDocNumber(VariableHandler.getGrnDocNumber());
					commonFunc.hardWait(3000);
					flag = commonFunc.logoutUser();
				}

			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create GRN Document from PO With and Without
	 * Inspection for UserDefined Item(Material)
	 * 
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void poToGrnMaterialUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlan() throws IOException {
		POfunc.verifyDirectPODomesticMaterialSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("Loc_100"));
		multipleLotAndSerialItem();
		lotSerialQtyApportion();
		overAllSaveAndPost();
		waitingForApproval();
		verifyReturnToGrnPageHeaderStatus();
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create GRN From PO With General With And
	 * Without Inspection Document(Multiple Item)
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlan() throws IOException {
		POfunc.verifyDirectPODomesticGeneralSimilarUOMWithMultipleItem();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("Loc_100"));
		multipleLotandSerialWithGeneralType();
		lotSerialQtyApportion();
		overAllSaveAndPost();
		waitingForApproval();
		verifyReturnToGrnPageHeaderStatus();
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create GRN From PO With Service Type
	 * Document(Single Item)
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyGrnPODomesticServiceSimilarUOMfromPo() {
		POfunc.verifyDirectPODomesticServiceSimilarUOMMultiple();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("Loc_100"));
		overAllSaveAndPost();
		waitingForApproval();
		verifyReturnToGrnPageHeaderStatus();
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create GRN Document from PO With and Without
	 * Inspection for Contract Item(General Type)
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void poToGrnGeneralRateContractSameStockAndSupplierUOMWithAndWithoutInspectionGeneralType() {
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralType();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("Loc_100"));
		multpleLotSerialWithRateContractItem();
		lotSerialQtyApportion();
		overAllSaveAndPost();
		waitingForApproval();
		verifyReturnToGrnPageHeaderStatus();
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create the GRN Document from PO with and
	 * Without Inspection for RateContract Item(Material)
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void poToGrnGeneralRateContractSameStockAndSupplierUOMWithAndWithoutInspectionMaterial() {
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionMaterial();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("Loc_100"));
		multipleLotSerialItemWithMaterialTypeForRatecontractItem();
		lotSerialQtyApportion();
		overAllSaveAndPost();
		waitingForApproval();
		verifyReturnToGrnPageHeaderStatus();
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle LOT_NUMBER input logic for Lot Item
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void inputLotNumber(String ITEM_NAME, String LOT_NUMBER) {
		flag = webDB.waitForElement(GrnLocators.LotserialitemsInput, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.LotserialitemsInput, ElementType.Xpath);
			webDB.sendTextToAnElement(GrnLocators.LotserialitemsInput, ITEM_NAME, ElementType.Xpath);
			flag = webDB.waitForElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
				flag = webDB.waitForElement(GrnLocators.LotserialItemSelect, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(GrnLocators.LotserialItemSelect, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					logs.logging("INFO", "Item Successfully Added");
					webDB.switchToFrame(GrnLocators.LotSerialIframe, ElementType.Xpath);
					flag = webDB.waitForElement(GrnLocators.LotclickInput, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(GrnLocators.LotclickInput, ElementType.Xpath);
						commonFunc.hardWait(3000);
						flag = webDB.isElementDisplayed(GrnLocators.LotNumberInput, ElementType.Xpath);
						if (flag) {
							webDB.sendTextToAnElement(GrnLocators.LotNumberInput, LOT_NUMBER, ElementType.Xpath);
							flag = webDB.waitForElement(GrnLocators.LotSerialDate, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Lot Number Insert Successfully");
								flag = webDB.isElementEnabled(GrnLocators.SerialNumberInput, ElementType.Xpath);
								if (!flag) {
									logs.logging("info", "Verified Serial Is Disabled");
								} else {
									logs.logging("INFO", "Serial Is Enabled");
								}
								flag = webDB.waitForElement(GrnLocators.LotSerialDate, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(GrnLocators.LotSerialDate, ElementType.Xpath);
									flag = webDB.waitForElement(GrnLocators.DateInput, ElementType.Xpath);
									if (flag) {
										commonFunc.hardWait(5000);
										webDB.sendTextToAnElement(GrnLocators.DateInput,
												commonFunc.getDataFromTestDataSheet("LotSerialDate"),
												ElementType.Xpath);
										logs.logging("INFO", "Lot Date Insert Successfully");
										commonFunc.hardWait(2000);
										flag = webDB.waitForElement(GrnLocators.LotSerialSave, ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(GrnLocators.LotSerialSave, ElementType.Xpath);
											commonFunc.hardWait(3000);
											closeToastIfDisplayed();
											flag = webDB.waitForElement(GrnLocators.LotSerialQtyField,
													ElementType.Xpath);
											if (flag) {
												commonFunc.hardWait(5000);
												webDB.clickAnElement(GrnLocators.LotSerialQtyField, ElementType.Xpath);
												logs.logging("INFO", "LotSerial Qty Field Clicked Successfully");
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
		assertTrue(flag);

	}

	/**
	 * This Methods Method to handle Serial_Number input logic for Lot Item
	 * 
	 * @author PremKumar.K-RMITS
	 */
	private void inputSerialNumber(String ITEM_NAME, String SERIAL_NUMBER) {
		flag = webDB.waitForElement(GrnLocators.LotserialitemsInput, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.LotserialitemsInput, ElementType.Xpath);
			webDB.sendTextToAnElement(GrnLocators.LotserialitemsInput, ITEM_NAME, ElementType.Xpath);
			flag = webDB.waitForElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
				flag = webDB.waitForElement(GrnLocators.LotserialItemSelect, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(GrnLocators.LotserialItemSelect, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					logs.logging("INFO", "item Successfully Added");
					webDB.switchToFrame(GrnLocators.LotSerialIframe, ElementType.Xpath);
					flag = webDB.waitForElement(GrnLocators.SerialClickInput, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(GrnLocators.SerialClickInput, ElementType.Xpath);
						flag = webDB.isElementDisplayed(GrnLocators.SerialNumberInput, ElementType.Xpath);
						if (flag) {
							webDB.sendTextToAnElement(GrnLocators.SerialNumberInput, SERIAL_NUMBER, ElementType.Xpath);
							logs.logging("INFO", "Serial Number Insert Successfully");
							flag = webDB.isElementEnabled(GrnLocators.LotNumberInput, ElementType.Xpath);
							if (!flag) {
								logs.logging("info", "verified lot is disabled");
							} else {
								logs.logging("INFO", "lot Is Enabled");
							}
							flag = webDB.waitForElement(GrnLocators.LotSerialDate, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(GrnLocators.LotSerialDate, ElementType.Xpath);
								flag = webDB.waitForElement(GrnLocators.DateInput, ElementType.Xpath);
								if (flag) {
									commonFunc.hardWait(5000);
									webDB.sendTextToAnElement(GrnLocators.DateInput,
											commonFunc.getDataFromTestDataSheet("LotSerialDate"), ElementType.Xpath);
									logs.logging("INFO", "Serial Date Insert Successfully");
									commonFunc.hardWait(2000);
									flag = webDB.waitForElement(GrnLocators.LotSerialSave, ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(GrnLocators.LotSerialSave, ElementType.Xpath);
										closeToastIfDisplayed();
										flag = webDB.waitForElement(GrnLocators.LotSerialQtyField, ElementType.Xpath);
										if (flag) {
											commonFunc.hardWait(5000);
											webDB.clickAnElement(GrnLocators.LotSerialQtyField, ElementType.Xpath);
											logs.logging("INFO", "LotSerial Qty Field Clicked Successfully");
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
	 * This Methods Method to handle After Approve the Document to Verify the
	 * Document Header Status
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyReturnToGrnPageHeaderStatus() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		flag = webDB.waitForElement(GrnLocators.FindPageGrnNoLov, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.FindPageGrnNoLov, ElementType.Xpath);
			flag = webDB.waitForElement(GrnLocators.GrnNumberInput, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(GrnLocators.GrnNumberInput, VariableHandler.getGrnDocNumber());
				logs.logging("INFO", "Document Number Search And Selected Successfully");
				flag = webDB.waitForElement(GrnLocators.GrnHeaderRadioBtn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(GrnLocators.GrnHeaderRadioBtn, ElementType.Xpath);
					logs.logging("INFO", "Header Button Clicked");
					flag = webDB.waitForElement(GrnLocators.GrnSearchButton, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(GrnLocators.GrnSearchButton, ElementType.Xpath);
						logs.logging("INFO", "Document Searched SuccessFully");
						flag = webDB.waitForElement(GrnLocators.GrnDocumentStatus, ElementType.Xpath);
						if (flag) {
							String GRNSTATUS = webDB.getTextFromElement(GrnLocators.GrnDocumentStatus,
									ElementType.Xpath);
							VariableHandler.setGrnStatus(GRNSTATUS);
							logs.logging("INFO", "GRN ProcesWise STATUS:" + VariableHandler.getGrnStatus());
							flag = commonFunc.logoutUser();
						}
					}
				}
			}
			assertTrue(flag);
		}

	}

	/**
	 * This Method to handle After Approve the Status Document Move to QC or MRV
	 * Screen
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void afterVerifyTheStatusDocumentMoveToQcOrMrv() {
		if (VariableHandler.getGrnStatus().equals(commonFunc.getDataFromTestDataSheet("Draft"))) {
			qcFunc.InspectionDocumentWithInspectionPlan();
		}
		mrvFunc.verifyPostMaterialReceiptWithAndWithoutLocator();
		assertTrue(flag);
	}

	/**
	 * This Method to handle Create the GRN Document For Stock Transfer Type With
	 * Rate Contract Item
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void PoToGrnStockTransferRateContractSameStockAndSupplierUomWithAndWithoutInspection() {
		POfunc.verifyToCreatePoForStockTransferRateContractSameStockandSupplierUomWithAndWithoutInspection();
		verifyToStockTransferSaleOrderDocumentPost();
		saleOrderFunc.forwardForApproval();
		flag = commonFunc.logoutUser();
		stockTransferAfterForwardToApproval();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocument();
		stockTransferAfterForwardToApprovalForInvoiceDocument();
		createGrnDocumentforwithandwithoutInspectionStockTransferType();
		verifyReturnToGrnPageHeaderStatus();
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		assertTrue(flag);
	}

	/**
	 * This Method to handle verify To Stock Transfer SaleOrder Document Post
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToStockTransferSaleOrderDocumentPost() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		flag = webDB.waitForElement(SalesOrderLocators.FindSONumberLOV, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.FindSONumberLOV,
					VariableHandler.getSalesOrdertDocNumber());
			flag = webDB.waitForElement(SalesOrderLocators.FindSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesOrderLocators.FindSearchButton, ElementType.Xpath);
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				logs.logging("INFO", "Document Document Successfuly Slected");
				flag = webDB.waitForElement(PurchaseOrderPageLocators.SaleOrderDrillDown, ElementType.Xpath);
				if (flag)
					webDB.clickAnElement(PurchaseOrderPageLocators.SaleOrderDrillDown, ElementType.Xpath);
				logs.logging("INFO", "Document Successfully Navigate Into Transaction Screen");
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				saleOrderFunc.clickPostButton();
				logs.logging("INFO", "Sale Order Document Successfully Posted");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method to handle verify To Stock Transfer Sales Invoice Document Post
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToPostStockTransferDocumentForSalesInvoice() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesInvoicePage();
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(SalesInvoiceLocators.Sales_Inv_Pend_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Sales_Inv_Pend_Btn, ElementType.Xpath);
			logs.logging("INFO", "Pending Button Clicked Successfully");
			flag = webDB.waitForElement(SalesInvoiceLocators.PendingSORadioBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.PendingSORadioBtn, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(SalesInvoiceLocators.PendingSOIframe, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Navigated to the Pending SO Successfully");
					commonFunc.checkRefreshCellsDisappear();
					webDB.switchToFrame(SalesInvoiceLocators.PendingSOIframe, ElementType.Xpath);
					flag = webDB.waitForElement(SalesInvoiceLocators.PendingSalesOrderSearchLOVBTN, ElementType.Xpath);
					if (flag) {
						commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.PendingSalesOrderSearchLOVBTN,
								VariableHandler.getSalesOrdertDocNumber());
						webDB.switchToFrame(SalesInvoiceLocators.PendingSOIframe, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(SalesInvoiceLocators.PendingSalesOrderSearchBtn, ElementType.Xpath);
						if (flag) {
							webDB.moveToElement(SalesInvoiceLocators.PendingSalesOrderSearchBtn, ElementType.Xpath);
							flag = webDB.clickAnElement(SalesInvoiceLocators.PendingSalesOrderSearchBtn,
									ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							logs.logging("INFO", "Pending SO Search Button Clicked");
							flag = webDB.waitForElement(SalesInvoiceLocators.SalesInvoiceSelectAllBtn,
									ElementType.Xpath);
						}
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * This Method to handle select All Pending SO And Click Create SI For HodUser
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void selectAllPendingSOAndClickCreateSIForHodUser() {
		commonFunc.checkRefreshCellsDisappear();
		webDB.switchToFrame(SalesInvoiceLocators.PendingSOIframe, ElementType.Xpath);
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(SalesInvoiceLocators.SalesInvoiceSelectAllBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.SalesInvoiceSelectAllBtn, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.PendingSO_CreateInvoiceBtn, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				flag = webDB.isElementEnabled(SalesInvoiceLocators.PendingSO_CreateInvoiceBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "SONumber Checkbox clicked in Pending SO and Create Sales Invoice Enabled");
					webDB.clickAnElement(SalesInvoiceLocators.PendingSO_CreateInvoiceBtn, ElementType.Xpath);
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(SalesInvoiceLocators.Invoice_Pfx, ElementType.Xpath);
					if (flag) {
						String SIdocno = webDB.getTextFromElement(SalesInvoiceLocators.SI_Doc_NO, ElementType.Xpath);
						logs.logging("INFO", "Get SI DocNo Is: " + SIdocno);
						VariableHandler.setSIdocno(SIdocno);
						logs.logging("INFO", "Pending SO - 'Create Invoice' Clicked");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method to handle verify To Post Sales Invoice Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToPostSalesInvoiceDocument() {
		saleInvoiceFunc.verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		saleInvoiceFunc.verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyInvoicePfxForStockTransferDocument(commonFunc.getDataFromTestDataSheet("StockTransferInvoice"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyCreateInvoiceNoForStockTransferSupplier();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		saleInvoiceFunc.clickPostButton();
		saleInvoiceFunc.forwardForApproval();
		commonFunc.logoutUser();
	}

	/**
	 * This Method to handle verify stock Transfer After Forward To Approval
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void stockTransferAfterForwardToApproval() {
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getSalesOrdertDocNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getSalesOrdertDocNumber());
			commonFunc.hardWait(3000);
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * This Method to handle verify stock Transfer After Forward To Approval For
	 * Invoice Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void stockTransferAfterForwardToApprovalForInvoiceDocument() {
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
			commonFunc.hardWait(3000);
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * This Method to handle verify Invoice Pfx For Stock Transfer Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyInvoicePfxForStockTransferDocument(String InvoicePrefix) {
		flag = webDB.waitForElement(SalesInvoiceLocators.Invoice_column, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Invoice_column, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.Invoice_Pfx, InvoicePrefix);
			logs.logging("INFO", "Invoice Prefix Is Displayed Successfully");
			flag = webDB.waitForElement(SalesInvoiceLocators.SalesInvoiceOverAllSaveBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.SalesInvoiceOverAllSaveBtn, ElementType.Xpath);
				logs.logging("INFO", "OverAllSaveSucessFully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method to handle verify Create Invoice No For Stock Transfer Supplier
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyCreateInvoiceNoForStockTransferSupplier() {
		flag = webDB.waitForElement(SalesInvoiceLocators.Cre_Inv_No, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Cre.Inv.No Button Displayed Successfully");
			webDB.clickAnElement(SalesInvoiceLocators.Cre_Inv_No, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				if (flag) {
					String SalesInvoiceNumber = webDB
							.getTextFromElement(SalesInvoiceLocators.SalesInvoiceNumberTextField, ElementType.Xpath);
					VariableHandler.setSalesInvoiceNumber(SalesInvoiceNumber);
					logs.logging("INFO", "Sales Invoice Number:" + VariableHandler.getSalesInvoiceNumber());

					if (flag) {
						logs.logging("INFO", "Cre.Inv.No Button clicked Successfully");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods to handle Click the Pending Stock Transfer Button And Select
	 * Search Particular Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void clickPendingStockTransfer() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(GrnLocators.ClickPendingButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(GrnLocators.ClickPendingButton, ElementType.Xpath);
			webDB.clickAnElement(GrnLocators.ClickPendingButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			logs.logging("INFO", "Pending Button clicked Succesfully");
			flag = webDB.waitForElement(GrnLocators.PendingStockTransfer, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.PendingStockTransfer, ElementType.Xpath);
				logs.logging("INFO", "Pending Stock Transfer Clicked Succesfully");
				flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(GrnLocators.PendingPOIframe, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(GrnLocators.CustomerInvoiceNumberLov,
							VariableHandler.getSalesInvoiceNumber());
					logs.logging("INFO", "Stock Transfer Document Selected Successfully");
					if (flag) {
						flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
						webDB.switchToFrame(GrnLocators.PendingPOIframe, ElementType.Xpath);
						if (flag) {
							flag = webDB.waitForElement(GrnLocators.PendingPOHeaderSearchButton, ElementType.Xpath);
							webDB.clickAnElement(GrnLocators.PendingPOHeaderSearchButton, ElementType.Xpath);
							webDB.switchToDefaultContent();
						}

					}

				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods to handle Create GRN Document from PO With and Without
	 * Inspection for Stock Transfer Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void createGrnDocumentforwithandwithoutInspectionStockTransferType() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		clickPendingStockTransfer();
		clickSelectAllButtonForStockTransferItem();
		overAllSaveAndPost();
		waitingForApproval();
	}

	/**
	 * This Methods to handle create GRN From Stock Transfer
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void createGrnFromStockTransfer() {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(3000);
		flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(GrnLocators.PendingPOIframe, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			logs.logging("INFO", "Frame Click GRN Document");
			flag = webDB.waitForElement(GrnLocators.GrnStockTransferCreation, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.GrnStockTransferCreation, ElementType.Xpath);
			}
		}
		if (flag) {
			logs.logging("INFO", "Click GRN Document");
		}
		assertTrue(flag);
	}

	/**
	 * This Methods to Handle click Select All Button For Stock Transfer Item
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void clickSelectAllButtonForStockTransferItem() {
		flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(GrnLocators.PendingPOIframe, ElementType.Xpath);
			flag = webDB.waitForClickElement(GrnLocators.PendingPOSelectAllButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.PendingPOSelectAllButton, ElementType.Xpath);
				logs.logging("INFO", " Document Fully Selected");
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(4000);
				flag = webDB.waitForElement(GrnLocators.GrnStockTransferCreation, ElementType.Xpath);

				flag = webDB.clickAnElement(GrnLocators.GrnStockTransferCreation, ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", " Document Fully Selected And Create GRN Button Shown");
				} else {
					logs.logging("INFO", " Document Not Selected And Create GRN Button Not Shown");
				}
			}

		}
		assertTrue(flag);
	}

	/**
	 * This Method to Handle PO to GRN Stock Transfer User Defined Same Stock and
	 * SupplierUOM With And Withou tInspection
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void poToGRNStockTransferUserDefinedSameStockandSupplierUOMWithAndWithoutInspection() {
		POfunc.verifyToCreatePoForStockTransferUserDefinedSameStockandSupplierUomWithAndWithoutInspection();
		verifyToStockTransferSaleOrderDocumentPost();
		saleOrderFunc.forwardForApproval();
		flag = commonFunc.logoutUser();
		stockTransferAfterForwardToApproval();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocument();
		stockTransferAfterForwardToApprovalForInvoiceDocument();
		createGrnDocumentforwithandwithoutInspectionStockTransferType();
		verifyReturnToGrnPageHeaderStatus();
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		assertTrue(flag);
	}

	/**
	 * This Method to Handle POTo GRN Service Price List Same Stock And Supplier UOM
	 * WO Inspection Plan
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void poToGRNServicePriceListSameStockAndSupplierUOMWOInspectionPlan() {
		POfunc.verifyDirectPODomesticPriceListItemSimilarUOMMultipleServiceType();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("Loc_100"));
		overAllSaveAndPost();
		waitingForApproval();
		verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Method to Handle PO To GRN Stock Transfer Price List Same Stock and
	 * Supplier UOM With And WO Inspection
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void poToGrnStockTransferPriceListSameStockandSupplierUOMWithAndWOInspection() {
		POfunc.verifyToCreatePoForStockTransferPriceListItemForSameStockandSupplierUomWithAndWithoutInspection();
		verifyToStockTransferSaleOrderDocumentPost();
		saleOrderFunc.forwardForApproval();
		flag = commonFunc.logoutUser();
		stockTransferAfterForwardToApproval();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocument();
		stockTransferAfterForwardToApprovalForInvoiceDocument();
		createGrnDocumentforwithandwithoutInspectionStockTransferType();
		verifyReturnToGrnPageHeaderStatus();
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		assertTrue(flag);
	}

	/**
	 * This Method to Handle PO To GRN General User Defined Stock And Supplier
	 * DisUom With And Without Inspection Plan
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void poToGrnGeneralUserDefinedStockAndSupplierDisUomWithAndWithoutInspectionPlan() {
		POfunc.verifyDirectPODomesticGeneralDissimilarUOMWithMultipleItem();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("Loc_100"));
		lotAndSerialWithGeneralTypeForDisUOM();
		lotSerialQtyApportion();
		overAllSaveAndPost();
		waitingForApproval();
		verifyReturnToGrnPageHeaderStatus();
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
	}

	/**
	 * This Method to Handle lot And Serial With General Type For DisUOM
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void lotAndSerialWithGeneralTypeForDisUOM() {
		flag = webDB.waitForElement(GrnLocators.LotSerailButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Successfully Created GRN and Entered GRN Transaction Screen");
			commonFunc.hardWait(3000);
			webDB.clickAnElement(GrnLocators.LotSerailButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			logs.logging("INFO", "LOT/SERIAL Button Clicked");
			flag = webDB.waitForElement(GrnLocators.LotSerialIframe, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(GrnLocators.LotSerialIframe, ElementType.Xpath);
				String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
				ExcelUtils excelUtils = new ExcelUtils();
				String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
						+ "ItemDetails.xlsx";
				itemmultiple = excelUtils.readAllDataInExcel(Filepath,
						webDB.getDataFromProperties("POwithUserDefinedItemDisimilarUomGenType"));
				for (int i = 1; i < itemmultiple.length; i++) {
					String ITEM_NAME = itemmultiple[i][0];
					String LOT_SERIAL = itemmultiple[i][3];
					String LOT_NUMBER = itemmultiple[i][4];
					String SERIAL_NUMBER = itemmultiple[i][5];
					if (!ITEM_NAME.equals("")) {
						if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Na"))) {
							continue;
						}
						flag = webDB.waitForElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(3000);
							flag = webDB.waitForElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
								webDB.switchToDefaultContent();
								flag = webDB.waitForElement(GrnLocators.LotserialitemsInput, ElementType.Xpath);
								if (flag) {
									if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Lot"))) {
										inputLotNumber(ITEM_NAME, LOT_NUMBER);
									} else if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Serial"))) {
										inputSerialNumber(ITEM_NAME, SERIAL_NUMBER);
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
	 * This Method to handle Lot Serial For PriceList General type Item for General
	 * Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void pricelistItemwithAndWithoutInspectionPlanLotSerialGeneralType() {
		flag = webDB.waitForElement(GrnLocators.LotSerailButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Successfully Created GRN and Entered GRN Transaction Screen");
			commonFunc.hardWait(3000);
			webDB.clickAnElement(GrnLocators.LotSerailButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			logs.logging("INFO", "LOT/SERIAL Button Clicked");
			flag = webDB.waitForElement(GrnLocators.LotSerialIframe, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(GrnLocators.LotSerialIframe, ElementType.Xpath);
				String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
				ExcelUtils excelUtils = new ExcelUtils();
				String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
						+ "ItemDetails.xlsx";
				itemmultiple = excelUtils.readAllDataInExcel(Filepath,
						webDB.getDataFromProperties("POwithPriceListItemGeneralType"));
				for (int i = 1; i < itemmultiple.length; i++) {
					String ITEM_NAME = itemmultiple[i][0];
					String LOT_SERIAL = itemmultiple[i][4];
					String LOT_NUMBER = itemmultiple[i][5];
					String SERIAL_NUMBER = itemmultiple[i][6];
					if (!ITEM_NAME.equals("")) {
						if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Na"))) {
							continue;
						}
						flag = webDB.waitForElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(3000);
							flag = webDB.waitForElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
								webDB.switchToDefaultContent();
								flag = webDB.waitForElement(GrnLocators.LotserialitemsInput, ElementType.Xpath);
								if (flag) {
									if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Lot"))) {
										inputLotNumber(ITEM_NAME, LOT_NUMBER);
									} else if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Serial"))) {
										inputSerialNumber(ITEM_NAME, SERIAL_NUMBER);
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
	 * This Method to handle Create the GRN Document from PO with PriceList Item for
	 * General Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void poToGrnGeneralPriceListSameStockAndSupplierUOMWithInspection() {
		POfunc.verifyDirectPODomesticPricelistItemWithItemandWithoutInspectionPlan();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("Loc_100"));
		pricelistItemwithAndWithoutInspectionPlanLotSerialGeneralType();
		lotSerialQtyApportion();
		overAllSaveAndPost();
		waitingForApproval();
		verifyReturnToGrnPageHeaderStatus();
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
	}

	/**
	 * This Methods Method to handle Create the GRN Document from PO with
	 * RateContract Item for Service Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void poToGrnServiceRateContractSameStockAndSupplierUOMWoInspectionPlan() {
		POfunc.verifyDirectPODomesticRateContractItemSimilarUOMMultipleServiceType();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("Loc_100"));
		overAllSaveAndPost();
		waitingForApproval();
		verifyReturnToGrnPageHeaderStatus();
	}

}
