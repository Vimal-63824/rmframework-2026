package commonfunctions.supplychainmanagement;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 28, 2025
 */
import static org.testng.Assert.assertTrue;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.supplychainmanagement.MaterialRequestLocators;
import java.io.File;
import java.math.BigDecimal;
import org.testng.Assert;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.ApprovalPageLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class MaterialRequestFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	public PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();
	public ApprovalPageFunctions approvalPageFunc = new ApprovalPageFunctions();

	/**
	 * This method used to store Material Request Number And Validate Status With
	 * Production Order Number
	 * 
	 * @author Sakthi-GWL
	 * @param ProdNo
	 * @param ExpectedStatus
	 */
	public void storeMRNoAndCheckStatusOfMRWithProdNo(String ProdNo, String ExpectedStatus) {
		commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.FindProdNoLovBtn, ProdNo);
		flag = webDB.waitForElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(MaterialRequestLocators.Find_Status_TableData(ProdNo), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button clicked and MRP No: " + ProdNo + " Displayed");
				String Status = webDB.getTextFromElement(MaterialRequestLocators.Find_Status_TableData(ProdNo),
						ElementType.Xpath);
				logs.logging("INFO", "Production Number: '" + ProdNo + "' searched and displaying status: " + Status);
				flag = Status.equalsIgnoreCase(ExpectedStatus);
				if (flag) {
					logs.logging("INFO", "Search Button clicked and MRP No: " + ProdNo + " Displayed");
					String MRNo = webDB.getTextFromElement(MaterialRequestLocators.Find_MRNo_TableData(ProdNo),
							ElementType.Xpath);
					VariableHandler.setMaterialRequestNo(MRNo);
					logs.logging("INFO", "Material Number: '" + VariableHandler.getMaterialRequestNo() + "'");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Create Material Request
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateMaterialRequest(String Location, String RequestType, String Requestor, String Employee) {
		flag = webDB.waitForElement(CommonUiLocators.CreateMaterialRequestButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Create Material Request Button Is Dsiplayed");
			webDB.clickAnElement(CommonUiLocators.CreateMaterialRequestButton, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialRequestLocators.CreateLocationLov, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Create Location Lov Button Is Displayed");
				commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.CreateLocationLov, Location);
				webDB.selectDropDownByText(MaterialRequestLocators.RequestType, RequestType, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.RequestorLovButton, Requestor);
				commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.EmployeeLovButton, Employee);
				flag = webDB.waitForElement(MaterialRequestLocators.TickIconMaterialRequest, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Tick Icon Is Displayed");
					webDB.clickAnElement(MaterialRequestLocators.TickIconMaterialRequest, ElementType.Xpath);
					flag = webDB.waitForElement(MaterialRequestLocators.HeaderLocationId, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Location Id Is Displayed");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to validate Item Warehouse Requester Name and Request
	 * Quantity by search with Material Request Number
	 * 
	 * @author Sakthi-GWL
	 * @param MRNo
	 * @param Item
	 * @param Warehouse
	 * @param ReqName
	 */
	public void validateItemWHReqNameQuantityWithProdNo(String MRNo, String Item, String Warehouse, String ReqName) {
		commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.FindProdNoLovBtn, MRNo);
		flag = webDB.waitForElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(3000);
			flag = webDB.waitForElement(MaterialRequestLocators.Find_Item_TableData(MRNo), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button clicked and MR No: " + MRNo + " Displayed");
				String DisplItem = webDB.getTextFromElement(MaterialRequestLocators.Find_Item_TableData(MRNo),
						ElementType.Xpath);
				logs.logging("INFO", "Displayed Item: " + DisplItem);
				flag = Item.equalsIgnoreCase(DisplItem);
				if (flag) {
					logs.logging("INFO", "Item Validated Successfully In Material Request");
					String DisplWH = webDB.getTextFromElement(MaterialRequestLocators.Find_Warehouse_TableData(MRNo),
							ElementType.Xpath);
					logs.logging("INFO", "Displayed WareHouse: " + DisplWH);
					flag = Warehouse.equalsIgnoreCase(DisplWH);
					if (flag) {
						logs.logging("INFO", "Warehouse Validated Successfully In Material Request");
						String DisplReqName = webDB.getTextFromElement(
								MaterialRequestLocators.Find_RequesterName_TableData(MRNo), ElementType.Xpath);
						logs.logging("INFO", "Displayed Requester Name: " + DisplReqName);
						flag = ReqName.equalsIgnoreCase(DisplReqName);
						if (flag) {
							logs.logging("INFO", "Requester Name Validated Successfully In Material Request");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methos Is Used To Verify Add Item
	 * 
	 * @author Deepan-GWL
	 * 
	 * @param Item
	 * 
	 * @param RequestQty
	 */
	public void verifyAddItem(String Item, String RequestQty) {
		flag = webDB.waitForElement(MaterialRequestLocators.AddButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Add Button Is Displayed");
			webDB.clickAnElement(MaterialRequestLocators.AddButton, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialRequestLocators.ItemTableButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Item Table Button Is Displayed");
				webDB.clickAnElement(MaterialRequestLocators.ItemTableButton, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.ItemLovButton, Item);
				webDB.moveToElement(MaterialRequestLocators.UOM, ElementType.Xpath);
				webDB.moveToElement(MaterialRequestLocators.RequestQuantity, ElementType.Xpath);
				flag = webDB.waitForElement(MaterialRequestLocators.RequestQuantity, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Request Quantity Is Displayed");
					webDB.clickAnElement(MaterialRequestLocators.RequestQuantity, ElementType.Xpath);
					flag = webDB.waitForElement(MaterialRequestLocators.RequestQuantityInput, ElementType.Xpath);
					if (flag) {
						webDB.sendTextToAnElement(MaterialRequestLocators.RequestQuantityInput, RequestQty,
								ElementType.Xpath);
						String value = webDB.getAttributeFromElement(MaterialRequestLocators.RequestQuantityInput,
								ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
						logs.logging("INFO", "Get Text Quantity Name Value from Table Is: " + value);
						flag = value.contains(RequestQty);
						if (flag) {
							logs.logging("INFO", "Quantity Entered: " + RequestQty);
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Save Line Button
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	public void lineSaveButton() {
		flag = webDB.waitForElement(MaterialRequestLocators.LineTickIcon, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Tick Icon Is Displayed");
			webDB.clickAnElement(MaterialRequestLocators.LineTickIcon, ElementType.Xpath);
			if (webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath)) {
				webDB.javaScriptClickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			} else {
				if (webDB.isElementDisplayed(CommonUiLocators.FailedNotifyMessage, ElementType.Xpath)) {
					logs.logging("INFO", "Save Entry Item failed Notification Displayed");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Header Material Request Validation
	 * 
	 * @author Deepan-GWL
	 * @param Location
	 * @param Unit
	 * @param Source
	 * @param Requestor
	 * @param Employee
	 */
	public void verifyHeaderMaterialRequestValidation(String Location, String Unit, String Source, String Requestor,
			String Employee) {
		purchaseOrderFunc.verifyGetTextValidation(MaterialRequestLocators.HeaderLocationId, Location);
		purchaseOrderFunc.verifyDomPropertyValidation(MaterialRequestLocators.HeaderEmployee, Employee);
//		purchaseOrderFunc.verifyGetTextValidation(MaterialRequestLocators.HeaderRequestor, Requestor);
		purchaseOrderFunc.verifyGetTextValidation(MaterialRequestLocators.HeaderSource, Source);
		purchaseOrderFunc.verifyDomPropertyValidation(MaterialRequestLocators.HeaderUnitId, Unit);
		storeMaterialRequestNumber();
	}

	/**
	 * Verify Navigate To Line Details
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void verifyNavigateToLineDetail(String LineAmount, String Unitcost) {
		webDB.moveToElement(MaterialRequestLocators.ViewLineDetails, ElementType.Xpath);
		flag = webDB.waitForElement(MaterialRequestLocators.ViewLineDetails, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "View Line Details Is Displayed");
			webDB.clickAnElement(MaterialRequestLocators.ViewLineDetails, ElementType.Xpath);
			flag = webDB.waitForClickElement(MaterialRequestLocators.IFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Iframe Is Displayed");
				webDB.switchToFrame(MaterialRequestLocators.IFrame, ElementType.Xpath);
				flag = webDB.waitForElement(MaterialRequestLocators.UnitCostLineDetail, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Line Amount Unit Cost Is Displayed");
					verifyDomPropertyValidation(MaterialRequestLocators.LineDetailLineAmount, LineAmount);
					Unitcost = purchaseOrderFunc
							.verifyRoundOffBigDecimalValueWithReturnString(new BigDecimal(Unitcost));
					verifyDomPropertyValidation(MaterialRequestLocators.UnitCostLineDetail, Unitcost);
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(MaterialRequestLocators.CloseLineDetail, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Close Line Details Is Displayed");
						webDB.clickAnElement(MaterialRequestLocators.CloseLineDetail, ElementType.Xpath);
						flag = webDB.waitForElement(MaterialRequestLocators.HeaderLocationId, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Location Id Is Displayed");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Line Amount Calculation
	 * 
	 * @author Deepan-GWL
	 * @param OrderedQty
	 * @param UnitCost
	 * @return String LineAmount
	 */
	public String lineAmountCalculation(String OrderedQty, String UnitCost) {
		BigDecimal Quantityd = new BigDecimal(OrderedQty);
		BigDecimal Costd = new BigDecimal(UnitCost);
		BigDecimal LineAmountd = Costd.multiply(Quantityd);
		String LineAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnString(LineAmountd);
		VariableHandler.setTotalLineAmount(VariableHandler.getTotalLineAmount()
				.add(purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(LineAmountd)));
		logs.logging("INFO", "Calculated Line Amount Is: " + LineAmount);
		return LineAmount;
	}

	/**
	 * This Method Is Used To Header Save And Post
	 * 
	 * @author Deepan-GWL
	 */
	public void headerSaveAndPost() {
		flag = webDB.waitForElement(MaterialRequestLocators.HeaderTickIcon, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header Tick Icon Is Displayed");
			webDB.moveToElement(MaterialRequestLocators.HeaderTickIcon, ElementType.Xpath);
			webDB.clickAnElement(MaterialRequestLocators.HeaderTickIcon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForClickElement(MaterialRequestLocators.HeaderPostIcon, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(MaterialRequestLocators.HeaderPostIcon, ElementType.Xpath);
				logs.logging("INFO", "Header Post Icon Is Clicked");
				flag = webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
					flag = webDB.waitForElement(CommonUiLocators.WorkAppoval_OkBtn, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Header Tick Icon Is Displayed");
						webDB.clickAnElement(CommonUiLocators.WorkAppoval_OkBtn, ElementType.Xpath);
						webDB.switchToDefaultContent();
						commonFunc.checkSuccessToastMessageDisappear();
						flag = webDB.waitForElement(CommonUiLocators.CreateMaterialRequestButton, ElementType.Xpath);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Dom Property Validations
	 * 
	 * @author Deepan-GWL
	 * @param locators
	 * @param Value
	 */
	public void verifyDomPropertyValidation(String locators, String Value) {
		flag = webDB.waitForElement(locators, ElementType.Xpath);
		if (flag) {
			String text = webDB.getDomPropertyFromElement(locators, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value")).replace(",", "");
			logs.logging("INFO", "Get Text Value Is: " + text);
			logs.logging("INFO", "Expected Value Is: " + Value);
			flag = text.contains(Value);
			if (flag) {
				logs.logging("INFO", "Verified Actual Value With Expected Value");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Store Material Request Number
	 * 
	 * @author Deepan-GWL
	 */
	public void storeMaterialRequestNumber() {
		String Number = webDB.getTextFromElement(MaterialRequestLocators.MaterialRequestDocumentNo, ElementType.Xpath);
		logs.logging("INFO", "Get Text Material Document Number Is: " + Number);
		VariableHandler.setMRNumber(Number);
	}

	/**
	 * This Method Is Used To Verify Create Material Request
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateMaterialRequest() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToInventoryMaterialRequest();
		verifyCreateMaterialRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("IntraTransfer"),
				commonFunc.getDataFromTestDataSheet("Warehouse100"), commonFunc.getDataFromTestDataSheet("Vinoth"));
		verifyHeaderMaterialRequestValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("ManualValue"),
				commonFunc.getDataFromTestDataSheet("Warehouse100"), commonFunc.getDataFromTestDataSheet("Vinoth"));
		VariableHandler.setTotalLineAmount(BigDecimal.ZERO);
		VariableHandler.setLineAmount(BigDecimal.ZERO);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("MRTMRVSheet"));
		for (int i = 1; i < itemmultiple.length; i++) {
			verifyAddItem(itemmultiple[i][0], itemmultiple[i][2]);
			lineSaveButton();
			String amount = lineAmountCalculation(itemmultiple[i][2],
					VariableHandler.getStockCost().get(itemmultiple[i][0]));
			verifyNavigateToLineDetail(amount, VariableHandler.getStockCost().get(itemmultiple[i][0]));
		}
		headerSaveAndPost();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		approvalPageFunc.navigateToApprovals();
		approvalPageFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
		verifyValidatedetailsPoDetails(VariableHandler.getMRNumber(), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("ManualValue"),
				commonFunc.getDataFromTestDataSheet("Warehouse100"), commonFunc.getDataFromTestDataSheet("Vinoth"),
				commonFunc.getDataFromTestDataSheet("EntryStatus"));
		approvalPageFunc.verifyNavigateback();
		approvalPageFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
		approvalPageFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToInventoryMaterialRequest();
		checkStatusOfMRNo(VariableHandler.getMRNumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
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
	public void verifyValidatedetailsPoDetails(String MRNumber, String Location, String Unit, String Source,
			String Requestor, String Employee, String status) {
		commonFunc.hardWait(1000);
		flag = webDB.waitForClickElement(ApprovalPageLocators.PODetailsInfoBtn(MRNumber), ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(ApprovalPageLocators.PODetailsInfoBtn(MRNumber), ElementType.Xpath);
			purchaseOrderFunc.verifyGetTextValidation(ApprovalPageLocators.MRLocationID, Location);
			purchaseOrderFunc.verifyGetTextValidation(ApprovalPageLocators.MREmployee, Employee);
			purchaseOrderFunc.verifyGetTextValidation(ApprovalPageLocators.MRRequestor, Requestor);
			purchaseOrderFunc.verifyGetTextValidation(ApprovalPageLocators.MRSource, Source);
			purchaseOrderFunc.verifyGetTextValidation(ApprovalPageLocators.MRStatus, status);
			purchaseOrderFunc.verifyGetTextValidation(ApprovalPageLocators.MRUnitId, Unit);

		}
		Assert.assertTrue(flag);
	}

	/**
	 * Check Status Of Mr No
	 * 
	 * @author Deepan-GWL
	 * @param MrNo
	 * @param ExpectedStatus
	 */
	public void checkStatusOfMRNo(String MrNo, String ExpectedStatus) {
		commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.RequestNumber, MrNo);
		flag = webDB.waitForElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(MaterialRequestLocators.MRApproveStatusTable(MrNo), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button clicked and MR No: " + MrNo + " Displayed");
				String Status = webDB.getTextFromElement(MaterialRequestLocators.MRApproveStatusTable(MrNo),
						ElementType.Xpath);
				logs.logging("INFO", "MR Number: '" + MrNo + "' searched and displaying status: " + Status);
				flag = Status.equalsIgnoreCase(ExpectedStatus);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Create Material Request
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateMaterialRequestIntertype() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToInventoryMaterialRequest();
		verifyCreateMaterialRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Inter Transfer"),
				commonFunc.getDataFromTestDataSheet("EmpWareHouse"), commonFunc.getDataFromTestDataSheet("Vinoth"));
		verifyHeaderMaterialRequestValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("ManualValue"),
				commonFunc.getDataFromTestDataSheet("EmpWareHouse"), commonFunc.getDataFromTestDataSheet("Vinoth"));
		VariableHandler.setTotalLineAmount(BigDecimal.ZERO);
		VariableHandler.setLineAmount(BigDecimal.ZERO);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("MRTMRVSheet"));
		for (int i = 1; i < itemmultiple.length; i++) {
			verifyAddItem(itemmultiple[i][0], itemmultiple[i][2]);
			lineSaveButton();
			String amount = lineAmountCalculation(itemmultiple[i][2],
					VariableHandler.getStockCost().get(itemmultiple[i][0]));
			verifyNavigateToLineDetail(amount, VariableHandler.getStockCost().get(itemmultiple[i][0]));
		}
		headerSaveAndPost();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		approvalPageFunc.navigateToApprovals();
		approvalPageFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
		verifyValidatedetailsPoDetails(VariableHandler.getMRNumber(), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("ManualValue"),
				commonFunc.getDataFromTestDataSheet("EmpWareHouse"), commonFunc.getDataFromTestDataSheet("Vinoth"),
				commonFunc.getDataFromTestDataSheet("EntryStatus"));
		approvalPageFunc.verifyNavigateback();
		approvalPageFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
		approvalPageFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToInventoryMaterialRequest();
		checkStatusOfMRNo(VariableHandler.getMRNumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To Verify Create Material Request
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateMaterialRequestDepartmenttype() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToInventoryMaterialRequest();
		verifyCreateMaterialRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DepartmentValue"), commonFunc.getDataFromTestDataSheet("Prod100"),
				commonFunc.getDataFromTestDataSheet("Vinoth"));
		verifyHeaderMaterialRequestValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("ManualValue"),
				commonFunc.getDataFromTestDataSheet("Prod100"), commonFunc.getDataFromTestDataSheet("Vinoth"));
		VariableHandler.setTotalLineAmount(BigDecimal.ZERO);
		VariableHandler.setLineAmount(BigDecimal.ZERO);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("MRTMRVSheet"));
		for (int i = 1; i < itemmultiple.length; i++) {
			verifyAddItem(itemmultiple[i][0], itemmultiple[i][2]);
			lineSaveButton();
			String amount = lineAmountCalculation(itemmultiple[i][2],
					VariableHandler.getStockCost().get(itemmultiple[i][0]));
			verifyNavigateToLineDetail(amount, VariableHandler.getStockCost().get(itemmultiple[i][0]));
		}
		commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.ISSCodeButton,
				commonFunc.getDataFromTestDataSheet("IssCodeValue"));
		headerSaveAndPost();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		approvalPageFunc.navigateToApprovals();
		approvalPageFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
		verifyValidatedetailsPoDetails(VariableHandler.getMRNumber(), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("ManualValue"),
				commonFunc.getDataFromTestDataSheet("Production100"), commonFunc.getDataFromTestDataSheet("Vinoth"),
				commonFunc.getDataFromTestDataSheet("EntryStatus"));
		approvalPageFunc.verifyNavigateback();
		approvalPageFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
		approvalPageFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToInventoryMaterialRequest();
		checkStatusOfMRNo(VariableHandler.getMRNumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To Verify Create Material Request With Wip Type
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateMaterialRequestWipType() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToInventoryMaterialRequest();
		verifyCreateMaterialRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WipType"), commonFunc.getDataFromTestDataSheet("WIP100"),
				commonFunc.getDataFromTestDataSheet("Vinoth"));
		verifyHeaderMaterialRequestValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("ManualValue"),
				commonFunc.getDataFromTestDataSheet("WorkInProgressWH"), commonFunc.getDataFromTestDataSheet("Vinoth"));
		VariableHandler.setTotalLineAmount(BigDecimal.ZERO);
		VariableHandler.setLineAmount(BigDecimal.ZERO);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("MRToMRVWipSheet"));
		for (int i = 1; i < itemmultiple.length; i++) {
			verifyAddItem(itemmultiple[i][0], itemmultiple[i][2]);
			lineSaveButton();
			String amount = lineAmountCalculation(itemmultiple[i][2],
					VariableHandler.getStockCost().get(itemmultiple[i][0]));
			verifyNavigateToLineDetail(amount, VariableHandler.getStockCost().get(itemmultiple[i][0]));
		}
		headerSaveAndPost();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		approvalPageFunc.navigateToApprovals();
		approvalPageFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
		verifyValidatedetailsPoDetails(VariableHandler.getMRNumber(), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("ManualValue"),
				commonFunc.getDataFromTestDataSheet("WorkInProgressWH"), commonFunc.getDataFromTestDataSheet("Vinoth"),
				commonFunc.getDataFromTestDataSheet("EntryStatus"));
		approvalPageFunc.verifyNavigateback();
		approvalPageFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
		approvalPageFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToInventoryMaterialRequest();
		checkStatusOfMRNo(VariableHandler.getMRNumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To Verify Create Material Request
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateMaterialRequestSubcontractor() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToInventoryMaterialRequest();
		verifyCreateMaterialRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Subcontractor"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("Vinoth"));
		verifyHeaderMaterialRequestValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("ManualValue"),
				commonFunc.getDataFromTestDataSheet("POSupplier"), commonFunc.getDataFromTestDataSheet("Vinoth"));
		VariableHandler.setTotalLineAmount(BigDecimal.ZERO);
		VariableHandler.setLineAmount(BigDecimal.ZERO);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("MRToMRVWipSheet"));
		for (int i = 1; i < itemmultiple.length; i++) {
			verifyAddItem(itemmultiple[i][0], itemmultiple[i][2]);
			lineSaveButton();
			String amount = lineAmountCalculation(itemmultiple[i][2],
					VariableHandler.getStockCost().get(itemmultiple[i][0]));
			verifyNavigateToLineDetail(amount, VariableHandler.getStockCost().get(itemmultiple[i][0]));
		}
		headerSaveAndPost();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		approvalPageFunc.navigateToApprovals();
		approvalPageFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
		verifyValidatedetailsPoDetails(VariableHandler.getMRNumber(), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("ManualValue"),
				commonFunc.getDataFromTestDataSheet("POSupplier"), commonFunc.getDataFromTestDataSheet("Vinoth"),
				commonFunc.getDataFromTestDataSheet("EntryStatus"));
		approvalPageFunc.verifyNavigateback();
		approvalPageFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
		approvalPageFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToInventoryMaterialRequest();
		checkStatusOfMRNo(VariableHandler.getMRNumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To Verify Create Material Request Document
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateMaterialRequestDocument(String DocNo) {
		commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.ReqNo, DocNo);
		flag = webDB.waitForElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(MaterialRequestLocators.RequestMRNo(DocNo), ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(2000);
				logs.logging("INFO", "Search Button Is Clicked");
				webDB.clickAnElement(MaterialRequestLocators.RequestMRNo(DocNo), ElementType.Xpath);
				flag = webDB.waitForElement(MaterialRequestLocators.HeaderType, ElementType.Xpath);
				if (flag) {
					String text = webDB.getTextFromElement(MaterialRequestLocators.HeaderType, ElementType.Xpath);
					logs.logging("INFO", "Get Text Header Value Is: " + text);
					logs.logging("INFO", "Expected Value Is: " + commonFunc.getDataFromTestDataSheet("Subcontractor"));
					flag = text.equals(commonFunc.getDataFromTestDataSheet("Subcontractor"));
					if (flag) {
						logs.logging("INFO", "Verified Actual Value With Expected Value");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Navigate To Line Details
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void verifyNavigateToLineDetail(String DocNo) {
		webDB.moveToElement(MaterialRequestLocators.ViewLineDetails, ElementType.Xpath);
		flag = webDB.waitForElement(MaterialRequestLocators.ViewLineDetails, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "View Line Details Is Displayed");
			webDB.clickAnElement(MaterialRequestLocators.ViewLineDetails, ElementType.Xpath);
			flag = webDB.waitForClickElement(MaterialRequestLocators.IFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Iframe Is Displayed");
				webDB.switchToFrame(MaterialRequestLocators.IFrame, ElementType.Xpath);
				flag = webDB.waitForElement(MaterialRequestLocators.OrderNo, ElementType.Xpath);
				if (flag) {
					String text = webDB.getTextFromElement(MaterialRequestLocators.OrderNo, ElementType.Xpath);
					logs.logging("INFO", "Get Text Order Number Is: " + text);
					flag = text.equals(DocNo);
					if (flag) {
						logs.logging("INFO", "Verified Doc No With Actual And Expected");
						webDB.switchToDefaultContent();
						flag = webDB.waitForElement(MaterialRequestLocators.CloseLineDetail, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Close Line Details Is Displayed");
							webDB.clickAnElement(MaterialRequestLocators.CloseLineDetail, ElementType.Xpath);
							flag = webDB.waitForElement(MaterialRequestLocators.HeaderLocationId, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Location Id Is Displayed");
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to store Material Request Number And check Status approve
	 * with Production Order Number
	 * 
	 * @author Sakthi-GWL
	 * @param ProdNo
	 * @param ExpectedStatus
	 */
	public void storeMRNoAndCheckStatusApproveWithProdNo(String ProdNo) {
		commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.FindProdNoLovBtn, ProdNo);
		flag = webDB.waitForElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(MaterialRequestLocators.Find_MRNo_TableData(ProdNo), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button clicked and Prod No: " + ProdNo + " Displayed");
				String MRNo = webDB.getTextFromElement(MaterialRequestLocators.Find_MRNo_TableData(ProdNo),
						ElementType.Xpath);
				VariableHandler.setMaterialRequestNo(MRNo);
				logs.logging("INFO", "Material Number: '" + VariableHandler.getMaterialRequestNo() + "'");
				flag = webDB.waitForElement(MaterialRequestLocators.Find_Status_TableData(ProdNo), ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Search Button clicked and MRP No: " + ProdNo + " Displayed");
					String Status = webDB.getTextFromElement(MaterialRequestLocators.Find_Status_TableData(ProdNo),
							ElementType.Xpath);
					logs.logging("INFO",
							"Production Number: '" + ProdNo + "' searched and displaying status: " + Status);
					flag = Status.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ApproveStatus"));
					if (flag) {
						logs.logging("INFO", "Status was approved for Material request");
					} else {
						webDB.moveToElement(MaterialRequestLocators.Find_MRNo_TableData(ProdNo), ElementType.Xpath);
						webDB.clickAnElement(MaterialRequestLocators.Find_MRNo_TableData(ProdNo), ElementType.Xpath);
						flag = webDB.waitForElement(MaterialRequestLocators.HeaderTickIcon, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "MR Status not Approve selected the Document to post");
							headerSaveAndPost();
							commonFunc.logoutUser();
							commonFunc.validCommonLogin(System.getProperty("HodUsername"),
									System.getProperty("HodPassword"), "HodUsername-Admin");
							approvalPageFunc.navigateToApprovals();
							approvalPageFunc.filterDocumentsforApproval(VariableHandler.getMaterialRequestNo());
							approvalPageFunc.verifyApproveWithDocNumber(VariableHandler.getMaterialRequestNo());
							flag = commonFunc.logoutUser();
							commonFunc.validCommonLogin(System.getProperty("TvsUsername"),
									System.getProperty("TvsPassword"), "TvsUsername");
							commonFunc.navigateToInventoryMaterialRequest();
							checkStatusOfMRNo(VariableHandler.getMaterialRequestNo(),
									commonFunc.getDataFromTestDataSheet("ApproveStatus"));
						}
					}
				}
			}
		}
		assertTrue(flag);
	}
}
