package commonfunctions.productionplanning;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.ItemDetailsHandler;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.productionplanning.ProductionAndShopFloorSetupLocator;
import commonlocators.supplychainmanagement.SalesInvoiceLocators;
import groovyjarjarantlr4.v4.runtime.tree.xpath.XPath;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class ProductionAndShopFloorSetupFunctions {

	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	private ApprovalPageFunctions approveFunc = new ApprovalPageFunctions();
	private static ItemDetailsHandler itemDetailHandler = new ItemDetailsHandler();
	DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();
	private static ExcelUtils excelUtils = new ExcelUtils();
	private static ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();

	/**
	 * This Method Used For Clicking WorkCenter Tab
	 * 
	 * @author Vimal - RMITS
	 */
	public void clickingWorkCenterTab() {
		flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.WorkCenter_Tab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionAndShopFloorSetupLocator.WorkCenter_Tab, ElementType.Xpath);
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.WorkCenter_AddIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "WorkCenter Tab Navigated Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Adding WorkCenter
	 * 
	 * @author Vimal - RMITS
	 * @param WorkCenterInput
	 * @param DepartmentInput
	 */
	public void addingWorkCenter(String WorkCenterInput, String DepartmentInput) {
		flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.WorkCenter_AddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionAndShopFloorSetupLocator.WorkCenter_AddIcon, ElementType.Xpath);
			commonFunc.hardWait(1000);
			String WorkCenterSeqNo = RandomStringUtils.randomNumeric(6);
			if (WorkCenterSeqNo.equals("000")) {
				WorkCenterSeqNo = "10";
			}
			String Workcenter = WorkCenterInput + WorkCenterSeqNo;
			webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.WorkCenter_Input, Workcenter,
					ElementType.Xpath);
			commonFunc.hardWait(1000);
			webDB.pressTabKey();
			commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.Dept_Lov, DepartmentInput);
			commonFunc.hardWait(1000);
			webDB.pressTabKey();
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.WorkCenterSaveIcon, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ProductionAndShopFloorSetupLocator.WorkCenterSaveIcon, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Workcenter_ToastMsg, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "WorkCenter Created Sucessfully");
				}
			}
		}
		commonFunc.hardWait(2000);
		assertTrue(flag);
	}

	/**
	 * This method Used For How to define Workcenter
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean howtodefineWorkcenters() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSetupPlanningandManufacturing();
		clickingWorkCenterTab();
		addingWorkCenter(commonFunc.getDataFromTestDataSheet("WorkCenterInput"),
				commonFunc.getDataFromTestDataSheet("DepartmentInput"));
		return flag;
	}

	/**
	 * This Method Used For Clicking Process Tab
	 * 
	 * @author Vimal - RMITS
	 */
	public void clickingProcessTab() {
		flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Process_Tab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Process_Tab, ElementType.Xpath);
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Process_AddIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Process Tab Navigated Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Adding Process With Inside Type
	 * 
	 * @author Vimal - RMITS
	 * @param ProcessIDinput
	 * @param ProcessNameInput
	 * @param Proc_UOM
	 * @param Proc_Inside_Type
	 * @param Proc_Suffix_input
	 */
	public void addingProcessWithInsideType(String ProcessIDinput, String ProcessNameInput, String Proc_UOM,
			String Proc_Inside_Type, String Proc_Suffix_input) {
		flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Process_AddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Process_AddIcon, ElementType.Xpath);
			String ProcessIDSeqNo = RandomStringUtils.randomNumeric(6);
			if (ProcessIDSeqNo.equals("000")) {
				ProcessIDSeqNo = "10";
			}
			String ProcessID = ProcessIDinput + ProcessIDSeqNo;
			webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.ProcessID_Input, ProcessID, ElementType.Xpath);
			commonFunc.hardWait(1000);
			webDB.pressTabKey();
			String ProcessNameSeqNo = RandomStringUtils.randomNumeric(4);
			if (ProcessNameSeqNo.equals("000")) {
				ProcessNameSeqNo = "10";
			}
			String ProcessNameID = ProcessNameInput + ProcessNameSeqNo;
			webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.ProcessName_Input, ProcessNameID,
					ElementType.Xpath);
			commonFunc.hardWait(1000);
			webDB.pressTabKey();
			commonFunc.hardWait(1000);
			commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.Process_UOM_Lov, Proc_UOM);
			webDB.pressTabKey();
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Proc_Type_Field, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Proc_Type_Field, ElementType.Xpath);
				webDB.selectDropDownByText(ProductionAndShopFloorSetupLocator.Proc_Type_Select_Class, Proc_Inside_Type,
						ElementType.Xpath);
				logs.logging("INFO", "Inside Type Selected Successfully");
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Proc_Suffix, ElementType.Xpath);
				if (flag) {
					webDB.doubleClickAnElement(ProductionAndShopFloorSetupLocator.Proc_Suffix, ElementType.Xpath);
					commonFunc.hardWait(1000);
					webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.Proc_Suffix_input, Proc_Suffix_input,
							ElementType.Xpath);
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Process_Save_Icon,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Process_Save_Icon, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(1000);
						String GetProcID = webDB.getTextFromElement(ProductionAndShopFloorSetupLocator.Get_ProcessID,
								ElementType.Xpath);
						logs.logging("INFO", "Get Process ID Is: " + GetProcID);
						VariableHandler.setProcessIDAsso(GetProcID);
						commonFunc.hardWait(1000);
						flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Process_save_Toast_Msg,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Process created Successfully");
						}
					}

				}
			}
		}
		webDB.navigateToRefresh();
		assertTrue(flag);
	}

	/**
	 * This Method Used For Adding Process With Outside Type
	 * 
	 * @author Vimal - RMITS
	 * @param ProcessIDinput
	 * @param ProcessNameInput
	 * @param Proc_UOM
	 * @param Proc_Outside_Type
	 * @param Proc_Suffix_input
	 * @param Proc_Charge_Item  @
	 */
	public void addingProcessOutSideType(String ProcessIDinput, String ProcessNameInput, String Proc_UOM,
			String Proc_Outside_Type, String Proc_Suffix_input, String Proc_Charge_Item) {
		flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Process_AddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Process_AddIcon, ElementType.Xpath);
			String ProcessIDSeqNo = RandomStringUtils.randomNumeric(6);
			if (ProcessIDSeqNo.equals("000")) {
				ProcessIDSeqNo = "10";
			}
			String ProcessID = ProcessIDinput + ProcessIDSeqNo;
			webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.ProcessID_Input, ProcessID, ElementType.Xpath);
			commonFunc.hardWait(1000);
			webDB.pressTabKey();
			String ProcessNameSeqNo = RandomStringUtils.randomNumeric(5);
			if (ProcessNameSeqNo.equals("000")) {
				ProcessNameSeqNo = "10";
			}
			String ProcessNameID = ProcessNameInput + ProcessNameSeqNo;
			webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.ProcessName_Input, ProcessNameID,
					ElementType.Xpath);
			commonFunc.hardWait(1000);
			webDB.pressTabKey();
			commonFunc.hardWait(1000);
			commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.Process_UOM_Lov, Proc_UOM);
			flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Proc_Type_Field, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Proc_Type_Field, ElementType.Xpath);
				webDB.selectDropDownByText(ProductionAndShopFloorSetupLocator.Proc_Type_Select_Class, Proc_Outside_Type,
						ElementType.Xpath);
				logs.logging("INFO", "Outside Type Selected Successfully");
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Proc_Suffix, ElementType.Xpath);
				if (flag) {
					webDB.doubleClickAnElement(ProductionAndShopFloorSetupLocator.Proc_Suffix, ElementType.Xpath);
					commonFunc.hardWait(1000);
					webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.Proc_Suffix_input, Proc_Suffix_input,
							ElementType.Xpath);
					webDB.pressTabKey();
					commonFunc.hardWait(1000);
					commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.Proc_Charge_Lov,
							Proc_Charge_Item);
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Process_Save_Icon,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Process_Save_Icon, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(1000);
						String GetProcID = webDB.getTextFromElement(ProductionAndShopFloorSetupLocator.Get_ProcessID,
								ElementType.Xpath);
						logs.logging("INFO", "Get Process ID Is: " + GetProcID);
						VariableHandler.setProcessIDAsso(GetProcID);
						commonFunc.hardWait(2500);
						getProcessIDandWriteInExcelSheet(webDB.getDataFromProperties("OpenSCO"));
						commonFunc.hardWait(1000);
						flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.ProcessAsso_Tab,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Process created Successfully");
						}
					}

				}
			}
		}
		webDB.navigateToRefresh();
		assertTrue(flag);
	}

	/**
	 * This Method Used For Adding Process With Outside Type
	 * 
	 * @author Vimal - RMITS
	 * @param ProcessIDinput
	 * @param ProcessNameInput
	 * @param Proc_UOM
	 * @param Proc_Outside_Type
	 * @param Proc_Suffix_input
	 * @param Proc_Charge_Item  @
	 */
	public void addingProcessOutSideTypeForRevise(String ProcessIDinput, String ProcessNameInput, String Proc_UOM,
			String Proc_Outside_Type, String Proc_Suffix_input, String Proc_Charge_Item) {
		flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Process_AddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Process_AddIcon, ElementType.Xpath);
			String ProcessIDSeqNo = RandomStringUtils.randomNumeric(6);
			if (ProcessIDSeqNo.equals("000")) {
				ProcessIDSeqNo = "10";
			}
			String ProcessID = ProcessIDinput + ProcessIDSeqNo;
			webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.ProcessID_Input, ProcessID, ElementType.Xpath);
			commonFunc.hardWait(1000);
			webDB.pressTabKey();
			String ProcessNameSeqNo = RandomStringUtils.randomNumeric(5);
			if (ProcessNameSeqNo.equals("000")) {
				ProcessNameSeqNo = "10";
			}
			String ProcessNameID = ProcessNameInput + ProcessNameSeqNo;
			webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.ProcessName_Input, ProcessNameID,
					ElementType.Xpath);
			commonFunc.hardWait(1000);
			webDB.pressTabKey();
			commonFunc.hardWait(1000);
			commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.Process_UOM_Lov, Proc_UOM);
			flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Proc_Type_Field, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Proc_Type_Field, ElementType.Xpath);
				webDB.selectDropDownByText(ProductionAndShopFloorSetupLocator.Proc_Type_Select_Class, Proc_Outside_Type,
						ElementType.Xpath);
				logs.logging("INFO", "Outside Type Selected Successfully");
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Proc_Suffix, ElementType.Xpath);
				if (flag) {
					webDB.doubleClickAnElement(ProductionAndShopFloorSetupLocator.Proc_Suffix, ElementType.Xpath);
					commonFunc.hardWait(1000);
					webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.Proc_Suffix_input, Proc_Suffix_input,
							ElementType.Xpath);
					webDB.pressTabKey();
					commonFunc.hardWait(1000);
					commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.Proc_Charge_Lov,
							Proc_Charge_Item);
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Process_Save_Icon,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Process_Save_Icon, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(1000);
						String GetProcID = webDB.getTextFromElement(ProductionAndShopFloorSetupLocator.Get_ProcessID,
								ElementType.Xpath);
						logs.logging("INFO", "Get Process ID Is: " + GetProcID);
						VariableHandler.setProcessIDAsso(GetProcID);
						commonFunc.hardWait(1000);
						getProcessIDForReverseandWriteInExcelSheet(webDB.getDataFromProperties("OpenSCORevise"));
						flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.ProcessAsso_Tab,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Process created Successfully");
						}
					}

				}
			}
		}
		webDB.navigateToRefresh();
		assertTrue(flag);
	}

	/**
	 * This Methods Used To Get ProcessID and Write In Excel Sheet
	 * 
	 * @author Vimal - RMITS
	 */
	public void getProcessIDandWriteInExcelSheet(String SheetName) {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Before Process Id ");
		for (int i = 4; i < ItemMultiple.length; i++) {
			String Process = ItemMultiple[i][1];
			if (!Process.equals("")) {
				logs.logging("INFO", "Before Process ID " + Process + "Is : " + VariableHandler.getProcessIDAsso());
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 1, VariableHandler.getProcessIDAsso());
			}
		}

	}

	/**
	 * This Methods Used To Get ProcessID and Write In Excel Sheet
	 * 
	 * @author Vimal - RMITS
	 */
	public void getProcessIDForReverseandWriteInExcelSheet(String SheetName) {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Before Process Id ");
		for (int i = 4; i < ItemMultiple.length; i++) {
			String Process = ItemMultiple[i][1];
			if (!Process.equals("")) {
				logs.logging("INFO", "Before Process ID " + Process + "Is : " + VariableHandler.getProcessIDAsso());
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 1, VariableHandler.getProcessIDAsso());
			}
		}

	}

	/**
	 * This Method Used For Adding Process With Both Type
	 * 
	 * @author Vimal - RMITS
	 * @param ProcessIDinput
	 * @param ProcessNameInput
	 * @param Proc_UOM
	 * @param Proc_Both_Type
	 * @param Proc_Suffix_input
	 * @param Proc_Charge_Item
	 */
	public void addingProcessBothType(String ProcessIDinput, String ProcessNameInput, String Proc_UOM,
			String Proc_Both_Type, String Proc_Suffix_input, String Proc_Charge_Item) {
		flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Process_AddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Process_AddIcon, ElementType.Xpath);
			String ProcessIDSeqNo = RandomStringUtils.randomNumeric(6);
			if (ProcessIDSeqNo.equals("000")) {
				ProcessIDSeqNo = "10";
			}
			String ProcessID = ProcessIDinput + ProcessIDSeqNo;
			webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.ProcessID_Input, ProcessID, ElementType.Xpath);
			commonFunc.hardWait(1000);
			webDB.pressTabKey();
			String ProcessNameSeqNo = RandomStringUtils.randomNumeric(5);
			if (ProcessNameSeqNo.equals("000")) {
				ProcessNameSeqNo = "10";
			}
			String ProcessNameID = ProcessNameInput + ProcessNameSeqNo;
			webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.ProcessName_Input, ProcessNameID,
					ElementType.Xpath);
			commonFunc.hardWait(1000);
			webDB.pressTabKey();
			commonFunc.hardWait(1000);
			commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.Process_UOM_Lov, Proc_UOM);
			webDB.pressTabKey();
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Proc_Type_Field, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Proc_Type_Field, ElementType.Xpath);
				webDB.selectDropDownByText(ProductionAndShopFloorSetupLocator.Proc_Type_Select_Class, Proc_Both_Type,
						ElementType.Xpath);
				logs.logging("INFO", "Both Type Selected Successfully");
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Proc_Suffix, ElementType.Xpath);
				if (flag) {
					webDB.doubleClickAnElement(ProductionAndShopFloorSetupLocator.Proc_Suffix, ElementType.Xpath);
					commonFunc.hardWait(1000);
					webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.Proc_Suffix_input, Proc_Suffix_input,
							ElementType.Xpath);
					webDB.pressTabKey();
					commonFunc.hardWait(1000);
					commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.Proc_Charge_Lov,
							Proc_Charge_Item);
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Process_Save_Icon,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Process_Save_Icon, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(1000);
						String GetProcID = webDB.getTextFromElement(ProductionAndShopFloorSetupLocator.Get_ProcessID,
								ElementType.Xpath);
						logs.logging("INFO", "Get Process ID Is: " + GetProcID);
						VariableHandler.setProcessIDAsso(GetProcID);
						commonFunc.hardWait(1000);
						flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Process_save_Toast_Msg,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Process created Successfully");
						}
					}

				}
			}
		}
		webDB.navigateToRefresh();
		assertTrue(flag);
	}

	/**
	 * This method Used For how to define Inside Type Process And Associated The
	 * Process
	 * 
	 * @author Vimal - RMITS
	 */
	public void howtodefineInsideTypeProcessAndAssociatedTheProcess() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSetupPlanningandManufacturing();
		clickingProcessTab();
		addingProcessWithInsideType(commonFunc.getDataFromTestDataSheet("ProcessIDinput"),
				commonFunc.getDataFromTestDataSheet("ProcessNameInput"),
				commonFunc.getDataFromTestDataSheet("Proc_UOM"),
				commonFunc.getDataFromTestDataSheet("Proc_Inside_Type"),
				commonFunc.getDataFromTestDataSheet("Proc_Suffix_input"));
		ClickingProcessAssociatedTab();
		AssociateProcessIdAndWorkcenter(VariableHandler.getProcessIDAsso(),
				commonFunc.getDataFromTestDataSheet("ProcessAsso_Unit"),
				commonFunc.getDataFromTestDataSheet("ProcAsso_WorkCenter"),
				commonFunc.getDataFromTestDataSheet("Proc_Asso_ConsWH"),
				commonFunc.getDataFromTestDataSheet("Proc_Asso_RecpWH"));
	}

	/**
	 * This method Used For how to define Outside Type Process And Associated The
	 * Process
	 * 
	 * @author Vimal - RMITS @
	 */
	public void howtodefineOutsideTypeProcessAndAssociatedTheProcess() {
		commonFunc.navigateToSetupPlanningandManufacturing();
		clickingProcessTab();
		addingProcessOutSideType(commonFunc.getDataFromTestDataSheet("ProcessIDinput"),
				commonFunc.getDataFromTestDataSheet("ProcessNameInput"),
				commonFunc.getDataFromTestDataSheet("Proc_UOM"),
				commonFunc.getDataFromTestDataSheet("Proc_Outside_Type"),
				commonFunc.getDataFromTestDataSheet("Proc_Suffix_input"),
				commonFunc.getDataFromTestDataSheet("Proc_Charge_Item"));
		ClickingProcessAssociatedTab();
		AssociateProcessIdAndWorkcenter(VariableHandler.getProcessIDAsso(),
				commonFunc.getDataFromTestDataSheet("ProcessAsso_Unit"),
				commonFunc.getDataFromTestDataSheet("ProcAsso_WorkCenter"),
				commonFunc.getDataFromTestDataSheet("Proc_Asso_ConsWH"),
				commonFunc.getDataFromTestDataSheet("Proc_Asso_RecpWH"));
	}

	/**
	 * This method Used For how to define Outside Type Process And Associated The
	 * Process
	 * 
	 * @author Vimal - RMITS
	 */
	public void howtodefineOutsideTypeProcessAndAssociatedTheProcessForOpenscoReversing() {
		commonFunc.navigateToSetupPlanningandManufacturing();
		clickingProcessTab();
		addingProcessOutSideTypeForRevise(commonFunc.getDataFromTestDataSheet("ProcessIDinput"),
				commonFunc.getDataFromTestDataSheet("ProcessNameInput"),
				commonFunc.getDataFromTestDataSheet("Proc_UOM"),
				commonFunc.getDataFromTestDataSheet("Proc_Outside_Type"),
				commonFunc.getDataFromTestDataSheet("Proc_Suffix_input"),
				commonFunc.getDataFromTestDataSheet("Proc_Charge_Item"));
		ClickingProcessAssociatedTab();
		AssociateProcessIdAndWorkcenter(VariableHandler.getProcessIDAsso(),
				commonFunc.getDataFromTestDataSheet("ProcessAsso_Unit"),
				commonFunc.getDataFromTestDataSheet("ProcAsso_WorkCenter"),
				commonFunc.getDataFromTestDataSheet("Proc_Asso_ConsWH"),
				commonFunc.getDataFromTestDataSheet("Proc_Asso_RecpWH"));
	}

	/**
	 * This method Used For how to define Both Type Process And Associated The
	 * Process
	 * 
	 * @author Vimal - RMITS
	 */
	public void howtodefineBothTypeProcessAndAssociatedTheProcess() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSetupPlanningandManufacturing();
		clickingProcessTab();
		addingProcessBothType(commonFunc.getDataFromTestDataSheet("ProcessIDinput"),
				commonFunc.getDataFromTestDataSheet("ProcessNameInput"),
				commonFunc.getDataFromTestDataSheet("Proc_UOM"), commonFunc.getDataFromTestDataSheet("Proc_Both_Type"),
				commonFunc.getDataFromTestDataSheet("Proc_Suffix_input"),
				commonFunc.getDataFromTestDataSheet("Proc_Charge_Item"));
		ClickingProcessAssociatedTab();
		AssociateProcessIdAndWorkcenter(VariableHandler.getProcessIDAsso(),
				commonFunc.getDataFromTestDataSheet("ProcessAsso_Unit"),
				commonFunc.getDataFromTestDataSheet("ProcAsso_WorkCenter"),
				commonFunc.getDataFromTestDataSheet("Proc_Asso_ConsWH"),
				commonFunc.getDataFromTestDataSheet("Proc_Asso_RecpWH"));
	}

	/**
	 * This Method Used For Clicking Process Asso.Tab
	 * 
	 * @author Vimal - RMITS
	 */
	public void ClickingProcessAssociatedTab() {
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.ProcessAsso_Tab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionAndShopFloorSetupLocator.ProcessAsso_Tab, ElementType.Xpath);
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.ProcessAsso_AddIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Process Asso. Tab Navigated Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Inserting Process Asso. Parameter
	 * 
	 * @author Vimal - RMITS
	 * @param ProcessID
	 * @param ProcessAsso_Unit
	 * @param ProcAsso_WorkCenter
	 * @param Proc_Asso_ConsWH
	 * @param Proc_Asso_RecpWH
	 */
	public void AssociateProcessIdAndWorkcenter(String ProcessID, String ProcessAsso_Unit, String ProcAsso_WorkCenter,
			String Proc_Asso_ConsWH, String Proc_Asso_RecpWH) {
		flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.ProcessAsso_AddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionAndShopFloorSetupLocator.ProcessAsso_AddIcon, ElementType.Xpath);
			commonFunc.hardWait(1000);
			commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.ProcessAsso_Lov, ProcessID);
			flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.ProcAsso_Unit_Field, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ProductionAndShopFloorSetupLocator.ProcAsso_Unit_Field, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.ProcAsso_Unit_Lov,
						ProcessAsso_Unit);
				commonFunc.hardWait(1000);
				webDB.pressTabKey();
				commonFunc.hardWait(1000);
				commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.ProcAsso_Workcenter_Lov,
						ProcAsso_WorkCenter);
				flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.ProcAsso_ConsWH_Field,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(ProductionAndShopFloorSetupLocator.ProcAsso_ConsWH_Field, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.ProcAsso_ConsWH_Lov,
							Proc_Asso_ConsWH);
					commonFunc.hardWait(1000);
					webDB.pressTabKey();
					commonFunc.hardWait(1000);
					commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.ProcAsso_RecpWH_Lov,
							Proc_Asso_RecpWH);
					flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Process_Asso_Save_Icon,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Process_Asso_Save_Icon,
								ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(1000);
						flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.ProcessAsso_Toast_Msg,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Process Asso. Created Sucessfully");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Clicking Machine Group Add Icon
	 * 
	 * @author Vimal - RMITS
	 */
	public void clickingMachineGroupAddIcon() {
		flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.MachineGrpAdd_Icon, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(ProductionAndShopFloorSetupLocator.MachineGrpAdd_Icon, ElementType.Xpath);
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Machine_Loc_Lov_btn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Machine Grp Add Icon Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Inserting Machines Group Parameters
	 * 
	 * @author Vimal - RMITS
	 * @param MachineLoc
	 * @param ResourceInput
	 * @param MachineUnitRate
	 * @param MachineUOM
	 * @param MachineNo_of_Units
	 * @param MachineUtilization
	 * @param MachineEfficiency
	 * @param MachineAccount
	 * @param MachineCPC
	 */
	public void insertingMachinesParameters(String MachineLoc, String ResourceInput, String MachineUnitRate,
			String MachineUOM, String MachineNo_of_Units, String MachineUtilization, String MachineEfficiency,
			String MachineAccount, String MachineCPC) {
		commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.Machine_Loc_Lov_btn, MachineLoc);
		flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Machine_Resource_Field, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Machine_Resource_Field, ElementType.Xpath);
			String ResourceIDSeqNo = RandomStringUtils.randomNumeric(4);
			if (ResourceIDSeqNo.equals("000")) {
				ResourceIDSeqNo = "10";
			}
			String ResourceID = ResourceInput + ResourceIDSeqNo;
			webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.Machine_Resource_Input, ResourceID,
					ElementType.Xpath);
			commonFunc.hardWait(1000);
			webDB.pressTabKey();
			commonFunc.hardWait(1000);
			webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.Machine_UnitRate, MachineUnitRate,
					ElementType.Xpath);
			commonFunc.hardWait(1000);
			webDB.pressTabKey();
			commonFunc.hardWait(1000);
			commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.Machine_UOM_Lov, MachineUOM);
			commonFunc.hardWait(1000);
			webDB.pressTabKey();
			commonFunc.hardWait(1000);
			webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.MachineNo_of_Unit, MachineNo_of_Units,
					ElementType.Xpath);
			commonFunc.hardWait(1000);
			webDB.pressTabKey();
			commonFunc.hardWait(1000);
			webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.Machine_Utilization, MachineUtilization,
					ElementType.Xpath);
			commonFunc.hardWait(1000);
			webDB.pressTabKey();
			commonFunc.hardWait(1000);
			webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.Machine_Efficiency, MachineEfficiency,
					ElementType.Xpath);
			flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Machine_Acct, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Machine_Acct, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.Machine_Acct_Lov,
						MachineAccount);
				commonFunc.hardWait(1000);
				webDB.pressTabKey();
				commonFunc.hardWait(1000);
				commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.Machine_CPC_Lov,
						MachineCPC);
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Machine_Save_Icon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Machine_Save_Icon, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
					if (webDB.verifyIsElementDisplayed(ProductionAndShopFloorSetupLocator.Machine_Toast_Msg,
							ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(ProductionAndShopFloorSetupLocator.Machine_Toast_Msg,
								ElementType.Xpath);
						commonFunc.hardWait(2000);
						String GetResourceID = webDB.getTextFromElement(
								ProductionAndShopFloorSetupLocator.Get_Resource_Grp, ElementType.Xpath);
						logs.logging("INFO", "Get Process ID Is: " + GetResourceID);
						VariableHandler.setResourceGrp(GetResourceID);
						commonFunc.hardWait(2000);
						flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Machine_Tab, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Machine Group Created Sucessfully)");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Navigate Machine Tab
	 * 
	 * @author Vimal - RMITS
	 */
	public void naviagateMachineTab() {
		flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Machine_Tab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Machine_Tab, ElementType.Xpath);
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Machine_Add_Icon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Machine Add Icon Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Clicking Machine AddIcon And Inserting Parameters
	 * 
	 * @author Vimal - RMITS
	 * @param MachineLoc
	 * @param MachineIDInput
	 * @param MachineNameInput
	 * @param GetResourceID
	 * @param Machine_Location
	 * @param MachineEfficiency
	 */
	public void clickingMachineAddIcon(String MachineLoc, String MachineIDInput, String MachineNameInput,
			String GetResourceID, String Machine_Location, String MachineEfficiency) {
		flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Machine_Add_Icon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Machine_Add_Icon, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.MachineLocation_Lov,
					MachineLoc);
			flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.MachineID, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ProductionAndShopFloorSetupLocator.MachineID, ElementType.Xpath);
				String MachineIDSeqNo = RandomStringUtils.randomNumeric(4);
				if (MachineIDSeqNo.equals("000")) {
					MachineIDSeqNo = "10";
				}
				String MachineID = MachineIDInput + MachineIDSeqNo;
				webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.MachineID, MachineID, ElementType.Xpath);
				commonFunc.hardWait(1000);
				webDB.pressTabKey();
				commonFunc.hardWait(1000);
				String MachineNameSeqNo = RandomStringUtils.randomNumeric(3);
				if (MachineNameSeqNo.equals("000")) {
					MachineNameSeqNo = "10";
				}
				String MachineName = MachineNameInput + MachineIDSeqNo;
				webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.MachineName, MachineName,
						ElementType.Xpath);
				commonFunc.hardWait(1000);
				commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.stored_Machine_Grp_Lov,
						GetResourceID);
				commonFunc.hardWait(1000);
				commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.Machine_Location,
						Machine_Location);
				webDB.pressTabKey();
				webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.Mach_Eff, MachineEfficiency,
						ElementType.Xpath);
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Machine_Header_SaveIcon,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Machine_Header_SaveIcon, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Machine_Save_ToastMsg,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Machine Created Sucessfully");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method Used For How to Create Resource Group and Resources for Machine
	 * Process
	 * 
	 * @author Vimal - RMITS
	 */
	public void howtoCreateResourceGroupandResourcesforMachine() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToMachineScreen();
		clickingMachineGroupAddIcon();
		insertingMachinesParameters(commonFunc.getDataFromTestDataSheet("MachineLoc"),
				commonFunc.getDataFromTestDataSheet("ResourceInput"),
				commonFunc.getDataFromTestDataSheet("MachineUnitRate"),
				commonFunc.getDataFromTestDataSheet("MachineUOM"),
				commonFunc.getDataFromTestDataSheet("MachineNo_of_Units"),
				commonFunc.getDataFromTestDataSheet("MachineUtilization"),
				commonFunc.getDataFromTestDataSheet("MachineEfficiency"),
				commonFunc.getDataFromTestDataSheet("MachineAccount"),
				commonFunc.getDataFromTestDataSheet("MachineCPC"));
		naviagateMachineTab();
		clickingMachineAddIcon(commonFunc.getDataFromTestDataSheet("MachineLoc"),
				commonFunc.getDataFromTestDataSheet("MachineIDInput"),
				commonFunc.getDataFromTestDataSheet("MachineNameInput"), VariableHandler.getResourceGrp(),
				commonFunc.getDataFromTestDataSheet("Machine_Location"),
				commonFunc.getDataFromTestDataSheet("MachineEfficiency"));
	}

	/**
	 * This method Used For Clicking Manpower Group AddIcon
	 * 
	 * @author Vimal - RMITS
	 */
	public void clickingManpowerGroupAddIcon() {
		flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.ManpowerGrp_Add_Icon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionAndShopFloorSetupLocator.ManpowerGrp_Add_Icon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.MG_LocID, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Manpower Add Icon Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method Used For Inserting Manpower Group Parameter
	 * 
	 * @author Vimal - RMITS
	 * @param LOCATION100
	 * @param Manpower_ResourceGrp
	 * @param MachineUtilization
	 * @param MachineUOM
	 * @param MachineEfficiency
	 */
	public void insertingManpowerGroupParameter(String LOCATION100, String Manpower_ResourceGrp, String MachineUnitRate,
			String MachineUOM, String MachineNo_of_Units, String MachineUtilization, String MachineEfficiency) {
		flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.MG_LocID, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionAndShopFloorSetupLocator.MG_LocID, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.MG_LocID, LOCATION100);
			flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.MG_Resource_Grp_Field, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ProductionAndShopFloorSetupLocator.MG_Resource_Grp_Field, ElementType.Xpath);
				String ManpowerIDSeqNo = RandomStringUtils.randomNumeric(4);
				if (ManpowerIDSeqNo.equals("000")) {
					ManpowerIDSeqNo = "10";
				}
				String ManpowerResourceGrp = Manpower_ResourceGrp + ManpowerIDSeqNo;
				webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.MG_Resource_Grp_Input, ManpowerResourceGrp,
						ElementType.Xpath);
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.MG_UnitRate, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(ProductionAndShopFloorSetupLocator.MG_UnitRate, ElementType.Xpath);
					webDB.doubleClickAnElement(ProductionAndShopFloorSetupLocator.MG_UnitRate, ElementType.Xpath);
					webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.MG_UnitRate_Input, MachineUnitRate,
							ElementType.Xpath);
					commonFunc.hardWait(1000);
					webDB.pressTabKey();
					commonFunc.hardWait(1000);
					commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.MG_UOM_Lov, MachineUOM);
					commonFunc.hardWait(1000);
					webDB.pressTabKey();
					commonFunc.hardWait(1000);
					webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.ManpowerGrp_No_Of_Unit_Input,
							MachineNo_of_Units, ElementType.Xpath);
					commonFunc.hardWait(1000);
					flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.ManpowerGrp_Utilization,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(ProductionAndShopFloorSetupLocator.ManpowerGrp_Utilization,
								ElementType.Xpath);
						webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.ManpowerGrp_Utilization_Input,
								MachineUtilization, ElementType.Xpath);
						flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Manpower_Efficiency_field,
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Manpower_Efficiency_field,
									ElementType.Xpath);
							webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.Manpower_Efficiency_Input,
									MachineEfficiency, ElementType.Xpath);
							flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.ManpowerGrp_Save_Icon,
									ElementType.Xpath);
							if (flag) {
								webDB.scrollToAnElement(ProductionAndShopFloorSetupLocator.ManpowerGrp_Save_Icon,
										ElementType.Xpath);
								commonFunc.hardWait(2000);
								webDB.clickAnElement(ProductionAndShopFloorSetupLocator.ManpowerGrp_Save_Icon,
										ElementType.Xpath);
								commonFunc.checkRefreshCellsDisappear();
								flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
								if (webDB.verifyIsElementDisplayed(
										ProductionAndShopFloorSetupLocator.ManpowerGrp_ToastMsg, ElementType.Xpath)) {
									webDB.clickAnElementWithoutException(
											ProductionAndShopFloorSetupLocator.ManpowerGrp_ToastMsg, ElementType.Xpath);
									commonFunc.hardWait(1000);
									String GetManpowerResourceID = webDB.getTextFromElement(
											ProductionAndShopFloorSetupLocator.Get_ManpowerGrp_Resource,
											ElementType.Xpath);
									logs.logging("INFO", "Get Process ID Is: " + GetManpowerResourceID);
									VariableHandler.setManpowerResourceGrp(GetManpowerResourceID);
									commonFunc.hardWait(2000);
									flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Manpower_Tab,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Manpower Group created Sucessfully");
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
	 * This method Used For Inserting Manpower Group Parameter for Tool
	 * 
	 * @author Vimal - RMITS
	 * @param LOCATION100
	 * @param Manpower_ResourceGrp
	 * @param MachineUtilization
	 * @param MachineUOM
	 * @param MachineEfficiency
	 */
	public void insertingManpowerGroupParameterForTool(String LOCATION100, String Manpower_ResourceGrp,
			String ResourceType, String MachineUnitRate, String MachineUOM, String MachineNo_of_Units,
			String MachineUtilization, String MachineEfficiency) {
		flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.MG_LocID, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionAndShopFloorSetupLocator.MG_LocID, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.MG_LocID, LOCATION100);
			flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.MG_Resource_Grp_Field, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ProductionAndShopFloorSetupLocator.MG_Resource_Grp_Field, ElementType.Xpath);
				String ManpowerIDSeqNo = RandomStringUtils.randomNumeric(4);
				if (ManpowerIDSeqNo.equals("000")) {
					ManpowerIDSeqNo = "10";
				}
				String ManpowerResourceGrp = Manpower_ResourceGrp + ManpowerIDSeqNo;
				webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.MG_Resource_Grp_Input, ManpowerResourceGrp,
						ElementType.Xpath);
				commonFunc.hardWait(1000);
				webDB.pressTabKey();
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Resource_Type_Select_Field,
						ElementType.Xpath);
				if (flag) {
					webDB.selectDropDownByText(ProductionAndShopFloorSetupLocator.Resource_Type_Select_Field,
							ResourceType, ElementType.Xpath);
					commonFunc.hardWait(1000);
					flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.MG_UnitRate, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(ProductionAndShopFloorSetupLocator.MG_UnitRate, ElementType.Xpath);
						webDB.doubleClickAnElement(ProductionAndShopFloorSetupLocator.MG_UnitRate, ElementType.Xpath);
						webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.MG_UnitRate_Input, MachineUnitRate,
								ElementType.Xpath);
						commonFunc.hardWait(1000);
						webDB.pressTabKey();
						commonFunc.hardWait(1000);
						commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.MG_UOM_Lov,
								MachineUOM);
						commonFunc.hardWait(1000);
						webDB.pressTabKey();
						commonFunc.hardWait(1000);
						webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.ManpowerGrp_No_Of_Unit_Input,
								MachineNo_of_Units, ElementType.Xpath);
						commonFunc.hardWait(1000);
						flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.ManpowerGrp_Utilization,
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(ProductionAndShopFloorSetupLocator.ManpowerGrp_Utilization,
									ElementType.Xpath);
							webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.ManpowerGrp_Utilization_Input,
									MachineUtilization, ElementType.Xpath);
							flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Manpower_Efficiency_field,
									ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Manpower_Efficiency_field,
										ElementType.Xpath);
								webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.Manpower_Efficiency_Input,
										MachineEfficiency, ElementType.Xpath);
								flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.ManpowerGrp_Save_Icon,
										ElementType.Xpath);
								if (flag) {
									webDB.scrollToAnElement(ProductionAndShopFloorSetupLocator.ManpowerGrp_Save_Icon,
											ElementType.Xpath);
									commonFunc.hardWait(2000);
									webDB.clickAnElement(ProductionAndShopFloorSetupLocator.ManpowerGrp_Save_Icon,
											ElementType.Xpath);
									commonFunc.checkRefreshCellsDisappear();
									flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
									if (webDB.verifyIsElementDisplayed(
											ProductionAndShopFloorSetupLocator.ManpowerGrp_ToastMsg,
											ElementType.Xpath)) {
										webDB.clickAnElementWithoutException(
												ProductionAndShopFloorSetupLocator.ManpowerGrp_ToastMsg,
												ElementType.Xpath);
										commonFunc.hardWait(1000);
										String GetManpowerResourceID = webDB.getTextFromElement(
												ProductionAndShopFloorSetupLocator.Get_ManpowerGrp_Resource,
												ElementType.Xpath);
										logs.logging("INFO", "Get Process ID Is: " + GetManpowerResourceID);
										VariableHandler.setManpowerResourceGrp(GetManpowerResourceID);
										commonFunc.hardWait(2000);
										flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Manpower_Tab,
												ElementType.Xpath);
										if (flag) {
											logs.logging("INFO", "Manpower Group Tool Type created Sucessfully");
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
	 * This method Used For Clicking Manpower Add icon
	 * 
	 * @author Vimal - RMITS
	 */
	public void clickingManpowerAddicon() {
		flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Manpower_Tab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Manpower_Tab, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Manpower_AddIcon, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Manpower_AddIcon, ElementType.Xpath);
				flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Manpower_Loc_Lov, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Manpower Add Icon Clicked Sucessfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method Used For Clicking Manpower Tool Add icon
	 * 
	 * @author Vimal - RMITS
	 */
	public void clickingManpowerToolAddicon() {
		flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Manpower_Tab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Manpower_Tab, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.ToolsOthersTab, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ProductionAndShopFloorSetupLocator.ToolsOthersTab, ElementType.Xpath);
				flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Tool_Other_AddIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Manpower Tool Add Icon Clicked Sucessfully");
				}
			}
		}
		commonFunc.hardWait(2000);
		assertTrue(flag);
	}

	/**
	 * This Method used for Inserting Manpower Parameter
	 * 
	 * @author Vimal - RMITS
	 * @param LOCATION100
	 * @param ManpowerResource
	 * @param GetManpowerResourceID
	 */
	public void insertingManpowerParameter(String LOCATION100, String ManpowerResource, String GetManpowerResourceID) {
		flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Manpower_Loc_Lov, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Manpower_Loc_Lov, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.Manpower_Loc_Lov, LOCATION100);
			flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Manpower_Resource_Filed, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Manpower_Resource_Filed, ElementType.Xpath);
				String MPResourceIDSeqNo = RandomStringUtils.randomNumeric(4);
				if (MPResourceIDSeqNo.equals("000")) {
					MPResourceIDSeqNo = "10";
				}
				String MpResource = ManpowerResource + MPResourceIDSeqNo;
				webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.Manpower_Resource_Input, MpResource,
						ElementType.Xpath);
				commonFunc.hardWait(2000);
				webDB.pressTabKey();
				commonFunc.hardWait(1000);
				commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.Manpower_Resource_Grp_lov,
						GetManpowerResourceID);
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Manpower_Save_Icon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Manpower_Save_Icon, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Manpower_ToastMsg,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Manpower Created Sucessfully");
					}
				}

			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used for Inserting Manpower Parameter Tool
	 * 
	 * @author Vimal - RMITS
	 * @param LOCATION100
	 * @param ManpowerResource
	 * @param GetManpowerResourceID
	 */
	public void insertingManpowerParameterTool(String LOCATION100, String ToolInput, String GetManpowerResourceID,
			String MachineUnitRate) {
		flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Tool_Other_AddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Tool_Other_AddIcon, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.Tool_loc_ID, LOCATION100);
			flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Tool_Resource_Field, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Tool_Resource_Field, ElementType.Xpath);
				String ToolResourceIDSeqNo = RandomStringUtils.randomNumeric(4);
				if (ToolResourceIDSeqNo.equals("000")) {
					ToolResourceIDSeqNo = "10";
				}
				String ToolResource = ToolInput + ToolResourceIDSeqNo;
				webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.Tool_Resource_Input, ToolResource,
						ElementType.Xpath);
				commonFunc.hardWait(2000);
				webDB.pressTabKey();
				commonFunc.hardWait(1000);
				commonFunc.searchAndSelectValueInFilters(ProductionAndShopFloorSetupLocator.Tool_ResourceGrp_Lov,
						GetManpowerResourceID);
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Tool_Unit_Rate, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Tool_Unit_Rate, ElementType.Xpath);
					webDB.sendTextToAnElement(ProductionAndShopFloorSetupLocator.Tool_UnitRate_Input, MachineUnitRate,
							ElementType.Xpath);
					commonFunc.hardWait(1000);
					flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Tool_Save_Icon, ElementType.Xpath);
					if (flag) {
						webDB.scrollToAnElement(ProductionAndShopFloorSetupLocator.Tool_Save_Icon, ElementType.Xpath);
						commonFunc.hardWait(2000);
						webDB.clickAnElement(ProductionAndShopFloorSetupLocator.Tool_Save_Icon, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
						if (webDB.verifyIsElementDisplayed(ProductionAndShopFloorSetupLocator.Tool_Save_Toast_Msg,
								ElementType.Xpath)) {
							webDB.clickAnElementWithoutException(ProductionAndShopFloorSetupLocator.Tool_Save_Toast_Msg,
									ElementType.Xpath);
							flag = webDB.waitForElement(ProductionAndShopFloorSetupLocator.Process_Resource_Grp,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Tool Created Sucessfully");
							}
						}

					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method Used For How to Create Resource Group and Resources for Manpower
	 * 
	 * @author Vimal - RMITS
	 */
	public void howtoCreateResourceGroupandResourcesformanpower() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToManPowerScreen();
		clickingMachineGroupAddIcon();
		insertingManpowerGroupParameter(commonFunc.getDataFromTestDataSheet("LOCATION100"),
				commonFunc.getDataFromTestDataSheet("Manpower_ResourceGrp"),
				commonFunc.getDataFromTestDataSheet("MachineUnitRate"),
				commonFunc.getDataFromTestDataSheet("MachineUOM"),
				commonFunc.getDataFromTestDataSheet("MachineNo_of_Units"),
				commonFunc.getDataFromTestDataSheet("MachineUtilization"),
				commonFunc.getDataFromTestDataSheet("MachineEfficiency"));
		clickingManpowerAddicon();
		insertingManpowerParameter(commonFunc.getDataFromTestDataSheet("LOCATION100"),
				commonFunc.getDataFromTestDataSheet("ManpowerResource"), VariableHandler.getManpowerResourceGrp());

	}

	/**
	 * This method Used For How to Create Resource Group and Resources for Tool
	 * 
	 * @author Vimal - RMITS
	 */
	public void howtoCreateResourceGroupandResourcesforTool() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToManPowerScreen();
		clickingMachineGroupAddIcon();
		insertingManpowerGroupParameterForTool(commonFunc.getDataFromTestDataSheet("LOCATION100"),
				commonFunc.getDataFromTestDataSheet("Manpower_ResourceGrp"),
				commonFunc.getDataFromTestDataSheet("ResourceType"),
				commonFunc.getDataFromTestDataSheet("MachineUnitRate"),
				commonFunc.getDataFromTestDataSheet("MachineUOM"),
				commonFunc.getDataFromTestDataSheet("MachineNo_of_Units"),
				commonFunc.getDataFromTestDataSheet("MachineUtilization"),
				commonFunc.getDataFromTestDataSheet("MachineEfficiency"));
		clickingManpowerToolAddicon();
		insertingManpowerParameterTool(commonFunc.getDataFromTestDataSheet("LOCATION100"),
				commonFunc.getDataFromTestDataSheet("ToolInput"), VariableHandler.getManpowerResourceGrp(),
				commonFunc.getDataFromTestDataSheet("MachineUnitRate"));

	}
}
