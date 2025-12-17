package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.ItemDetailsHandler;
import commonfunctions.VariableHandler;
import commonfunctions.qualitycontrol.InspectionDocumentFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.DirectGrnPageLocators;
import commonlocators.supplychainmanagement.GrnLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import commonlocators.supplychainmanagement.SalesInvoiceLocators;
import commonlocators.supplychainmanagement.SalesOrderLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class GrnPageFunctions {
	boolean flag = false;
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
	private static DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();
	static DirectGrnPageFunctions DirectGrnFunc = new DirectGrnPageFunctions();
	static GateEntryPageFunctions gateEntryPageFunc = new GateEntryPageFunctions();

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
						if (!element.isSelected()) {
							element.click();
							flag = true;
							break;
						} else {
							element.click();
							Thread.sleep(10000);
							webDB.switchToDefaultContent();
							clickSelectAllButton();
							flag = true;
							break;
						}
					}
				} catch (Exception e) {
				}
			}
			logs.logging("INFO", " Document Fully Selected");
//			flag = webDB.waitForElement(GrnLocators.PendingPOCreateGRN, ElementType.Xpath);
//			if (flag) {
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", " Document Fully Selected And Create GRN Button Shown");
			} else {
				logs.logging("INFO", " Document Not Selected And Create GRN Button Not Shown");
//				}
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

	public void multipleLotAndSerialItem() throws Exception {
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
					commonFunc.getDataFromTestDataSheet("HodValue"));
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
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
							VariableHandler.getGrnDocNumber(), webDB.getDataFromProperties("HodEmpId"),
							commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
				}

			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create the GRN Document from PO with and
	 * Without Inspection for RateContract Item(Material)
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnGeneralRateContractSameStockAndSupplierUOMWithAndWithoutInspectionMaterial() throws Exception {
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionMaterial();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
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
			commonFunc.searchAndSelectValueInFilters(GrnLocators.LotserialitemsInput, ITEM_NAME);
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
										commonFunc.getDataFromTestDataSheet("LotSerialDate"), ElementType.Xpath);
								logs.logging("INFO", "Lot Date Insert Successfully");
								commonFunc.hardWait(2000);
								flag = webDB.waitForElement(GrnLocators.LotSerialSave, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(GrnLocators.LotSerialSave, ElementType.Xpath);
									commonFunc.hardWait(3000);
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
			commonFunc.searchAndSelectValueInFilters(GrnLocators.LotserialitemsInput, ITEM_NAME);
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
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle After Approve the Document to Verify the
	 * Document Header Status
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyReturnToGrnPageHeaderStatus() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
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
		}
		assertTrue(flag);
	}

	/**
	 * This Method to handle After Approve the Status Document Move to QC or MRV
	 * Screen
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void afterVerifyTheStatusDocumentMoveToQcOrMrv() {
		String GrnHeaderWiseStatus = dbCommonFunctions.GrnHeaderDetails(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("ProcessFlowStatus"));
		logs.logging("INFO", "GRN Document ProcessFlow Status Is " + GrnHeaderWiseStatus);
		if (GrnHeaderWiseStatus.equals(commonFunc.getDataFromTestDataSheet("QualityEnabled"))) {
			qcFunc.InspectionDocumentWithInspectionPlan();
		}
		verifyTocreateMrvDocumentBasedOnAplicationControl();
		assertTrue(flag);
	}

	/**
	 * This Method to handle Create the GRN Document For Stock Transfer Type With
	 * Rate Contract Item
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void PoToGrnStockTransferRateContractSameStockAndSupplierUomWithAndWithoutInspection() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectPoStockTransferRateContract"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectPoStockTransferRateContract"));
		POfunc.verifyToCreatePoForStockTransferRateContractSameStockandSupplierUomWithAndWithoutInspection(
				webDB.getDataFromProperties("DirectPoStockTransferRateContract"));
		verifyToStockTransferSaleOrderDocumentPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSalesOrdertDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocument();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		createGrnDocumentforwithandwithoutInspectionStockTransferType(
				webDB.getDataFromProperties("DirectPoStockTransferRateContract"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectPoStockTransferRateContract"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransferType();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectPoStockTransferRateContract"));
		assertTrue(flag);
	}

	/**
	 * This Method to handle verify To Stock Transfer SaleOrder Document Post
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToStockTransferSaleOrderDocumentPost() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
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
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
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
//		saleInvoiceFunc.forwardForApproval();
//		commonFunc.logoutUser();
	}

	/**
	 * This Method to handle verify stock Transfer After Forward To Approval
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void stockTransferAfterForwardToApproval() {
		commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getSalesOrdertDocNumber(),
				webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit7"),
				System.getProperty("HariUsername"));
		assertTrue(flag);
	}

	/**
	 * This Method to handle verify stock Transfer After Forward To Approval For
	 * Invoice Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void stockTransferAfterForwardToApprovalForInvoiceDocument() {
		commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
//					"HodUsername");
//			approvalFunc.navigateToApprovals();
//			approvalFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
//			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
//			commonFunc.hardWait(3000);
//			flag = commonFunc.logoutUser();
//		}
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
					commonFunc.checkRefreshCellsDisappear();
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
	public void createGrnDocumentforwithandwithoutInspectionStockTransferType(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingStockTransfer();
		clickSelectAllButtonForStockTransferItem();
		overAllSaveAndPost();
		// waitingForApproval();
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
	 * @throws Exception
	 */
	public void poToGRNStockTransferUserDefinedSameStockandSupplierUOMWithAndWithoutInspection() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectPoStockTransferUD"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectPoStockTransferUD"));
		POfunc.verifyToCreatePoForStockTransferUserDefinedSameStockandSupplierUomWithAndWithoutInspection(
				webDB.getDataFromProperties("DirectPoStockTransferUD"));
		verifyToStockTransferSaleOrderDocumentPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSalesOrdertDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocument();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		createGrnDocumentforwithandwithoutInspectionStockTransferType(
				webDB.getDataFromProperties("DirectPoStockTransferUD"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectPoStockTransferUD"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransferType();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectPoStockTransferUD"));
		assertTrue(flag);
	}

	/**
	 * This Method to Handle PO To GRN Stock Transfer Price List Same Stock and
	 * Supplier UOM With And WO Inspection
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnStockTransferPriceListSameStockandSupplierUOMWithAndWOInspection() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectPoStockTransferPriceList"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectPoStockTransferPriceList"));
		POfunc.verifyToCreatePoForStockTransferPriceListItemForSameStockandSupplierUomWithAndWithoutInspection(
				webDB.getDataFromProperties("DirectPoStockTransferPriceList"));
		verifyToStockTransferSaleOrderDocumentPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSalesOrdertDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocument();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		createGrnDocumentforwithandwithoutInspectionStockTransferType(
				webDB.getDataFromProperties("DirectPoStockTransferUD"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectPoStockTransferPriceList"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransferType();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectPoStockTransferPriceList"));
		assertTrue(flag);
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
	 * This Method to Handle lot And Serial With Material Type For DisUOM
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void lotAndSerialWithMaterialTypeForDisUOM() {

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
						webDB.getDataFromProperties("POwithUserDefinedItemDisimilarUomMatType"));
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
	 * This Methods Method to handle Create GRN From PO With Service Type
	 * Document(Single Item)
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyGrnPODomesticServiceSimilarUOMfromPo() {
		POfunc.verifyDirectPODomesticServiceSimilarUOMMultiple(webDB.getDataFromProperties("DirectPOSerUD"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("Loc_100"));
		overAllSaveAndPost(webDB.getDataFromProperties("DirectPOSerUD"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyReturnToGrnPageHeaderStatus();
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create GRN Document from PO With and Without
	 * Inspection for Contract Item(General Type)
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnGeneralRateContractSameStockAndSupplierUOMWithAndWithoutInspectionGeneralType()
			throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectPORateGen"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectPORateGen"));
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralType(
				webDB.getDataFromProperties("DirectPORateGen"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("DirectPORateGen"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("DirectPORateGen"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectPORateGen"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("DirectPORateGen"));
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create the GRN Document from PO with and
	 * Without Inspection for RateContract Item(Material)
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnlRateContractSameStockAndSupplierUOMWithAndWithoutInspectionMaterial() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectPORateGen"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectPORateGen"));
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionMaterial(
				webDB.getDataFromProperties("DirectPORateGen"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("DirectPORateGen"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("DirectPORateGen"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectPORateGen"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("DirectPORateGen"));
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create GRN Document from PO With and Without
	 * Inspection for UserDefined Item(Material)
	 * 
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */

	public void poToGrnMaterialUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlan() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectPOMatUD"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectPOMatUD"));
		POfunc.verifyDirectPODomesticMaterialSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				webDB.getDataFromProperties("DirectPOMatUD"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("DirectPOMatUD"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("DirectPOMatUD"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectPOMatUD"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("DirectPOMatUD"));
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create GRN From PO With General With And
	 * Without Inspection Document(Multiple Item)
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlan() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectPOMatUD"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectPOMatUD"));
		POfunc.verifyDirectPODomesticGeneralSimilarUOMWithMultipleItem(webDB.getDataFromProperties("DirectPOMatUD"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("DirectPOMatUD"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("DirectPOMatUD"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectPOMatUD"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("DirectPOMatUD"));
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create the GRN Document from PO with
	 * RateContract Item for Service Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	public void poToGrnServiceRateContractSameStockAndSupplierUOMWoInspectionPlan() throws IOException {
		POfunc.verifyDirectPODomesticRateContractItemSimilarUOMMultipleServiceTypeWithIgstSupplier(
				webDB.getDataFromProperties("FreePassUdRateSer"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		overAllSaveAndPost(webDB.getDataFromProperties("FreePassUdRateSer"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Method to handle Create the GRN Document from PO with PriceList Item for
	 * General Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnGeneralPriceListSameStockAndSupplierUOMWithandWithoutInspectionPlan() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectPoPriceListGen"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectPoPriceListGen"));
		POfunc.verifyDirectPODomesticPricelistItemWithItemandWithoutInspectionPlan(
				webDB.getDataFromProperties("DirectPoPriceListGen"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("DirectPoPriceListGen"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("DirectPoPriceListGen"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectPoPriceListGen"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectPoPriceListGen"));
	}

	/**
	 * This Method to Handle PO To GRN General User Defined Stock And Supplier
	 * DisUom With And Without Inspection Plan
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnGeneralUserDefinedStockAndSupplierDisUomWithAndWithoutInspectionPlan() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectPODifUOMGENUD"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectPODifUOMGENUD"));
		POfunc.verifyDirectPODomesticGeneralDissimilarUOMWithMultipleItem(
				webDB.getDataFromProperties("DirectPODifUOMGENUD"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("DirectPODifUOMGENUD"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("DirectPODifUOMGENUD"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectPODifUOMGENUD"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectPODifUOMGENUD"));

	}

	/**
	 * This Method to Handle PO To GRN Material User Defined Stock And Supplier
	 * DisUom With And Without Inspection Plan
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */

	public void poToGRNMaterialUserDefinedDifferentStockAndSupplierUOMWithAndWithOutInspectionPlan() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectPODifUOMGENUD"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectPODifUOMGENUD"));
		POfunc.verifyDirectPODomesticMaterialDifferentUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				webDB.getDataFromProperties("DirectPODifUOMGENUD"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("DirectPODifUOMGENUD"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("DirectPODifUOMGENUD"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectPODifUOMGENUD"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectPODifUOMGENUD"));

	}

	/**
	 * This Methods Method to handle Create GRN Document from PO With and Without
	 * Inspection for Contract Item(General Type,Dissimilar UOM)
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnGeneralRateContractDifferentStockAndSupplierUOMWithAndWithoutInspectionGeneralType()
			throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DifferentUOMRateGen"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DifferentUOMRateGen"));
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralTypeForDissimilarUOM(
				webDB.getDataFromProperties("DifferentUOMRateGen"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("Loc_100"));
		multipleLotAndSerialItem(webDB.getDataFromProperties("DifferentUOMRateGen"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("DifferentUOMRateGen"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DifferentUOMRateGen"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DifferentUOMRateGen"));
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create the GRN Document from PO with and
	 * Without Inspection for RateContract Item(Material)
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnlRateContractStockAndSupplierDifferentUOMWithAndWithoutInspectionMaterial() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DifferentUOMRateGen"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DifferentUOMRateGen"));
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionMaterialForDifferentUom(
				webDB.getDataFromProperties("DifferentUOMRateGen"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("DifferentUOMRateGen"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("DifferentUOMRateGen"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DifferentUOMRateGen"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DifferentUOMRateGen"));
		assertTrue(flag);
	}

	/**
	 * This Methods Used To Verify stock Quantity For Quality Warehouse
	 * Document(Single Item)
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void verifyStockQtyAndCostForQualityWh(String SheetName) {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Before Stock Qty For This Item ");
		for (int i = 22; i < ItemMultiple.length; i++) {
			String ItemId = ItemMultiple[i][1];
			String WareHouse = ItemMultiple[i][44];
			String ExpectedStockQuantity = ItemMultiple[i][50];
			String ExpectedStockCost = ItemMultiple[i][51];
			if (!ItemId.equals("")) {
				String BeforeStockQty = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO",
						"Before InspectionWH Stock Qty For This Item " + ItemId + "Is : " + BeforeStockQty);
				logs.logging("INFO",
						"Expected InspectionWH Stock Qty For This Item " + ItemId + "Is : " + ExpectedStockQuantity);
				String BeforeStockCost = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO",
						"Before InspectionWH Stock Cost For This Item " + ItemId + "Is : " + BeforeStockCost);
				logs.logging("INFO",
						"Expected InspectionWH Stock Cost For This Item " + ItemId + "Is : " + ExpectedStockCost);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 46, BeforeStockQty);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 47, BeforeStockCost);
			}
		}

	}

	/**
	 * This Methods Used To Verify stock Quantity For Source Warehouse
	 * Document(Single Item)
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyStockQtyAndCostForSourceWH(String SheetName) {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Before Stock Qty For This Item ");
		for (int i = 22; i < ItemMultiple.length; i++) {
			String ItemId = ItemMultiple[i][1];
			String SourecWareHouse = ItemMultiple[i][63];
			String ExpectedStockQuantity = ItemMultiple[i][67];
			String ExpectedStockCost = ItemMultiple[i][66];
			if (!ItemId.equals("")) {
				String BeforeStockQty = dbCommonFunctions.StockQuery1(SourecWareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO",
						"Before Source WareHouse Stock Qty For This Item " + ItemId + "Is : " + BeforeStockQty);
				logs.logging("INFO", "Expected Source WareHouse Stock Qty For This Item " + ItemId + "Is : "
						+ ExpectedStockQuantity);
				String BeforeStockCost = dbCommonFunctions.StockQuery1(SourecWareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO",
						"Before Source WareHouse Stock Cost For This Item " + ItemId + "Is : " + BeforeStockCost);
				logs.logging("INFO",
						"Expected Source WareHouse Stock Cost For This Item " + ItemId + "Is : " + ExpectedStockCost);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 65, BeforeStockQty);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 64, BeforeStockCost);
			}
		}

	}

	/**
	 * This Methods Is Used After Give all Details to Click overall save and Forward
	 * the Approval User
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void overAllSaveAndPost(String SheetName) {
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
			verifyGrnDocumentCompareToPoDocument(SheetName);
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
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
	 * This Methods Is Used To verify GrnDocument Compare to PO document Details
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyGrnDocumentCompareToPoDocument(String SheetName) {
		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < PRMultipleline.length; i++) {
			String ItemId = PRMultipleline[i][1];
			if (!ItemId.equals("")) {
				System.out.println("tradevalueup");
				verifyAssertionBothValues(
						dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("TradeAssessableValue")),
						dbCommonFunctions.StockQuery3(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNTradeAssessableValue")));
				logs.logging("INFO", "Trade Assessable Value Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("SpecialAssessableValue")),
						dbCommonFunctions.StockQuery3(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNSpecialAssessableValue")));
				logs.logging("INFO", "Special Assessable Value Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("CashAssessableValue")),
						dbCommonFunctions.StockQuery3(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNCashAssessableValue")));
				logs.logging("INFO", "Cash Assessable Value Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("TradeDiscountAmount")),
						dbCommonFunctions.StockQuery3(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNTradeDiscountAmount")));
				logs.logging("INFO", "Trade Discount Amount Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("SpecialDiscountAmount")),
						dbCommonFunctions.StockQuery3(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNSpecialDiscountAmount")));
				logs.logging("INFO", "Special Discount Amount Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("CashDiscountAmount")),
						dbCommonFunctions.StockQuery3(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNCashDiscountAmount")));
				logs.logging("INFO", "Cash  Discount Amount Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PoIgstAmount")),
						dbCommonFunctions.StockQuery3(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GrnIgstAmount")));
				logs.logging("INFO", "IGST Tax Amount Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PoSgstAmount")),
						dbCommonFunctions.StockQuery3(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GrnSgstAmount")));
				logs.logging("INFO", "SGST  Tax Amount Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("POCgstAmount")),
						dbCommonFunctions.StockQuery3(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GrnCgstAmount")));
				logs.logging("INFO", "CGST  Tax Amount Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PoUtgstAmount")),
						dbCommonFunctions.StockQuery3(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GrnUtgstAmount")));
				logs.logging("INFO", "UTGST  Tax Amount Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PoCessAmount")),
						dbCommonFunctions.StockQuery3(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GrnCessAmount")));
				logs.logging("INFO", "UTGST  Tax Amount Matched For PO and GRN " + ItemId);
			}
		}
	}

	/**
	 * This Methods Is Used To verify Expected stock Qty And Cost For Quality
	 * WareHouse
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyExpectedStockQtAndCostyForQualityWH(String SheetName) {
		String[][] ItemDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		ItemDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < ItemDetails.length; i++) {
			String ItemId = ItemDetails[i][1];
			String WareHouse = ItemDetails[i][44];
			String AfterExcelStockCost = ItemDetails[i][51];
			if (!ItemId.equals("")) {
				String AfterStockCost = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO", "After GRN Actual Stock Cost For This Item " + ItemId + "Is : " + AfterStockCost);

				verifyAssertionBothValues(AfterStockCost, AfterExcelStockCost);
				logs.logging("INFO",
						"Verified Expected Stock Cost For This Item " + ItemId + " With Database Values After MRV");
			}
		}
	}

	/**
	 * This Methods Is Used To verify Expected stock Qty And Cost For Source
	 * WareHouse
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(String SheetName) {
		String[][] ItemDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		ItemDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < ItemDetails.length; i++) {
			String ItemId = ItemDetails[i][1];
			String SourecWareHouse = ItemDetails[i][63];
			String AfterExcelStockQuantity = ItemDetails[i][67];
			String AfterExcelStockCost = ItemDetails[i][66];
			if (!ItemId.equals("")) {

				String AfterStockQty = dbCommonFunctions.StockQuery1(SourecWareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO", "After MRV Source WareHouse Actual Stock Qty For This Item " + ItemId + "Is : "
						+ AfterStockQty);

				verifyAssertionBothValues(AfterStockQty, AfterExcelStockQuantity);
				logs.logging("INFO", "Verified Expected Source WareHouse Stock Qty For This Item " + ItemId
						+ " With Database Values After MRV");
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				String AfterStockCost = dbCommonFunctions.StockQuery1(SourecWareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO", "After MRV Source WareHouse Actual Stock Qty For This Item " + ItemId + "Is : "
						+ AfterStockCost);

				verifyAssertionBothValues(AfterStockCost, AfterExcelStockCost);
				logs.logging("INFO", "Verified Expected Source WareHouse Stock Cost For This Item " + ItemId
						+ " With Database Values After MRV");
			}
		}
	}

	/**
	 * This Methods Is Used To Add Lot and Serial Number for Multiple Item
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void multipleLotAndSerialItem(String SheetName) throws Exception {
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
						+ "DBDETAILS.xlsx";
				itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
				for (int i = 22; i < itemmultiple.length; i++) {
					String ITEM_NAME = itemmultiple[i][1];
					String LOT_SERIAL = itemmultiple[i][54];
					String LOT_NUMBER = itemmultiple[i][55];
					String SERIAL_NUMBER = itemmultiple[i][56];
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
	 * Check Assertion Both Values
	 * 
	 * @author Deepan-GWL
	 * @param Actual
	 * @param Expected
	 */
	public void verifyAssertionBothValues(String Actual, String Expected) {
		logs.logging("INFO", "Actual DataBase Value Is: " + Actual);
		logs.logging("INFO", "Expected Value Is: " + Expected);
		flag = Actual.equals(Expected);
		if (flag) {
			logs.logging("INFO", "Verified Actual DataBase Value 8: " + Actual + " And Expected Values : " + Expected);
		} else {
			logs.logging("INFO",
					"Mismatch With Actual DataBase Value : " + Actual + " And Expected Values " + Expected);
		}
		assertTrue(flag);
	}

	/**
	 * This Method to Handle POTo GRN Service Price List Same Stock And Supplier UOM
	 * WO Inspection Plan
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	public void poToGRNServicePriceListSameStockAndSupplierUOMWOInspectionPlan() throws IOException {
		POfunc.verifyDirectPODomesticPriceListItemSimilarUOMMultipleServiceType(
				webDB.getDataFromProperties("DirectPoPriceListSer"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("Loc_100"));
		overAllSaveAndPost(webDB.getDataFromProperties("DirectPoPriceListSer"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Method to handle Create the GRN Document from PO with PriceList Item for
	 * General Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnMaterialPriceListSameStockAndSupplierUOMWithandWithoutInspectionPlan() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectPoPriceListGen"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectPoPriceListGen"));
		POfunc.verifyDirectPODomesticPricelistItemWithItemandWithoutInspectionPlanForMaterialType(
				webDB.getDataFromProperties("DirectPoPriceListGen"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("DirectPoPriceListGen"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("DirectPoPriceListGen"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectPoPriceListGen"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectPoPriceListGen"));
	}

	/**
	 * This Method to handle Create the GRN Document from PO with PriceList Item for
	 * General Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnGeneralPriceListDifferentStockAndSupplierUOMWithandWithoutInspectionPlan() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DifferentUompriceListGen"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DifferentUompriceListGen"));
		POfunc.verifyDirectPODomesticPricelistItemWithItemandWithoutInspectionPlanForDifferentUom(
				webDB.getDataFromProperties("DifferentUompriceListGen"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("DifferentUompriceListGen"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("DifferentUompriceListGen"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DifferentUompriceListGen"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DifferentUompriceListGen"));
	}

	/**
	 * This Method to handle Create the GRN Document from PO with PriceList Item for
	 * General Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnMaterialPriceListDifferentStockAndSupplierUOMWithandWithoutInspectionPlan() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DifferentUompriceListGen"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DifferentUompriceListGen"));
		POfunc.verifyDirectPODomesticDifferentUomPricelistItemWithItemandWithoutInspectionPlanForMaterialType(
				webDB.getDataFromProperties("DifferentUompriceListGen"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("DifferentUompriceListGen"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("DifferentUompriceListGen"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DifferentUompriceListGen"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DifferentUompriceListGen"));
	}

	/**
	 * This Methods Method to handle Create GRN From PO With General With And
	 * Without Inspection Document(Multiple Item)
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithoutQualityEnabled()
			throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		POfunc.verifyDirectPODomesticGeneralSimilarUOMWithMultipleItemWithoutQualityEnabled(
				webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));

		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create GRN Document from PO With and Without
	 * Inspection for UserDefined Item(Material)Without Quality Enabled
	 * 
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */

	public void poToGrnMaterialUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithoutQualityEnabled()
			throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		POfunc.verifyDirectPODomesticMaterialSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItemWithOutQuality(
				webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create GRN Document from PO With and Without
	 * Inspection for Contract Item(General Type)
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnGeneralRateContractSameStockAndSupplierUOMWithAndWithoutInspectionGeneralTypeForWithoutQualityEnabled()
			throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("POwithoutQualityRateGen"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("POwithoutQualityRateGen"));
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralTypeForWithoutQualityEnabled(
				webDB.getDataFromProperties("POwithoutQualityRateGen"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("Loc_100"));
		multipleLotAndSerialItem(webDB.getDataFromProperties("POwithoutQualityRateGen"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("POwithoutQualityRateGen"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("POwithoutQualityRateGen"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("POwithoutQualityRateGen"));
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create the GRN Document from PO with and
	 * Without Inspection for RateContract Item(Material)
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnlRateContractSameStockAndSupplierUOMWithAndWithoutInspectionMaterialWithoutQualityEnabled()
			throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("POwithoutQualityRateGen"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("POwithoutQualityRateGen"));
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionMaterialWithoutQualityEnabled(
				webDB.getDataFromProperties("POwithoutQualityRateGen"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("POwithoutQualityRateGen"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("POwithoutQualityRateGen"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("POwithoutQualityRateGen"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("POwithoutQualityRateGen"));
		assertTrue(flag);
	}

	/**
	 * This Method to handle Create the GRN Document from PO with PriceList Item for
	 * General Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnGeneralPriceListSameStockAndSupplierUOMWithandWithoutInspectionPlanWithoutQualityEnabled()
			throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PriceListGenWOQE"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PriceListGenWOQE"));
		POfunc.verifyDirectPODomesticPricelistItemWithItemandWithoutInspectionPlanWithoutQualityEnabled(
				webDB.getDataFromProperties("PriceListGenWOQE"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("PriceListGenWOQE"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PriceListGenWOQE"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PriceListGenWOQE"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("PriceListGenWOQE"));
	}

	/**
	 * This Method to handle Create the GRN Document from PO with PriceList Item for
	 * General Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnMaterialPriceListSameStockAndSupplierUOMWithandWithoutInspectionPlanWithoutQualityEnabled()
			throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PriceListGenWOQE"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PriceListGenWOQE"));
		POfunc.verifyDirectPODomesticPricelistItemWithItemandWithoutInspectionPlanForMaterialTypeWithoutQualityEnabled(
				webDB.getDataFromProperties("PriceListGenWOQE"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("PriceListGenWOQE"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PriceListGenWOQE"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PriceListGenWOQE"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("PriceListGenWOQE"));
	}

	/**
	 * This Method to Handle PO To GRN General User Defined Stock And Supplier
	 * DisUom With And Without Inspection Plan Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnGeneralUserDefinedStockAndSupplierDisUomWithAndWithoutInspectionPlanWithoutQualityEnabled()
			throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGrnDifferentUomGenWOQE"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGrnDifferentUomGenWOQE"));
		POfunc.verifyDirectPODomesticGeneralDissimilarUOMWithMultipleItemWithoutQualityEnabled(
				webDB.getDataFromProperties("PoToGrnDifferentUomGenWOQE"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("PoToGrnDifferentUomGenWOQE"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToGrnDifferentUomGenWOQE"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGrnDifferentUomGenWOQE"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGrnDifferentUomGenWOQE"));

	}

	/**
	 * This Method to Handle PO To GRN Material User Defined Stock And Supplier
	 * DisUom With And Without Inspection Plan
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */

	public void poToGRNMaterialUserDefinedDifferentStockAndSupplierUOMWithAndWithOutInspectionPlanWithoutQualityEnabled()
			throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGrnDifferentUomGenWOQE"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGrnDifferentUomGenWOQE"));
		POfunc.verifyDirectPODomesticMaterialDifferentUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				webDB.getDataFromProperties("PoToGrnDifferentUomGenWOQE"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("PoToGrnDifferentUomGenWOQE"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToGrnDifferentUomGenWOQE"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGrnDifferentUomGenWOQE"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGrnDifferentUomGenWOQE"));
	}

	/**
	 * This Methods Method to handle Create GRN Document from PO With and Without
	 * Inspection for Contract Item(General Type,Dissimilar UOM)Without Quality
	 * Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnGeneralRateContractDifferentStockAndSupplierUOMWithAndWithoutInspectionGeneralTypeWithoutQualityEnabled()
			throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralTypeForDissimilarUOM(
				webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		commonFunc.hardWait(1000);
		multipleLotAndSerialItem(webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create the GRN Document from PO with and
	 * Without Inspection for RateContract Item(Material)
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnlRateContractStockAndSupplierDifferentUOMWithAndWithoutInspectionMaterialWithoutQualityEnabled()
			throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionMaterialForDifferentUom(
				webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		assertTrue(flag);
	}

	/**
	 * This Method to handle Create the GRN Document from PO with PriceList Item for
	 * General Type WithoutQualityEnabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnGeneralPriceListDifferentStockAndSupplierUOMWithandWithoutInspectionPlanWithoutQualityEnabled()
			throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		POfunc.verifyDirectPODomesticPricelistItemWithItemandWithoutInspectionPlanForDifferentUom(
				webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
	}

	/**
	 * This Method to handle Create the GRN Document from PO with PriceList Item for
	 * General Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnMaterialPriceListDifferentStockAndSupplierUOMWithandWithoutInspectionPlanWithoutQualityEnabled()
			throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		POfunc.verifyDirectPODomesticDifferentUomPricelistItemWithItemandWithoutInspectionPlanForMaterialType(
				webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("PotoGRNDiffUOMPriceListMatWQE"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
	}

	/**
	 * This Method to handle Create the MRV Document Based On Application Control
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	public void verifyTocreateMrvDocumentBasedOnAplicationControl() {
		String ApplicationControlPostMrv = dbCommonFunctions.InAplctrl(commonFunc.getDataFromTestDataSheet("ControlBU"),
				commonFunc.getDataFromTestDataSheet("AutoPostMRV"));
		logs.logging("INFO", "AutoPost Flag Is " + ApplicationControlPostMrv);
		String ApplicationcontrolCreationMrv = dbCommonFunctions.InAplctrl(
				commonFunc.getDataFromTestDataSheet("ControlBU"),
				commonFunc.getDataFromTestDataSheet("AutoCreationMRV"));
		logs.logging("INFO", "Autocreation Flag Is " + ApplicationcontrolCreationMrv);
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToMaterialReceiptVoucher();
		if (!ApplicationcontrolCreationMrv.equals(commonFunc.getDataFromTestDataSheet("NotCheck"))
				&& ApplicationControlPostMrv.equals(commonFunc.getDataFromTestDataSheet("NotCheck"))) {
			System.out.println("CheckCreateflagAndNotCheckPostFlag");
			logs.logging("INFO", "ApplicationControlFlag Is" + "CheckCreateflagAndNotCheckPostFlag");
			ArrayList<String> MrvNumberarray = dbCommonFunctions.MrvDocDetails(VariableHandler.getGrnDocNumber(),
					commonFunc.getDataFromTestDataSheet("MRVNo"));
			System.out.println("MRVNo");
			logs.logging("INFO", "MRVNumber Is" + MrvNumberarray);
			for (String MRVNo : MrvNumberarray) {
				mrvFunc.verifyOpenAlreadyCreatedMRVDocumentsWithGRNSCREEN(MRVNo);
				System.out.println(MRVNo);
			}
		} else if (ApplicationcontrolCreationMrv.equals(commonFunc.getDataFromTestDataSheet("NotCheck"))
				&& ApplicationControlPostMrv.equals(commonFunc.getDataFromTestDataSheet("NotCheck"))) {
			System.out.println("NotCheckCreateflagAndNotCheckPostFlag");
			logs.logging("INFO", "ApplicationControlFlag Is" + "NotCheckCreateflagAndNotCheckPostFlag");
			mrvFunc.verifyPostMaterialReceiptWithAndWithoutLocatorBasedOnApplicationControl();
		} else if (!ApplicationcontrolCreationMrv.equals(commonFunc.getDataFromTestDataSheet("NotCheck"))
				&& !ApplicationControlPostMrv.equals(commonFunc.getDataFromTestDataSheet("NotCheck"))) {
			getMaterialReceiptVoucherDocumentNumberFromDB();
		} else if (ApplicationcontrolCreationMrv.equals(commonFunc.getDataFromTestDataSheet("NotCheck"))
				&& !ApplicationControlPostMrv.equals(commonFunc.getDataFromTestDataSheet("NotCheck"))) {
			getMaterialReceiptVoucherDocumentNumberFromDB();

		}
	}

	/**
	 * This Method to handle Create the MRV Document Based On Application Control
	 * From DB
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void getMaterialReceiptVoucherDocumentNumberFromDB() {
		ArrayList<String> MrvNumberarray = dbCommonFunctions.MrvDocDetails(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("MRVNo"));
		ArrayList<String> MrvDocStatus = dbCommonFunctions.MrvDocDetails(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("MRVDBDocStatus"));
		if (MrvNumberarray == null || MrvDocStatus == null || MrvNumberarray.size() != MrvDocStatus.size()) {
			logs.logging("ERROR", "Mismatch or null in MRV document arrays.");
			return;
		}
		VariableHandler.setMultiMRVDocNumber(MrvNumberarray);
		VariableHandler.setMultiMRVDocStatus(MrvDocStatus);
		for (int i = 0; i < MrvNumberarray.size(); i++) {
			String docNumber = MrvNumberarray.get(i);
			String docStatus = MrvDocStatus.get(i);
			logs.logging("INFO", "Get Text MRV Document Against The GRN Document Is : " + docNumber
					+ " And That MRV Document Status Is " + docStatus);
			if ("N".equals(docStatus)) {
				mrvFunc.verifyOpenAlreadyCreatedMRVDocumentsWithGRNScreen(docNumber);
				commonFunc.checkRefreshCellsDisappear();
			}
		}
	}

	/**
	 * This Method to handle After Approve the Status Document Move to QC or MRV
	 * Screen
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransferType() throws Exception {
		String GrnHeaderWiseStatus = dbCommonFunctions.GrnHeaderDetails(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("ProcessFlowStatus"));
		logs.logging("INFO", "GRN Document ProcessFlow Status Is " + GrnHeaderWiseStatus);

		if (GrnHeaderWiseStatus.equals(commonFunc.getDataFromTestDataSheet("QualityEnabled"))) {
			qcFunc.InspectionDocumentWithInspectionPlanForStocktransferType();
		}
		verifyTocreateMrvDocumentBasedOnAplicationControl();
		assertTrue(flag);
	}

	/**
	 * This Method to Handle PO to GRN Stock Transfer User Defined Same Stock and
	 * SupplierUOM With And Withou tInspection
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGRNStockTransferUserDefinedDissimilarUOMWithAndWithoutInspection() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("StockTransferUDDifferentUOM"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("StockTransferUDDifferentUOM"));
		POfunc.verifyToCreatePoForStockTransferUserDefinedDissimilarUomWithAndWithoutInspection(
				webDB.getDataFromProperties("StockTransferUDDifferentUOM"));
		verifyToStockTransferSaleOrderDocumentPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSalesOrdertDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocument();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		createGrnDocumentforwithandwithoutInspectionStockTransferType(
				webDB.getDataFromProperties("StockTransferUDDifferentUOM"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("StockTransferUDDifferentUOM"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransferType();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("StockTransferUDDifferentUOM"));
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create GRN From PO With General With And
	 * Without Inspection Document(Multiple Item)
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnGeneralUserDefinedSameStockAndSupplierUomFreePassItemInterstate() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		POfunc.verifyDirectPODomesticGeneralSimilarUOMWithMultipleItemWithoutQualityEnabled(
				webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create GRN Document from PO With and Without
	 * Inspection for UserDefined Item(Material)
	 * 
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */

	public void poToGrnMaterialUserDefinedSameStockAndSupplierUomFreepassItemIgst() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		POfunc.verifyDirectPODomesticMaterialSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItemWithOutQuality(
				webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("POwithGrnWithoutQualityEnabled"));
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create GRN From PO With Service Type
	 * Document(Single Item)
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyGrnPODomesticServiceSimilarUOMfromPoWithIgstSupplier() {
		POfunc.verifyDirectPODomesticServiceSimilarUOMMultiple(webDB.getDataFromProperties("DirectPOSerUD"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		overAllSaveAndPost(webDB.getDataFromProperties("DirectPOSerUD"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyReturnToGrnPageHeaderStatus();
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create GRN Document from PO With and Without
	 * Inspection for Contract Item(General Type)
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnGeneralRateContractSameStockAndSupplierUOMGeneralTypeFreePass() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("FreePassUDRatContract"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("FreePassUDRatContract"));
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralType(
				webDB.getDataFromProperties("FreePassUDRatContract"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("FreePassUDRatContract"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("FreePassUDRatContract"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("FreePassUDRatContract"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("FreePassUDRatContract"));
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create the GRN Document from PO with and
	 * Without Inspection for RateContract Item(Material)
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnlRateContractSameStockAndSupplierUOMWithFreePassIgstSupplier() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("FreePassUDRatContract"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("FreePassUDRatContract"));
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionMaterialWithoutQualityEnabled(
				webDB.getDataFromProperties("FreePassUDRatContract"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("FreePassUDRatContract"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("FreePassUDRatContract"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("FreePassUDRatContract"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("FreePassUDRatContract"));
		assertTrue(flag);
	}

	/**
	 * This Method to handle Create the GRN Document from PO with PriceList Item for
	 * General Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnGeneralPriceListSameStockAndSupplierUOMWithandWithFreePassIgstSupplier() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PriceListGenWOQE"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PriceListGenWOQE"));
		POfunc.verifyDirectPODomesticPricelistItemWithItemandWithoutInspectionPlanWithoutQualityEnabled(
				webDB.getDataFromProperties("PriceListGenWOQE"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("PriceListGenWOQE"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PriceListGenWOQE"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PriceListGenWOQE"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("PriceListGenWOQE"));
	}

	/**
	 * This Method to handle Create the GRN Document from PO with PriceList Item for
	 * General Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnMaterialPriceListSameStockAndSupplierUOMWithandWithFreePassIgstSupplier() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PriceListGenWOQE"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PriceListGenWOQE"));
		POfunc.verifyDirectPODomesticPricelistItemWithItemandWithoutInspectionPlanForMaterialTypeWithoutQualityEnabled(
				webDB.getDataFromProperties("PriceListGenWOQE"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("PriceListGenWOQE"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PriceListGenWOQE"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PriceListGenWOQE"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("PriceListGenWOQE"));
	}

	/**
	 * This Method to Handle POTo GRN Service Price List Same Stock And Supplier UOM
	 * WO Inspection Plan
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	public void poToGRNServicePriceListSameStockAndSupplierUOMWOWithFreePassAndIgstSupplier() throws IOException {
		POfunc.verifyDirectPODomesticPriceListItemSimilarUOMMultipleServiceType(
				webDB.getDataFromProperties("DirectPoPriceListSer"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		overAllSaveAndPost(webDB.getDataFromProperties("DirectPoPriceListSer"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Method to handle Create GRN Document from PO With and Without
	 * Inspection for Contract Item(General Type,Dissimilar UOM)Without Quality
	 * Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnGeneralRateContractDifferentStockAndSupplierUOMWithAndWithoutInspectionGeneralForFreePassItem()
			throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralTypeForDissimilarUOM(
				webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		commonFunc.hardWait(1000);
		multipleLotAndSerialItem(webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create GRN Document from PO With and Without
	 * Inspection for Contract Item(General Type,Dissimilar UOM)Without Quality
	 * Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnMaterialRateContractDifferentStockAndSupplierUOMWithAndWithoutInspectionGeneralForFreePassItem()
			throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionMaterialForDifferentUom(
				webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("Loc_100"));
		commonFunc.hardWait(4000);
		multipleLotAndSerialItem(webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGrnRateContractGenWQE"));
	}

	/**
	 * This Method to handle Create the GRN Document from PO with PriceList Item for
	 * General Type WithoutQualityEnabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnGeneralPriceListDifferentStockAndSupplierUOMForFreePassItem() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		POfunc.verifyDirectPODomesticPricelistItemWithItemGeneralTypeForFreePassItem(
				webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
	}

	/**
	 * This Method to handle Create the GRN Document from PO with PriceList Item for
	 * General Type WithoutQualityEnabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnMaterialPriceListDifferentStockAndSupplierUOMForFreePassItem() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		POfunc.verifyDirectPODomesticPricelistItemWithMaterialTypeForFreePassItem(
				webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		multipleLotAndSerialItem(webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PotoGRNDiffUOMPriceListGenWQE"));
	}

	/**
	 * This Method to handle Create the GRN Document For Stock Transfer Type With
	 * Rate Contract Item
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void PoToGrnStockTransferRateContractDifferentUomWithAndWithoutInspection() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("StockTransferRateDifferent"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("StockTransferRateDifferent"));
		POfunc.verifyToCreatePoForStockTransferRateContractSameStockandSupplierUomWithAndWithoutInspection(
				webDB.getDataFromProperties("StockTransferRateDifferent"));
		verifyToStockTransferSaleOrderDocumentPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSalesOrdertDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocument();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		createGrnDocumentforwithandwithoutInspectionStockTransferType(
				webDB.getDataFromProperties("StockTransferRateDifferent"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("StockTransferRateDifferent"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransferType();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("StockTransferRateDifferent"));
		assertTrue(flag);
	}

	/**
	 * This Method to Handle PO To GRN Stock Transfer Price List Same Stock and
	 * Supplier UOM With And WO Inspection
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnStockTransferPriceListDifferentUOMWithAndWOInspection() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("StocktransferPriceListDifferent"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("StocktransferPriceListDifferent"));
		POfunc.verifyToCreatePoForStockTransferPriceListItemForSameStockandSupplierUomWithAndWithoutInspection(
				webDB.getDataFromProperties("StocktransferPriceListDifferent"));
		verifyToStockTransferSaleOrderDocumentPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSalesOrdertDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocument();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		createGrnDocumentforwithandwithoutInspectionStockTransferType(
				webDB.getDataFromProperties("StocktransferPriceListDifferent"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("StocktransferPriceListDifferent"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransferType();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("StocktransferPriceListDifferent"));
		assertTrue(flag);
	}

	/**
	 * This Method to Handle PO To GRN Stock Transfer Price List Same Stock and
	 * Supplier UOM With And WO Inspection
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnStockTransferPriceListDifferentUOMForFreePassItem() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("StockTransferPricelistFreePass"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("StockTransferPricelistFreePass"));
		POfunc.verifyToCreatePoForStockTransferPriceListItemForSameStockandSupplierUomWithAndWithoutInspection(
				webDB.getDataFromProperties("StockTransferPricelistFreePass"));
		verifyToStockTransferSaleOrderDocumentPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSalesOrdertDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocument();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		createGrnDocumentforwithandwithoutInspectionStockTransferType(
				webDB.getDataFromProperties("StockTransferPricelistFreePass"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("StockTransferPricelistFreePass"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransferType();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("StockTransferPricelistFreePass"));
		assertTrue(flag);
	}

	/**
	 * This Method to Handle PO To GRN Stock Transfer Price List Same Stock and
	 * Supplier UOM With And WO Inspection
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void PoToGRNStockTransferUserDefinedSameStockAndSupplierUOMWithInspectionPlan() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("StockTransferPOUserDefined"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("StockTransferPOUserDefined"));
		POfunc.verifyToCreatePoForStockTransferUserDefinedSameStockandSupplierUomWithAndWithoutInspection(
				webDB.getDataFromProperties("StockTransferPOUserDefined"));
		verifyToStockTransferSaleOrderDocumentPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSalesOrdertDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocument();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		createGrnDocumentforwithandwithoutInspectionStockTransferType(
				webDB.getDataFromProperties("StockTransferPOUserDefined"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("StockTransferPOUserDefined"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransferType();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("StockTransferPOUserDefined"));
		assertTrue(flag);
	}

	/**
	 * This Method to handle Create the GRN Document For Stock Transfer Type With
	 * Rate Contract Item
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void PoToGrnStockTransferRateContractSameUomWithAndWithoutInspection() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("StockTransferPORateContract"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("StockTransferPORateContract"));
		POfunc.verifyToCreatePoForStockTransferRateContractSameStockandSupplierUomWithAndWithoutInspection(
				webDB.getDataFromProperties("StockTransferPORateContract"));
		verifyToStockTransferSaleOrderDocumentPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSalesOrdertDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocument();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		createGrnDocumentforwithandwithoutInspectionStockTransferType(
				webDB.getDataFromProperties("StockTransferPORateContract"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("StockTransferPORateContract"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransferType();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("StockTransferPORateContract"));
		assertTrue(flag);
	}

	/**
	 * This Method to Handle PO To GRN Stock Transfer Price List Same Stock and
	 * Supplier UOM With And WO Inspection
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnStockTransferPriceListSameStockandSupplierUOMWithAndWOInspectionWithoutQualityEnabled()
			throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("StockTransferPOpriceList"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("StockTransferPOpriceList"));
		POfunc.verifyToCreatePoForStockTransferPriceListItemForSameStockandSupplierUomWithAndWithoutInspection(
				webDB.getDataFromProperties("StockTransferPOpriceList"));
		verifyToStockTransferSaleOrderDocumentPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSalesOrdertDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocument();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		createGrnDocumentforwithandwithoutInspectionStockTransferType(
				webDB.getDataFromProperties("StockTransferPOpriceList"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("StockTransferPOpriceList"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransferType();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("StockTransferPOpriceList"));
		assertTrue(flag);
	}

	/**
	 * This Method to Handle PO To GRN Stock Transfer Price List Same Stock and
	 * Supplier UOM With And WO Inspection
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void PoToGRNStockTransferUserDefinedSameStockAndSupplierUOMWithInspectionPlanForFreePassItem()
			throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("StockTransferPOUserDefined"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("StockTransferPOUserDefined"));
		POfunc.verifyToCreatePoForStockTransferUserDefinedSameStockandSupplierUomWithAndWithoutInspection(
				webDB.getDataFromProperties("StockTransferPOUserDefined"));
		verifyToStockTransferSaleOrderDocumentPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSalesOrdertDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocument();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		createGrnDocumentforwithandwithoutInspectionStockTransferType(
				webDB.getDataFromProperties("StockTransferPOUserDefined"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("StockTransferPOUserDefined"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransferType();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("StockTransferPOUserDefined"));
		assertTrue(flag);
	}

	/**
	 * This Method to Handle PO To GRN Stock Transfer Price List Same Stock and
	 * Supplier UOM With And WO Inspection
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnStockTransferPriceListSameStockandSupplierUOMWithAndWOInspectionForFreePass() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("StockTransferPOpriceList"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("StockTransferPOpriceList"));
		POfunc.verifyToCreatePoForStockTransferPriceListItemForSameStockandSupplierUomWithAndWithoutInspection(
				webDB.getDataFromProperties("StockTransferPOpriceList"));
		verifyToStockTransferSaleOrderDocumentPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSalesOrdertDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocument();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		createGrnDocumentforwithandwithoutInspectionStockTransferType(
				webDB.getDataFromProperties("StockTransferPOpriceList"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("StockTransferPOpriceList"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransferType();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("StockTransferPOpriceList"));
		assertTrue(flag);
	}

	/**
	 * This Method to handle Create the GRN Document For Stock Transfer Type With
	 * Rate Contract Item
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForWithoutQualityEnabledItem()
			throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("StockTransferPORateContract"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("StockTransferPORateContract"));
		POfunc.verifyToCreatePoForStockTransferRateContractSameStockandSupplierUomWithAndWithoutInspection(
				webDB.getDataFromProperties("StockTransferPORateContract"));
		verifyToStockTransferSaleOrderDocumentPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSalesOrdertDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocument();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		createGrnDocumentforwithandwithoutInspectionStockTransferType(
				webDB.getDataFromProperties("StockTransferPORateContract"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("StockTransferPORateContract"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransferType();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("StockTransferPORateContract"));
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create GRN Document from PO With and Without
	 * Inspection for Contract Item(General Type)
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnGeneralRateContractSameStockAndSupplierUOMWithAndWithoutInspectionGeneralTypeCheckingWithToleranceQty()
			throws Exception {
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralType(
				webDB.getDataFromProperties("ToleranceQty"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNoAndItemWise();
		verifyToEnteredToleranceQty();
		clickSelectAllButtonForExcessQty();
		createGrn();
		supplierBillNumberAndDcNumber();
//		multipleLotAndSerialItem(webDB.getDataFromProperties("DirectPORateGen"));
//		lotSerialQtyApportion();
		overAllSaveAndPostForExcess(webDB.getDataFromProperties("ToleranceQty"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Click Pending PO and Search PO Number in Header
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void clickPendingPoAndSearchPoNoAndItemWise() {
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
					webDB.switchToFrame(GrnLocators.PendingPOIframe, ElementType.Xpath);
					logs.logging("INFO", "PO Number Succesfully search and Selected");
					commonFunc.hardWait(2000);
					commonFunc.searchAndSelectValueInFilters(GrnLocators.PendingPOItemWiseSearch,
							commonFunc.getDataFromTestDataSheet("ItemTol"));
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
	 * This Methods Is Used After Give all Details to Click overall save and Forward
	 * the Approval User
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void overAllSaveAndPostForExcess(String SheetName) {
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(GrnLocators.OverAllSave, ElementType.Xpath);
			if (flag)
				webDB.clickAnElement(GrnLocators.OverAllSave, ElementType.Xpath);
			logs.logging("INFO", "Document Successfully Saved");
			commonFunc.hardWait(2000);
			flag = webDB.waitForClickElement(GrnLocators.PostButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.PostButton, ElementType.Xpath);
				logs.logging("INFO", "Document Successfully posted");
				flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used Entered Tolerance Qty
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void verifyToEnteredToleranceQty() {
		commonFunc.hardWait(5000);
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(GrnLocators.PendingPOIframe, ElementType.Xpath);
			flag = webDB.waitForElement(GrnLocators.PendingPoExcessQty, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.PendingPoExcessQty, ElementType.Xpath);
				commonFunc.hardWait(2000);
				webDB.clearText(GrnLocators.PendingPoExcessQty, ElementType.Xpath);
				commonFunc.hardWait(2000);
				webDB.sendTextToAnElement(GrnLocators.PendingPoExcessQty,
						commonFunc.getDataFromTestDataSheet("ToleranceQty"), ElementType.Xpath);
				logs.logging("INFO", "Able Select the Document");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used Entered Excess Qty
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void verifyToEnteredExcessQty() {
		commonFunc.hardWait(5000);
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(GrnLocators.PendingPOIframe, ElementType.Xpath);
			flag = webDB.waitForElement(GrnLocators.PendingPoExcessQty, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.PendingPoExcessQty, ElementType.Xpath);
				commonFunc.hardWait(2000);
				webDB.clearText(GrnLocators.PendingPoExcessQty, ElementType.Xpath);
				commonFunc.hardWait(2000);
				webDB.sendTextToAnElement(GrnLocators.PendingPoExcessQty,
						commonFunc.getDataFromTestDataSheet("ExcessQty"), ElementType.Xpath);
				logs.logging("INFO", "Able Select the Document");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Click Fully selected
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void clickSelectAllButtonForExcessQty() {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(3000);
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
		assertTrue(flag);
	}

	/**
	 * This Method to handle After Approve the Status Document Move to QC or MRV
	 * Screen
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	public void aafterVerifyTheStatusDocumentMoveToQcOrMrv() throws IOException {
		String GrnHeaderWiseStatus = dbCommonFunctions.GrnHeaderDetails(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("ProcessFlowStatus"));
		logs.logging("INFO", "GRN Document ProcessFlow Status Is " + GrnHeaderWiseStatus);

		if (GrnHeaderWiseStatus.equals(commonFunc.getDataFromTestDataSheet("QualityEnabled"))) {
			qcFunc.InspectionDocumentWithInspectionPlan();
		} else {
			verifyTocreateMrvDocumentBasedOnAplicationControl();
			assertTrue(flag);
		}

	}

	/**
	 * This Methods Method to handle Create GRN From PO With General With And
	 * Without Inspection Document(Multiple Item)
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLinewiseCancelled()
			throws IOException {
		POfunc.verifyDirectPODomesticGeneralSimilarUOMWithMultipleItem(webDB.getDataFromProperties("ToleranceQty"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		grnDocumentNumber();
		verifyToClickLinewiseCancelled();
		verifyToCheckLinewiseStatus();
		validateGrnLinewiseStatus(commonFunc.getDataFromTestDataSheet("LineWiseStatus"));

	}

	/**
	 * This Methods Method to handle Create Get GRN Document Number
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void grnDocumentNumber() {
		String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		VariableHandler.setGrnDocNumber(GrnNumber);
		logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());

	}

	/**
	 * This Method To Verify To Click Line wise Cancelled
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToClickLinewiseCancelled() {
		flag = webDB.waitForElement(GrnLocators.GrnLineWiseDeleteButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.GrnLineWiseDeleteButton, ElementType.Xpath);
			logs.logging("INFO", "Successfully LineWise Cancelled");
			commonFunc.hardWait(3000);
		}
		assertTrue(flag);
	}

	/**
	 * This Method To Verify To Click Line wise Status
	 * 
	 * @author PremKumar.K-RMITS
	 */
	private void verifyToCheckLinewiseStatus() {
		if (flag) {
			webDB.clickAnElement(GrnLocators.GrnSearchButton, ElementType.Xpath);
			flag = webDB.waitForElement(GrnLocators.FindPageGrnNoLov, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.FindPageGrnNoLov, ElementType.Xpath);
				flag = webDB.waitForElement(GrnLocators.GrnNumberInput, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(GrnLocators.GrnNumberInput,
							VariableHandler.getGrnDocNumber());
					logs.logging("INFO", "Document Number Search And Selected Successfully");
					flag = webDB.waitForElement(GrnLocators.GrnSearchButton, ElementType.Xpath);
					webDB.clickAnElement(GrnLocators.GrnSearchButton, ElementType.Xpath);
					logs.logging("INFO", "Document Searched SuccessFully");

					flag = webDB.waitForElement(GrnLocators.GrnLineWiseStatus, ElementType.Xpath);
					if (flag) {
						String GRNSTATUS = webDB.getTextFromElement(GrnLocators.GrnLineWiseStatus, ElementType.Xpath);
						VariableHandler.setGrnStatus(GRNSTATUS);
						logs.logging("INFO", "GRN LineWise STATUS:" + VariableHandler.getGrnStatus());
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
	public void validateGrnLinewiseStatus(String LineWiseStatus) {
		flag = webDB.waitForElement(GrnLocators.GrnLineWiseStatus, ElementType.Xpath);
		if (flag) {
			String DisplayText = webDB.getTextFromElement(GrnLocators.GrnLineWiseStatus, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Expected Text: " + LineWiseStatus + " Displayed: " + DisplayText);
			}
			flag = DisplayText.equalsIgnoreCase(LineWiseStatus);
			if (flag) {
				logs.logging("INFO", "verified LineWise Status Matched: " + DisplayText);
			} else {
				logs.logging("INFO", "verified LineWise Status MissMatched");
				logs.logging("INFO", "Expected Text: " + LineWiseStatus + " Displayed: " + DisplayText);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create GRN From PO With General With And
	 * Without Inspection Document(Multiple Item)
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithDocumentwiseCancelled()
			throws IOException {
		POfunc.verifyDirectPODomesticGeneralSimilarUOMWithMultipleItem(webDB.getDataFromProperties("ToleranceQty"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingPoAndSearchPoNo();
		clickSelectAllButton();
		createGrn();
		supplierBillNumberAndDcNumber();
		grnDocumentNumber();
		verifyToClickDocumentwiseCancelled();
		verifyToCheckDocumentwiseStatus();
		validateGrnDocumentwiseStatus(commonFunc.getDataFromTestDataSheet("DocumentWiseStatus"));
	}

	/**
	 * This Method To Verify To Document wise Cancelled
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToClickDocumentwiseCancelled() {
		flag = webDB.waitForElement(GrnLocators.GrnDocumentwiseCancelButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.GrnDocumentwiseCancelButton, ElementType.Xpath);
			logs.logging("INFO", "Successfully Document Wise Cancel Button Clicked");
			commonFunc.hardWait(3000);
			flag = webDB.waitForElement(GrnLocators.GrnDocumentwiseCancelReasonLov, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(GrnLocators.GrnDocumentwiseCancelReasonLov,
						commonFunc.getDataFromTestDataSheet("GRNcancellReason"));
				logs.logging("INFO", "Successfully Document Wise Cancelled Reason Selected");

				flag = webDB.waitForElement(GrnLocators.GrnDocumentCancelledReasonOKButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(GrnLocators.GrnDocumentCancelledReasonOKButton, ElementType.Xpath);
					logs.logging("INFO", "Successfully Document Wise Cancel OK Button Clicked");
				}
			}

		}
		assertTrue(flag);
	}

	/**
	 * This Method To Verify To Document wise Cancelled
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToCheckDocumentwiseStatus() {
		if (flag) {
			flag = webDB.waitForElement(GrnLocators.FindPageGrnNoLov, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.FindPageGrnNoLov, ElementType.Xpath);
				flag = webDB.waitForElement(GrnLocators.GrnNumberInput, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(GrnLocators.GrnNumberInput,
							VariableHandler.getGrnDocNumber());
					logs.logging("INFO", "Document Number Search And Selected Successfully");
					flag = webDB.waitForElement(GrnLocators.GrnHeaderRadioBtn, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(GrnLocators.GrnHeaderRadioBtn, ElementType.Xpath);
						logs.logging("INFO", "Header Button Clicked");
						flag = webDB.waitForElement(GrnLocators.GrnSearchButton, ElementType.Xpath);
						flag = webDB.clickAnElement(GrnLocators.GrnSearchButton, ElementType.Xpath);
						logs.logging("INFO", "Document Searched SuccessFully");
						flag = webDB.waitForElement(GrnLocators.GrnDocumentStatus, ElementType.Xpath);
						if (flag) {
							String GRNSTATUS = webDB.getTextFromElement(GrnLocators.GrnDocumentStatus,
									ElementType.Xpath);
							VariableHandler.setGrnStatus(GRNSTATUS);
							logs.logging("INFO", "GRN ProcesWise STATUS:" + VariableHandler.getGrnStatus());
						}
					}
				}
			}
			assertTrue(flag);
		}

	}

	/**
	 * This Methods Method to handle Validate the PO Location and GRN Document
	 * Location
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void validateGrnDocumentwiseStatus(String DocumentWiseStatus) {
		flag = webDB.waitForElement(GrnLocators.GrnDocumentStatus, ElementType.Xpath);
		if (flag) {
			String DisplayText = webDB.getTextFromElement(GrnLocators.GrnDocumentStatus, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Expected Text: " + DocumentWiseStatus + " Displayed: " + DisplayText);
			}
			flag = DisplayText.equalsIgnoreCase(DocumentWiseStatus);
			if (flag) {
				logs.logging("INFO", "verified LineWise Status Matched: " + DisplayText);
			} else {
				logs.logging("INFO", "verified LineWise Status MissMatched");
				logs.logging("INFO", "Expected Text: " + DocumentWiseStatus + " Displayed: " + DisplayText);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method to handle Create the GRN Document For Stock Transfer Type With
	 * Rate Contract Item
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void PoToGrnStockTransferRateContractSameUomForFreePassItem() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("StockTransferPORateContract"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("StockTransferPORateContract"));
		POfunc.verifyToCreatePoForStockTransferRateContractSameStockandSupplierUomWithAndWithoutInspection(
				webDB.getDataFromProperties("StockTransferPORateContract"));
		verifyToStockTransferSaleOrderDocumentPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSalesOrdertDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocument();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		createGrnDocumentforwithandwithoutInspectionStockTransferType(
				webDB.getDataFromProperties("StockTransferPORateContract"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("StockTransferPORateContract"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransferType();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("StockTransferPORateContract"));
		assertTrue(flag);
	}

	/**
	 * This Method to handle Create To verify Application Control
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void vrifyApplcontrl() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSetupInventoryPage();
		boolean createflag = mrvFunc.autoCreateMRVCheckboxIsSelected();
		boolean postflag = mrvFunc.autoPostMRVCheckboxIsSelected();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToMaterialReceiptVoucher();

		if (createflag && !postflag) {
			System.out.println("CheckCreateflagAndNotCheckPostFlag");
			ArrayList<String> MrvNumberarray = dbCommonFunctions.MrvDocDetails(VariableHandler.getGrnDocNumber(),
					commonFunc.getDataFromTestDataSheet("MRVNo"));
			System.out.println("MRVNo");
			for (String MRVNo : MrvNumberarray) {
				mrvFunc.verifyOpenAlreadyCreatedMRVDocumentsWithGRNSCREEN(MRVNo);
				System.out.println(MRVNo);
			}
		} else if (!createflag && !postflag) {
			System.out.println("NotCheckCreateflagAndNotCheckPostFlag");
			mrvFunc.verifyPostMaterialReceiptWithAndWithoutLocatorBasedOnApplicationControl();
		} else if (createflag && postflag) {
			getMaterialReceiptVoucherDocumentNumberFromDB();

		} else if (!createflag && postflag) {
			getMaterialReceiptVoucherDocumentNumberFromDB();

		}

	}

	/**
	 * This Methods Method to handle Create GRN From PO With General With And
	 * Without Inspection Document(Multiple Item)
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost()
			throws IOException {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		DirectGrnFunc.verifyGrnAddLineDetails(webDB.getDataFromProperties("ToleranceQty"));
		// DirectGrnFunc.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ToleranceQty"));
		// lotSerialQtyApportion();
		verifyToAddChargeItem();
		clickChargeButtonClosed();
		verifyToClickLandedCostField();
		clickChargeButtonClosed();
		clickIncludeCharge();
		overAllSaveAndPostForExcess(webDB.getDataFromProperties("ToleranceQty"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyGrnAdd() {
		flag = webDB.waitForElement(GrnLocators.GrnAddBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.GrnAddBtn, ElementType.Xpath);
			flag = webDB.waitForElement(DirectGrnPageLocators.AddHeaderFrame, ElementType.Xpath);
			logs.logging("INFO", "Add Button is Clicked");
		}
		assertTrue(flag);

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyGrnAddDetails(String Location, String Type, String Supplier, String BillNo) {
		String SupBillNo = RandomStringUtils.randomNumeric(6);
		if (SupBillNo.equals("000")) {
			SupBillNo = "10";
		}
		String DCNO = BillNo + SupBillNo;
		VerifyToClickGrnFrame();
		flag = webDB.waitForElement(DirectGrnPageLocators.GrnLocLov, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(DirectGrnPageLocators.GrnLocLov, Location);
			logs.logging("INFO", "Location Is Entered:" + Location);
			VerifyToClickGrnFrame();
			flag = webDB.waitForElement(DirectGrnPageLocators.GrnType, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByValue(DirectGrnPageLocators.GrnType, Type, ElementType.Xpath);
				logs.logging("INFO", "Type Is Entered:" + Type);
				flag = webDB.waitForElement(DirectGrnPageLocators.GrnSupplierLov, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(DirectGrnPageLocators.GrnSupplierLov, Supplier);
					logs.logging("INFO", "Supplier Is Entered:" + Supplier);
					VerifyToClickGrnFrame();
					flag = webDB.waitForElement(DirectGrnPageLocators.GrnSupplierBillNo, ElementType.Xpath);
					if (flag) {
						webDB.sendTextToAnElement(DirectGrnPageLocators.GrnSupplierBillNo, DCNO, ElementType.Xpath);
						logs.logging("INFO", "Supplier Bill NO Is Entered");
						flag = webDB.waitForElement(DirectGrnPageLocators.GrnDcNo, ElementType.Xpath);
						if (flag) {
							webDB.sendTextToAnElement(DirectGrnPageLocators.GrnDcNo, DCNO, ElementType.Xpath);
							logs.logging("INFO", "DC NO Is Entered");
							flag = webDB.waitForElement(DirectGrnPageLocators.GrnCreate, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(DirectGrnPageLocators.GrnCreate, ElementType.Xpath);
								logs.logging("INFO", "GRN Is Created");
								webDB.switchToDefaultContent();
								flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	private void VerifyToClickGrnFrame() {
		flag = webDB.waitForElement(DirectGrnPageLocators.AddHeaderFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(DirectGrnPageLocators.AddHeaderFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Frame");

		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyToAddChargeItem() {
		flag = webDB.waitForElement(GrnLocators.GrnChargesBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.GrnChargesBtn, ElementType.Xpath);
			logs.logging("INFO", "Charges Button Clicked");
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(GrnLocators.GrnChargesIFrame, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(GrnLocators.GrnChargesIFrame, ElementType.Xpath);
				logs.logging("INFO", "Switch To Charges Frame");
				String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
				ExcelUtils excelUtils = new ExcelUtils();
				String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
						+ "ItemDetails.xlsx";
				itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("ChargeItem"));
				for (int i = 1; i < itemmultiple.length; i++) {
					String ChargesBasis = itemmultiple[i][0];
					String ItemName = itemmultiple[i][1];
					String Amount = itemmultiple[i][2];
					String GLAccount = itemmultiple[i][3];
					if (!ChargesBasis.equals("")) {
						commonFunc.hardWait(2000);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(GrnLocators.GrnChargesAddbtn, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(GrnLocators.GrnChargesAddbtn, ElementType.Xpath);
							logs.logging("INFO", "Charges Add Button Clicked succesfully");

							if (ChargesBasis.equals(commonFunc.getDataFromTestDataSheet("Percentage"))) {
								percentageBasedItem(ItemName, Amount, GLAccount);
							} else {
								valueBasedItem(ItemName, Amount, GLAccount);
							}

						}
					}
				}

			}
			assertTrue(flag);
		}
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void clickChargeButtonClosed() {
		webDB.pressEscapeKey();
		commonFunc.checkRefreshCellsDisappear();
		webDB.switchToDefaultContent();
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(GrnLocators.GrnIncludecharge, ElementType.Xpath);
		if (flag) {

		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void percentageBasedItem(String ItemName, String Amount, String GLAccount) {
		commonFunc.hardWait(2000);
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(GrnLocators.GrnChargesBasis, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.GrnChargesBasis, ElementType.Xpath);
			webDB.selectDropDownByValue(GrnLocators.GrnChargesSelect, commonFunc.getDataFromTestDataSheet("Percentage"),
					ElementType.Xpath);
			logs.logging("INFO", "Percentage Based Select Successfully");
			flag = webDB.waitForElement(GrnLocators.GrnCharges, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.GrnCharges, ElementType.Xpath);
				logs.logging("INFO", "Moved To Charges Item Field");
				flag = webDB.waitForElement(GrnLocators.GrnChargesItemInput, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(GrnLocators.GrnChargesItemInput, ElementType.Xpath);
					flag = webDB.waitForElement(GrnLocators.GrnItemChargesLov, ElementType.Xpath);
					if (flag) {
						commonFunc.searchAndSelectValueInFilters(GrnLocators.GrnItemChargesLov, ItemName);
						webDB.switchToFrame(GrnLocators.GrnChargesIFrame, ElementType.Xpath);
						logs.logging("INFO", "Again Switch To Chages Frame");
						commonFunc.hardWait(2000);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(GrnLocators.GrnPerncentageField, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(GrnLocators.GrnPerncentageField, ElementType.Xpath);
							flag = webDB.waitForElement(GrnLocators.GrnPerncentageFieldInput, ElementType.Xpath);
							flag = webDB.clearText(GrnLocators.GrnPerncentageFieldInput, ElementType.Xpath);
							if (flag) {
								webDB.sendTextToAnElement(GrnLocators.GrnPerncentageFieldInput, Amount,
										ElementType.Xpath);
								flag = webDB.waitForElement(GrnLocators.GrnChargesGLAccount, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(GrnLocators.GrnChargesGLAccount, ElementType.Xpath);
									logs.logging("INFO", "Again Switched To Chages Frame");
									commonFunc.hardWait(2000);
									commonFunc.checkRefreshCellsDisappear();
									if (flag) {
										commonFunc.searchAndSelectValueInFilters(GrnLocators.GrnChargesGLAccountLov,
												GLAccount);
										logs.logging("INFO", "Sucessfully GL Account Selected");
										webDB.switchToFrame(GrnLocators.GrnChargesIFrame, ElementType.Xpath);
										flag = webDB.waitForElement(GrnLocators.GrnChargesSaveBtn, ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(GrnLocators.GrnChargesSaveBtn, ElementType.Xpath);
											logs.logging("INFO", "Sucessfully Charge Item saved");
											commonFunc.hardWait(2000);
											commonFunc.checkRefreshCellsDisappear();
											flag = webDB.waitForElement(GrnLocators.ToastAlertClosed,
													ElementType.Xpath);
											if (flag) {
												webDB.clickAnElement(GrnLocators.ToastAlertClosed, ElementType.Xpath);

												logs.logging("INFO", " Percentage Toast Alert Closed");

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
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToClickLandedCostField() {
		verifyToclickLandedCostBtn();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostField"));
		for (int i = 1; i < itemmultiple.length; i++) {
			String LandedCostItem = itemmultiple[i][0];
			String LandedCostSupplier = itemmultiple[i][1];
			String LandedCostAmount = itemmultiple[i][2];
			String LandedCostDocumentNumber = itemmultiple[i][3];
			String AICSelection = itemmultiple[i][4];
			if (!LandedCostItem.equals("")) {
				verifyToCreatelandedCostAddBtn();
				verifyToSelectItem(LandedCostItem);
				verifyToSelectSupplier(LandedCostSupplier);
				verifyToenteredLandedcostAmount(LandedCostAmount);
				verfytoEnteredLandedCostDocumentNumber(LandedCostDocumentNumber);
				verifyToEnteredLandedCostDateField();
				verifyToSelectAvailOrNotAvailField(AICSelection);
				verifyToSavetheLineDetails();
			}
		}
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToclickLandedCostBtn() {
		flag = webDB.waitForElement(GrnLocators.GrnLandedCostBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.GrnLandedCostBtn, ElementType.Xpath);
			flag = webDB.waitForElement(GrnLocators.GrnLandedCostFrame, ElementType.Xpath);
			logs.logging("INFO", "landed Cost Button Clicked SuccessFully");
			flag = webDB.waitForElement(GrnLocators.GrnLandedCostFrame, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(GrnLocators.GrnLandedCostFrame, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToCreatelandedCostAddBtn() {
		flag = webDB.waitForElement(GrnLocators.GrnLandedCostItemAddbtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.GrnLandedCostItemAddbtn, ElementType.Xpath);
			flag = webDB.waitForElement(GrnLocators.GrnLandedCostItemLov, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "landed Cost AddButton Clicked SuccessFully");
				commonFunc.hardWait(2000);
			}
		}

		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToSelectItem(String LandedCostItem) {

		flag = webDB.waitForElement(GrnLocators.GrnLandedCostItemLov, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(GrnLocators.GrnLandedCostItemLov, LandedCostItem);
			logs.logging("INFO", "landed Cost Item saved");
			flag = webDB.waitForElement(GrnLocators.GrnLandedCostFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "landed Cost Frame Enabled");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void clickIncludeCharge() {
		commonFunc.hardWait(2000);
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(GrnLocators.GrnIncludecharge, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.GrnIncludecharge, ElementType.Xpath);
			logs.logging("INFO", "include Charge Button Clicked Successfully");
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void valueBasedItem(String ItemName, String Amount, String GLAccount) {
		commonFunc.hardWait(2000);
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(GrnLocators.GrnChargesBasis, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.GrnChargesBasis, ElementType.Xpath);
			webDB.selectDropDownByValue(GrnLocators.GrnChargesSelect, commonFunc.getDataFromTestDataSheet("Value"),
					ElementType.Xpath);
			logs.logging("INFO", "Value Based Select Successfully");
			flag = webDB.waitForElement(GrnLocators.GrnCharges, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.GrnCharges, ElementType.Xpath);
				logs.logging("INFO", "Moved To Charges Item Field");
				flag = webDB.waitForElement(GrnLocators.GrnChargesItemInput, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(GrnLocators.GrnChargesItemInput, ElementType.Xpath);
					flag = webDB.waitForElement(GrnLocators.GrnItemChargesLov, ElementType.Xpath);
					if (flag) {
						commonFunc.searchAndSelectValueInFilters(GrnLocators.GrnItemChargesLov, ItemName);
						webDB.switchToFrame(GrnLocators.GrnChargesIFrame, ElementType.Xpath);
						logs.logging("INFO", "Again Switch To Chages Frame");
						commonFunc.hardWait(2000);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(GrnLocators.GrnChargesAmountValue, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(GrnLocators.GrnChargesAmountValue, ElementType.Xpath);

							flag = webDB.waitForElement(GrnLocators.GrnChargesAmountValueInput, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(GrnLocators.GrnChargesAmountValueInput, ElementType.Xpath);
								logs.logging("INFO", "Again Switch To Chages Frame");

								flag = webDB.waitForElement(GrnLocators.GrnChargesAmountValueInput, ElementType.Xpath);
								flag = webDB.clearText(GrnLocators.GrnChargesAmountValueInput, ElementType.Xpath);
								if (flag) {
									webDB.sendTextToAnElement(GrnLocators.GrnChargesAmountValueInput, Amount,
											ElementType.Xpath);
									logs.logging("INFO", "SuccessFully Entered");
									commonFunc.hardWait(2000);
									commonFunc.checkRefreshCellsDisappear();
									flag = webDB.waitForElement(GrnLocators.GrnChargesGLAccount, ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(GrnLocators.GrnChargesGLAccount, ElementType.Xpath);
										logs.logging("INFO", "Again Switched To Chages Frame");

										if (flag) {
											commonFunc.searchAndSelectValueInFilters(GrnLocators.GrnChargesGLAccountLov,
													GLAccount);
											commonFunc.hardWait(2000);
											commonFunc.checkRefreshCellsDisappear();
											logs.logging("INFO", "Sucessfully GL Account Selected");
											webDB.switchToFrame(GrnLocators.GrnChargesIFrame, ElementType.Xpath);
											flag = webDB.waitForElement(GrnLocators.GrnChargesSaveBtn,
													ElementType.Xpath);
											if (flag) {
												webDB.clickAnElement(GrnLocators.GrnChargesSaveBtn, ElementType.Xpath);
												logs.logging("INFO", "Sucessfully Charge Item saved");
												commonFunc.hardWait(2000);
												commonFunc.checkRefreshCellsDisappear();
												flag = webDB.waitForElement(GrnLocators.ToastAlertClosed,
														ElementType.Xpath);
												if (flag) {
													webDB.clickAnElement(GrnLocators.ToastAlertClosed,
															ElementType.Xpath);
													logs.logging("INFO", "Value Toast Alert Closed SuccessFullly");

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

		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToSelectSupplier(String LandedCostSupplier) {
		flag = webDB.waitForElement(GrnLocators.GrnLandedCostFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(GrnLocators.GrnLandedCostFrame, ElementType.Xpath);
			flag = webDB.waitForElement(GrnLocators.GrnLandedCostSuppliergrid, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.GrnLandedCostSuppliergrid, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(GrnLocators.GrnLandedCostSupplierLov, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(GrnLocators.GrnLandedCostSupplierLov, LandedCostSupplier);
					logs.logging("INFO", "landed Cost Supplier SuccessFully saved");
					flag = webDB.waitForElement(GrnLocators.GrnLandedCostFrame, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "landed Cost Frame Enabled");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToenteredLandedcostAmount(String LandedCostAmount) {
		flag = webDB.waitForElement(GrnLocators.GrnLandedCostFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(GrnLocators.GrnLandedCostFrame, ElementType.Xpath);
			flag = webDB.waitForElement(GrnLocators.GrnLandedCostAmountGrid, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.GrnLandedCostAmountGrid, ElementType.Xpath);

				flag = webDB.waitForElement(GrnLocators.GrnLandedCostAmountValue, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(GrnLocators.GrnLandedCostAmountValue, ElementType.Xpath);
					webDB.clearText(GrnLocators.GrnLandedCostAmountValue, ElementType.Xpath);
					webDB.sendTextToAnElement(GrnLocators.GrnLandedCostAmountValue, LandedCostAmount,
							ElementType.Xpath);
					flag = webDB.waitForElement(GrnLocators.GrnLandedCostDocumentGrid, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "landed Cost Amount SuccessFully saved");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verfytoEnteredLandedCostDocumentNumber(String LandedCostDocumentNumber) {
		flag = webDB.waitForElement(GrnLocators.GrnLandedCostDocumentGrid, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.GrnLandedCostDocumentGrid, ElementType.Xpath);
			flag = webDB.waitForElement(GrnLocators.GrnLandedCostDocumentNumberField, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.GrnLandedCostDocumentNumberField, ElementType.Xpath);
				webDB.clearText(GrnLocators.GrnLandedCostDocumentNumberField, ElementType.Xpath);
				webDB.sendTextToAnElement(GrnLocators.GrnLandedCostDocumentNumberField, LandedCostDocumentNumber,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(GrnLocators.GrnLandedCostDateGrid, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "landed Cost Document SuccessFully saved");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToEnteredLandedCostDateField() {
		flag = webDB.waitForElement(GrnLocators.GrnLandedCostDateGrid, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.GrnLandedCostDateGrid, ElementType.Xpath);
			webDB.switchToDefaultContent();
			if (flag) {
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				webDB.clickAnElement(GrnLocators.GrnLandedCostTodayDate, ElementType.Xpath);
				flag = webDB.waitForElement(GrnLocators.GrnLandedCostFrame, ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(GrnLocators.GrnLandedCostFrame, ElementType.Xpath);
					logs.logging("INFO", "landed Cost Date SuccessFully saved");

				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToSelectAvailOrNotAvailField(String AICSelection) {
		commonFunc.hardWait(2000);
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(GrnLocators.GrnLandedCostAIcGrid, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.GrnLandedCostAIcGrid, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.GrnLandedCostAIcGrid, ElementType.Xpath);
				flag = webDB.waitForElement(GrnLocators.GrnLandedCostSelectAic, ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(2000);
					commonFunc.checkRefreshCellsDisappear();
					webDB.selectDropDownByValue(GrnLocators.GrnLandedCostSelectAic, AICSelection, ElementType.Xpath);
					flag = webDB.waitForElement(GrnLocators.GrnLandedCostAIcGrid, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "landed Cost Avail SuccessFully Choosed");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToSavetheLineDetails() {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(4000);
		flag = webDB.waitForElement(GrnLocators.GrnLandedCostAIcGrid, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.GrnLandedCostAIcGrid, ElementType.Xpath);
			flag = webDB.waitForElement(GrnLocators.GrnLandedCostLineLevelSave, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.GrnLandedCostLineLevelSave, ElementType.Xpath);
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(GrnLocators.GrnLandedCostItemAddbtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "landed Cost Avail SuccessFully Saved");

				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * This Method Is Used To Click Pending GE And Search GE NO
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void clickPendingGEAndSearchGENo() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(GrnLocators.ClickPendingButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(GrnLocators.ClickPendingButton, ElementType.Xpath);
			webDB.clickAnElement(GrnLocators.ClickPendingButton, ElementType.Xpath);
			logs.logging("INFO", "Pending Button Clicked");
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(GrnLocators.PendingGE, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.PendingGE, ElementType.Xpath);
				logs.logging("INFO", "Pending PO Button Clicked");
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(GrnLocators.PendingPOIframe, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(GrnLocators.FindGELovButton,
							VariableHandler.getGateEntryNumber());
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
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection
	 * Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWOIpSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWOIpSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), webDB.getDataFromProperties("PoToMRVWOIpSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();

//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));

		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWOIpSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWOIpSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWOIpSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("PoToMRVWOIpSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods Is Used After create the GRN to Give Supplier Bill Number And DC
	 * Number
	 * 
	 * @author Deepan-GWL
	 */
	public void clickOkButton() {
		flag = webDB.waitForElement(GrnLocators.OkButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.OkButton, ElementType.Xpath);
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Add Lot and Serial Number for Multiple Item
	 * 
	 * @author Deepan-GWL
	 */
	public void multipleLotAndSerialItemWithPoToMrvSheet(String SheetName, String FileName) {
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
						+ FileName;
				itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
				for (int i = 3; i < itemmultiple.length; i++) {
					String ITEM_NAME = itemmultiple[i][1];
					String LOT_SERIAL = itemmultiple[i][54];
					String LOT_NUMBER = itemmultiple[i][55];
					String SERIAL_NUMBER = itemmultiple[i][56];
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
	 * This Methods Is Used To verify GrnDocument Compare to PO document Details
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyGrnDocumentCompareToPoDocumentWithDesiredSheet(String SheetName, String FileName) {
		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < PRMultipleline.length; i++) {
			String ItemId = PRMultipleline[i][1];
			if (!ItemId.equals("")) {
				System.out.println("tradevalueup");
				verifyAssertionBothValues(
						dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("TradeAssessableValue")),
						dbCommonFunctions.StockQuery3(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNTradeAssessableValue")));
				logs.logging("INFO", "Trade Assessable Value Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("SpecialAssessableValue")),
						dbCommonFunctions.StockQuery3(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNSpecialAssessableValue")));
				logs.logging("INFO", "Special Assessable Value Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("CashAssessableValue")),
						dbCommonFunctions.StockQuery3(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNCashAssessableValue")));
				logs.logging("INFO", "Cash Assessable Value Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("TradeDiscountAmount")),
						dbCommonFunctions.StockQuery3(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNTradeDiscountAmount")));
				logs.logging("INFO", "Trade Discount Amount Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("SpecialDiscountAmount")),
						dbCommonFunctions.StockQuery3(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNSpecialDiscountAmount")));
				logs.logging("INFO", "Special Discount Amount Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("CashDiscountAmount")),
						dbCommonFunctions.StockQuery3(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNCashDiscountAmount")));
				logs.logging("INFO", "Cash  Discount Amount Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PoIgstAmount")),
						dbCommonFunctions.StockQuery3(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GrnIgstAmount")));
				logs.logging("INFO", "IGST Tax Amount Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PoSgstAmount")),
						dbCommonFunctions.StockQuery3(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GrnSgstAmount")));
				logs.logging("INFO", "SGST  Tax Amount Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("POCgstAmount")),
						dbCommonFunctions.StockQuery3(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GrnCgstAmount")));
				logs.logging("INFO", "CGST  Tax Amount Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PoUtgstAmount")),
						dbCommonFunctions.StockQuery3(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GrnUtgstAmount")));
				logs.logging("INFO", "UTGST  Tax Amount Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PoCessAmount")),
						dbCommonFunctions.StockQuery3(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GrnCessAmount")));
				logs.logging("INFO", "UTGST  Tax Amount Matched For PO and GRN " + ItemId);
			}
		}
	}

	/**
	 * This Methods Is Used After Give all Details to Click overall save and Forward
	 * the Approval User
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void overAllSaveAndPost(String SheetName, String FileName) {
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
			verifyGrnDocumentCompareToPoDocumentWithDesiredSheet(SheetName, FileName);
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(GrnLocators.OverAllSave, ElementType.Xpath);
			if (flag)
				webDB.clickAnElement(GrnLocators.OverAllSave, ElementType.Xpath);
			logs.logging("INFO", "Document Successfully Saved");
			commonFunc.hardWait(2000);
			flag = webDB.waitForClickElement(GrnLocators.PostButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.PostButton, ElementType.Xpath);
				logs.logging("INFO", "Document Successfully posted");
				flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Click Fully selected
	 * 
	 * @author Deepan-GWL
	 */
	public void clickSelectAllGEButton() {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(GrnLocators.PendingPOIframe, ElementType.Xpath);
			List<WebElement> CheckBox = webDB.getDriver()
					.findElements(By.xpath(PurchaseOrderPageLocators.PendingGE_SelectAllCheckBox));
			for (WebElement element : CheckBox) {
				try {
					if (element.isDisplayed()) {
						if (!element.isSelected()) {
							element.click();
							flag = true;
							break;
						} else {
							element.click();
							Thread.sleep(10000);
							clickSelectAllGEButton();

						}
					}
				} catch (Exception e) {
				}
			}
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", " Document Fully Selected And Create GRN Button Shown");
			} else {
				logs.logging("INFO", " Document Not Selected And Create GRN Button Not Shown");
//				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used After select the document to create GRN
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void createGrnWithGeButton() {
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(GrnLocators.PendingPOIframe, ElementType.Xpath);
			flag = webDB.waitForElement(GrnLocators.CreateGeButton, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				webDB.clickAnElement(GrnLocators.CreateGeButton, ElementType.Xpath);
				logs.logging("INFO", "GRN Document Created");
				flag = webDB.waitForElement(GrnLocators.OkButton, ElementType.Xpath);
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
	 * This Methods Is Used To verify Expected stock Qty And Cost For Quality
	 * WareHouse
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyExpectedStockQtAndCostyForQualityWH(String SheetName, String FileName) {
		String[][] ItemDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		ItemDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < ItemDetails.length; i++) {
			String ItemId = ItemDetails[i][1];
			String WareHouse = ItemDetails[i][44];
			String AfterExcelStockCost = ItemDetails[i][51];
			if (!ItemId.equals("")) {
				String AfterStockCost = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO", "After GRN Actual Stock Cost For This Item " + ItemId + "Is : " + AfterStockCost);

				verifyAssertionBothValues(AfterStockCost, AfterExcelStockCost);
				logs.logging("INFO",
						"Verified Expected Stock Cost For This Item " + ItemId + " With Database Values After MRV");
			}
		}
	}

	/**
	 * This Methods Used To Verify stock Quantity For Quality Warehouse
	 * Document(Single Item)
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyStockQtyAndCostForQualityWh(String SheetName, String FileName) {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Before Stock Qty For This Item ");
		for (int i = 3; i < ItemMultiple.length; i++) {
			String ItemId = ItemMultiple[i][1];
			String WareHouse = ItemMultiple[i][44];
			String ExpectedStockQuantity = ItemMultiple[i][50];
			String ExpectedStockCost = ItemMultiple[i][51];
			if (!ItemId.equals("")) {
				String BeforeStockQty = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO",
						"Before InspectionWH Stock Qty For This Item " + ItemId + "Is : " + BeforeStockQty);
				logs.logging("INFO",
						"Expected InspectionWH Stock Qty For This Item " + ItemId + "Is : " + ExpectedStockQuantity);
				String BeforeStockCost = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO",
						"Before InspectionWH Stock Cost For This Item " + ItemId + "Is : " + BeforeStockCost);
				logs.logging("INFO",
						"Expected InspectionWH Stock Cost For This Item " + ItemId + "Is : " + ExpectedStockCost);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 46, BeforeStockQty);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 47, BeforeStockCost);
			}
		}

	}

	/**
	 * This Methods Used To Verify stock Quantity For Source Warehouse
	 * Document(Single Item)
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyStockQtyAndCostForSourceWH(String SheetName, String FileName) {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Before Stock Qty For This Item ");
		for (int i = 3; i < ItemMultiple.length; i++) {
			String ItemId = ItemMultiple[i][1];
			String SourecWareHouse = ItemMultiple[i][63];
			String ExpectedStockQuantity = ItemMultiple[i][67];
			String ExpectedStockCost = ItemMultiple[i][66];
			if (!ItemId.equals("")) {
				String BeforeStockQty = dbCommonFunctions.StockQuery1(SourecWareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO",
						"Before Source WareHouse Stock Qty For This Item " + ItemId + "Is : " + BeforeStockQty);
				logs.logging("INFO", "Expected Source WareHouse Stock Qty For This Item " + ItemId + "Is : "
						+ ExpectedStockQuantity);
				String BeforeStockCost = dbCommonFunctions.StockQuery1(SourecWareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO",
						"Before Source WareHouse Stock Cost For This Item " + ItemId + "Is : " + BeforeStockCost);
				logs.logging("INFO",
						"Expected Source WareHouse Stock Cost For This Item " + ItemId + "Is : " + ExpectedStockCost);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 65, BeforeStockQty);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 64, BeforeStockCost);
			}
		}

	}

	/**
	 * This Methods Is Used To verify Expected stock Qty And Cost For Source
	 * WareHouse
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(String SheetName, String FileName) {
		String[][] ItemDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		ItemDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < ItemDetails.length; i++) {
			String ItemId = ItemDetails[i][1];
			String SourecWareHouse = ItemDetails[i][63];
			String AfterExcelStockQuantity = ItemDetails[i][67];
			String AfterExcelStockCost = ItemDetails[i][66];
			if (!ItemId.equals("")) {

				String AfterStockQty = dbCommonFunctions.StockQuery1(SourecWareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO", "After MRV Source WareHouse Actual Stock Qty For This Item " + ItemId + "Is : "
						+ AfterStockQty);

				verifyAssertionBothValues(AfterStockQty, AfterExcelStockQuantity);
				logs.logging("INFO", "Verified Expected Source WareHouse Stock Qty For This Item " + ItemId
						+ " With Database Values After MRV");
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				String AfterStockCost = dbCommonFunctions.StockQuery1(SourecWareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO", "After MRV Source WareHouse Actual Stock Qty For This Item " + ItemId + "Is : "
						+ AfterStockCost);

				verifyAssertionBothValues(AfterStockCost, AfterExcelStockCost);
				logs.logging("INFO", "Verified Expected Source WareHouse Stock Cost For This Item " + ItemId
						+ " With Database Values After MRV");
			}
		}
	}

	/**
	 * This Methods PO to GRN-Material-User Defined-Same Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnMaterialUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWOIpSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWOIpSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"), webDB.getDataFromProperties("PoToMRVWOIpSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();
//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));
		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWOIpSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWOIpSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWOIpSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("PoToMRVWOIpSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Method to handle After Approve the Status Document Move to QC or MRV
	 * Screen
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc() {
		String GrnHeaderWiseStatus = dbCommonFunctions.GrnHeaderDetails(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("ProcessFlowStatus"));
		logs.logging("INFO", "GRN Document ProcessFlow Status Is " + GrnHeaderWiseStatus);
		if (GrnHeaderWiseStatus.equals(commonFunc.getDataFromTestDataSheet("QualityEnabled"))) {
			qcFunc.InspectionDocumentWithInspectionPlanWithEDPManagerForDifferentLoc();
		}
		verifyTocreateMrvDocumentBasedOnAplicationControl();
		assertTrue(flag);
	}

	/**
	 * This Methods PO to GRN-Service-User Defined-Same Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnServiceUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry() {
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"), webDB.getDataFromProperties("PoToMRVWOIpSerSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();
//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWOIpSerSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnGeneralRateContractSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWOIpRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWOIpRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForRateContractItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), webDB.getDataFromProperties("PoToMRVWOIpRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();

//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));

		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWOIpRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWOIpRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWOIpRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("PoToMRVWOIpRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM -
	 * With Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnMaterialRateContractSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWOIpRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWOIpRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForRateContractItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"), webDB.getDataFromProperties("PoToMRVWOIpRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();
		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));
		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWOIpRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWOIpRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWOIpRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("PoToMRVWOIpRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-Service-Rate Contract-Same Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnServiceRateContractSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry() {
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForRateContractItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"),
				webDB.getDataFromProperties("PoToMRVWOIpRcServSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWOIpRcServSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods PO to GRN-General-Price List-Same Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnGeneralPriceListSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWOIpPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWOIpPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForPriceListItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), webDB.getDataFromProperties("PoToMRVWOIpPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();
		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWOIpPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWOIpPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWOIpPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("PoToMRVWOIpPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-Material-Price List-Same Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnMaterialPriceListSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWOIpPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWOIpPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForPriceListItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"), webDB.getDataFromProperties("PoToMRVWOIpPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();
		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWOIpPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWOIpPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWOIpPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("PoToMRVWOIpPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-Service-Price List-Same Stock and Supplier UOM - WO
	 * Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnServicePriceListSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry() {
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForPriceListItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"),
				webDB.getDataFromProperties("PoToMRVWOIpPlServSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWOIpPlServSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used for Approve the Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void waitingForApproval(String FrwdPerson) {
		commonFunc.hardWait(500);
		commonFunc.checkRefreshCellsDisappear();
		if (webDB.verifyIsElementDisplayed(PurchaseOrderPageLocators.DocAprovalIfrmae, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ForwardApproval, FrwdPerson);
			commonFunc.hardWait(500);
			commonFunc.checkRefreshCellsDisappear();
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(PurchaseOrderPageLocators.OkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseOrderPageLocators.OkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(500);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.logoutUser();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods PO to GRN-General-User Defined-Different Stock and Supplier UOM
	 * - Without Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnGeneralUserDefinedDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWOIpDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWOIpDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToMRVWOIpDUomSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();

//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));

		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWOIpDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWOIpDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWOIpDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToMRVWOIpDUomSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-Material-User Defined-Different Stock and Supplier UOM
	 * - Without Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnMaterialUserDefinedDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWOIpDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWOIpDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("PoToMRVWOIpDUomSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();

//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));

		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWOIpDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWOIpDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWOIpDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToMRVWOIpDUomSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-General-Rate Contract-Different Stock and Supplier UOM
	 * - WO Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnGeneralRateContractDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWOIpDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWOIpDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForRateContractItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToMRVWOIpDUomRcSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();

//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));

		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWOIpDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWOIpDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWOIpDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToMRVWOIpDUomRcSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-Material-Rate Contract-Different Stock and Supplier
	 * UOM - WO Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnMaterialRateContractDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWOIpDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWOIpDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForRateContractItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("PoToMRVWOIpDUomRcSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();

//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));

		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWOIpDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWOIpDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWOIpDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToMRVWOIpDUomRcSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-General-User Defined-Different Stock and Supplier UOM
	 * - With Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnGeneralPriceListDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWOIpDUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWOIpDUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForPriceListItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToMRVWOIpDUomPlSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();

//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));

		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWOIpDUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWOIpDUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWOIpDUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToMRVWOIpDUomPlSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-General-Price List-Different Stock and Supplier UOM -
	 * WO Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnMaterialPriceListDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWOIpDUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWOIpDUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForPriceListItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("PoToMRVWOIpDUomPlSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();
//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));
		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWOIpDUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWOIpDUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWOIpDUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToMRVWOIpDUomPlSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier
	 * UOM - WO Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("StPoToMRVWOIpSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("StPoToMRVWOIpSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L0007"), commonFunc.getDataFromTestDataSheet("MagaPvtLtd"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"),
				webDB.getDataFromProperties("StPoToMRVWOIpSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		verifyToStockTransferSaleOrderDocumentPost();
		saleOrderFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		flag = commonFunc.logoutUser();
		stockTransferAfterForwardToApproval();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocumentWithPrefix();
		stockTransferAfterForwardToApprovalForInvoiceDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingSTNumber();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButtonWithSt();
		createGrnWithGeButton();
		clickOkButton();
//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));
		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("StPoToMRVWOIpSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("StPoToMRVWOIpSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("StPoToMRVWOIpSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("StPoToMRVWOIpSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Method to Handle PO To GRN Stock Transfer Price List Same Stock and
	 * Supplier UOM With And WO Inspection
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void poToGrnStockTransferRateContractDifferentUOMForFreePassItem() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("StockTransferRCFreePass"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("StockTransferRCFreePass"));
		POfunc.verifyToCreatePoForStockTransferRateContractSameStockandSupplierUomWithAndWithoutInspection(
				webDB.getDataFromProperties("StockTransferRCFreePass"));
		verifyToStockTransferSaleOrderDocumentPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSalesOrdertDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocument();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		createGrnDocumentforwithandwithoutInspectionStockTransferType(
				webDB.getDataFromProperties("StockTransferRCFreePass"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("StockTransferRCFreePass"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransferType();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("StockTransferRCFreePass"));
		assertTrue(flag);
	}

	/**
	 * This Method to Handle PO To GRN Stock Transfer Price List Same Stock and
	 * Supplier UOM With And WO Inspection
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void PoToGRNStockTransferUserDefinedDifferentStockAndSupplierUOMForFreePass() throws Exception {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("StockTransferUDFreePass"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("StockTransferUDFreePass"));
		POfunc.verifyToCreatePoForStockTransferUserDefinedSameStockandSupplierUomWithAndWithoutInspection(
				webDB.getDataFromProperties("StockTransferUDFreePass"));
		verifyToStockTransferSaleOrderDocumentPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSalesOrdertDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocument();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSIdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		createGrnDocumentforwithandwithoutInspectionStockTransferType(
				webDB.getDataFromProperties("StockTransferUDFreePass"));
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("StockTransferUDFreePass"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransferType();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("StockTransferUDFreePass"));
		assertTrue(flag);
	}

	/**
	 * This Methods PO to GRN-General-User Defined-Same Stock and Supplier UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnGeneralUserDefinedSameStockAndSupplierUomWithFreePassWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWFSUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWFSUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), webDB.getDataFromProperties("PoToMRVWFSUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();

//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));

		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWFSUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWFSUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWFSUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("PoToMRVWFSUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-Material-User Defined-Same Stock and Supplier UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnMaterialUserDefinedSameStockAndSupplierUomWithFreePassWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWFSUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWFSUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"), webDB.getDataFromProperties("PoToMRVWFSUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();

//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));

		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWFSUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWFSUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWFSUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("PoToMRVWFSUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-Service-User Defined-Same Stock and Supplier UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnServiceUserDefinedSameStockAndSupplierUomWithFreePassWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWFSUomservSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWFSUomservSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"),
				webDB.getDataFromProperties("PoToMRVWFSUomservSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();
//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWFSUomservSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods PO to GRN-General-Rate Contract-Same Stock and Supplier UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnGeneralRateContractSameStockAndSupplierUomWithFreePassWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWFSUomRCSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWFSUomRCSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForRateContractItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToMRVWFSUomRCSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();

//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));

		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWFSUomRCSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWFSUomRCSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWFSUomRCSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToMRVWFSUomRCSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This MethodsPO to GRN-Material-Rate Contract-Same Stock and Supplier UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnMaterialRateContractSameStockAndSupplierUomWithFreePassWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWFSUomRCSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWFSUomRCSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForRateContractItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("PoToMRVWFSUomRCSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();

//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));

		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWFSUomRCSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWFSUomRCSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWFSUomRCSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToMRVWFSUomRCSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-Service-Rate Contract-Same Stock and Supplier UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnServiceRateContractSameStockAndSupplierUomWithFreePassWithGateEntry() {
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForRateContractItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"),
				webDB.getDataFromProperties("PoToMRVWFSUomservRcSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();
//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWFSUomservRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods PO to GRN-General-Price List-Same Stock and Supplier UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnGeneralPriceListSameStockAndSupplierUomWithFreePassWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWFSUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWFSUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForPriceListItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToMRVWFSUomPlSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();

//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));

		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWFSUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWFSUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWFSUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToMRVWFSUomPlSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-Material-Price List-Same Stock and Supplier UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnMaterialPriceListSameStockAndSupplierUomWithFreePassWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWFSUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWFSUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForPriceListItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("PoToMRVWFSUomPlSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();

//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));

		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWFSUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWFSUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWFSUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToMRVWFSUomPlSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-Service-Price List-Same Stock and Supplier UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnServicePriceListSameStockAndSupplierUomWithFreePassWithGateEntry() {
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForPriceListItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"),
				webDB.getDataFromProperties("PoToMRVWFSUomservPlSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();
//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWFSUomservPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Method to handle Create GRN Document from PO With and Without
	 * Inspection for RateContract Item(General)
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnGeneralRateContractDifferentStockAndSupplierUomWithFreePassWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWOIpDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWOIpDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForRateContractItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToMRVWOIpDUomRcSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();

//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));

		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWOIpDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWOIpDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWOIpDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToMRVWOIpDUomRcSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-General-Rate Contract-Different Stock and Supplier UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnGeneralRateContractDifferentStockAndSupplierWithFreePassWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWFPDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWFPDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForRateContractItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToMRVWFPDUomRcSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();

//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));

		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWFPDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWFPDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWFPDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToMRVWFPDUomRcSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-Material-Rate Contract-Different Stock and Supplier
	 * UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnMaterialRateContractDifferentStockAndSupplierWithFreePassWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWFPDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWFPDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForRateContractItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("PoToMRVWFPDUomRcSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();

//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));

		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWFPDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWFPDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWFPDUomRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToMRVWFPDUomRcSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-General-Price List-Different Stock and Supplier UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnGeneralPriceListDifferentStockAndSupplierUomWithFreePassWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWFPDUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWFPDUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForPriceListItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToMRVWFPDUomPlSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();
//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));
		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWFPDUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWFPDUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWFPDUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToMRVWFPDUomPlSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-Material-Price List-Different Stock and Supplier UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnMaterialPriceListDifferentStockAndSupplierUomWithFreePassWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWFPDUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWFPDUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForPriceListItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("PoToMRVWFPDUomPlSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();
//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));
		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWFPDUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWFPDUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWFPDUomPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToMRVWFPDUomPlSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Method to handle verify To Post Sales Invoice Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToPostSalesInvoiceDocumentWithPrefix() {
		saleInvoiceFunc.verifyAllocLSbatch();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		saleInvoiceFunc.verifyPickButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyInvoicePfxForStockTransferDocument(commonFunc.getDataFromTestDataSheet("SalesInvoicePrefix"));
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
	 * This Methods Is Used To Click Fully selected
	 * 
	 * @author Deepan-GWL
	 */
	public void clickSelectAllGEButtonWithSt() {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(GrnLocators.PendingPOIframe, ElementType.Xpath);
			List<WebElement> CheckBox = webDB.getDriver()
					.findElements(By.xpath(PurchaseOrderPageLocators.GE_SelectAllCheckBox));
			for (WebElement element : CheckBox) {
				try {
					if (element.isDisplayed()) {
						if (!element.isSelected()) {
							element.click();
							flag = true;
						} else {
							element.click();
							Thread.sleep(10000);
							clickSelectAllGEButton();

						}
					}
				} catch (Exception e) {
				}
			}
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", " Document Fully Selected And Create GRN Button Shown");
			} else {
				logs.logging("INFO", " Document Not Selected And Create GRN Button Not Shown");
//				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method to handle After Approve the Status Document Move to QC or MRV
	 * Screen
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	public void afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer() {
		String GrnHeaderWiseStatus = dbCommonFunctions.GrnHeaderDetails(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("ProcessFlowStatus"));
		logs.logging("INFO", "GRN Document ProcessFlow Status Is " + GrnHeaderWiseStatus);
		if (GrnHeaderWiseStatus.equals(commonFunc.getDataFromTestDataSheet("QualityEnabled"))) {
			qcFunc.InspectionDocumentWithInspectionPlanWithEDPManagerForStockTransfer();
		}
		verifyTocreateMrvDocumentBasedOnAplicationControl();
		assertTrue(flag);
	}

	/**
	 * This MethodsPO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier
	 * UOM - With Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnStockTransferRateContractSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("StPoToMRVWOIpRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("StPoToMRVWOIpRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForRateContractItem(
				commonFunc.getDataFromTestDataSheet("L0007"), commonFunc.getDataFromTestDataSheet("MagaPvtLtd"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"),
				webDB.getDataFromProperties("StPoToMRVWOIpRcSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		verifyToStockTransferSaleOrderDocumentPost();
		saleOrderFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		flag = commonFunc.logoutUser();
		stockTransferAfterForwardToApproval();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocumentWithPrefix();
		stockTransferAfterForwardToApprovalForInvoiceDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingSTNumber();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButtonWithSt();
		createGrnWithGeButton();
		clickOkButton();
//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));
		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("StPoToMRVWOIpRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("StPoToMRVWOIpRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("StPoToMRVWOIpRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("StPoToMRVWOIpRcSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM
	 * - WO Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnStockTransferPriceListSameStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("StPoToMRVWOIpPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("StPoToMRVWOIpPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForPriceListItem(
				commonFunc.getDataFromTestDataSheet("L0007"), commonFunc.getDataFromTestDataSheet("MagaPvtLtd"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"),
				webDB.getDataFromProperties("StPoToMRVWOIpPlSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		verifyToStockTransferSaleOrderDocumentPost();
		saleOrderFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		flag = commonFunc.logoutUser();
		stockTransferAfterForwardToApproval();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocumentWithPrefix();
		stockTransferAfterForwardToApprovalForInvoiceDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingSTNumber();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButtonWithSt();
		createGrnWithGeButton();
		clickOkButton();
//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));
		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("StPoToMRVWOIpPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("StPoToMRVWOIpPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("StPoToMRVWOIpPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("StPoToMRVWOIpPlSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-Stock Transfer-User Defined-Different Stock and
	 * Supplier UOM - WO Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnStockTransferUserDefinedDifferentStockAndSupplierUomWithAndWithoutInspectionPlanWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("StPoToMRVWOIpDUSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("StPoToMRVWOIpDUSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L0007"), commonFunc.getDataFromTestDataSheet("MagaPvtLtd"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"),
				webDB.getDataFromProperties("StPoToMRVWOIpDUSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		verifyToStockTransferSaleOrderDocumentPost();
		saleOrderFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		flag = commonFunc.logoutUser();
		stockTransferAfterForwardToApproval();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocumentWithPrefix();
		stockTransferAfterForwardToApprovalForInvoiceDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingSTNumber();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButtonWithSt();
		createGrnWithGeButton();
		clickOkButton();
//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));
		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("StPoToMRVWOIpDUSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("StPoToMRVWOIpDUSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("StPoToMRVWOIpDUSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("StPoToMRVWOIpDUSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-General-User Defined-Different Stock and Supplier UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnGeneralUserDefinedDifferentStockAndSupplierUomWithFreePassWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWFDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWFDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), webDB.getDataFromProperties("PoToMRVWFDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();

//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));

		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWFDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWFDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWFDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("PoToMRVWFDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-Material-User Defined-Different Stock and Supplier UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnMaterialUserDefinedDifferentStockAndSupplierUomWithFreePassWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWFDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWFDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"), webDB.getDataFromProperties("PoToMRVWFDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButton();
		createGrnWithGeButton();
		clickOkButton();

//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));

		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWFDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("PoToMRVWFDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWFDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("PoToMRVWFDUomSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier
	 * UOM - WO Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnStockTransferUserDefinedSameStockAndSupplierUomWithFreePassWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("StPoToMRVWFSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("StPoToMRVWFSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L0007"), commonFunc.getDataFromTestDataSheet("MagaPvtLtd"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"),
				webDB.getDataFromProperties("StPoToMRVWFSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		verifyToStockTransferSaleOrderDocumentPost();
		saleOrderFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		flag = commonFunc.logoutUser();
		stockTransferAfterForwardToApproval();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocumentWithPrefix();
		stockTransferAfterForwardToApprovalForInvoiceDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingSTNumber();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButtonWithSt();
		createGrnWithGeButton();
		clickOkButton();
//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));
		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("StPoToMRVWFSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("StPoToMRVWFSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("StPoToMRVWFSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("StPoToMRVWFSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This MethodsPO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier
	 * UOM - With Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnStockTransferRateContractSameStockAndSupplierUomWithFreePassWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("StPoToMRVWFRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("StPoToMRVWFRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForRateContractItem(
				commonFunc.getDataFromTestDataSheet("L0007"), commonFunc.getDataFromTestDataSheet("MagaPvtLtd"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"),
				webDB.getDataFromProperties("StPoToMRVWFRcSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		verifyToStockTransferSaleOrderDocumentPost();
		saleOrderFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		flag = commonFunc.logoutUser();
		stockTransferAfterForwardToApproval();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocumentWithPrefix();
		stockTransferAfterForwardToApprovalForInvoiceDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingSTNumber();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButtonWithSt();
		createGrnWithGeButton();
		clickOkButton();
//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));
		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("StPoToMRVWFRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("StPoToMRVWFRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("StPoToMRVWFRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("StPoToMRVWFRcSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM
	 * - WO Inspection Plan
	 * 
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	public void poToGrnStockTransferPriceListSameStockAndSupplierUomWithFreePassWithGateEntry() {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("StPoToMRVWFPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("StPoToMRVWFPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForPriceListItem(
				commonFunc.getDataFromTestDataSheet("L0007"), commonFunc.getDataFromTestDataSheet("MagaPvtLtd"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"),
				webDB.getDataFromProperties("StPoToMRVWFPlSheet"), webDB.getDataFromProperties("PoToMrvSheet"));
		verifyToStockTransferSaleOrderDocumentPost();
		saleOrderFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		flag = commonFunc.logoutUser();
		stockTransferAfterForwardToApproval();
		verifyToPostStockTransferDocumentForSalesInvoice();
		selectAllPendingSOAndClickCreateSIForHodUser();
		verifyToPostSalesInvoiceDocumentWithPrefix();
		stockTransferAfterForwardToApprovalForInvoiceDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingSTNumber();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		clickPendingGEAndSearchGENo();
		clickSelectAllGEButtonWithSt();
		createGrnWithGeButton();
		clickOkButton();
//		validateLocationInGrnHeader(commonFunc.getDataFromTestDataSheet("L49"));
		multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("StPoToMRVWFPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		lotSerialQtyApportion();
		overAllSaveAndPost(webDB.getDataFromProperties("StPoToMRVWFPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("StPoToMRVWFPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("StPoToMRVWFPlSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
	}

	/**
	 * This Methods Is Used After Give all Details to Click overall save and Forward
	 * the Approval User
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void clickPostButton() {
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(1000);
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());
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
	 * This Methods Is Used for Approve the Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void waitingForApprovalHK(String Employee) {
		commonFunc.hardWait(1000);
		if (webDB.verifyIsElementDisplayed(GrnLocators.DocAprovalIfrmae, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			String GetTextEmployee = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.ForwardEmployeefield,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			if (!GetTextEmployee.equals(Employee)) {
				commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ForwardApproval, Employee);
				commonFunc.hardWait(500);
				webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
				logs.logging("INFO", "Switched To WorkFlow Frame");
			}
			flag = webDB.waitForElement(PurchaseOrderPageLocators.OkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseOrderPageLocators.OkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				commonFunc.checkRefreshCellsDisappear();
				webDB.switchToDefaultContent();
				flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
				if (flag) {
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(1000);
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
							VariableHandler.getGrnDocNumber(), webDB.getDataFromProperties("HodEmpId"),
							commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
				}

			}
		}
		assertTrue(flag);
	}
}
