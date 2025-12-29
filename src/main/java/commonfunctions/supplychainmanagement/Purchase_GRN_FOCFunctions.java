package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks December 15, 2025
 */
import java.io.File;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;

public class Purchase_GRN_FOCFunctions {
	private static WebDriverBase webDB = new WebDriverBase();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static ApprovalPageFunctions approveFunc = new ApprovalPageFunctions();
	private static DirectGrnPageFunctions DirectGRNFunc = new DirectGrnPageFunctions();
	private static ReportLoger logs = new ReportLoger();
	private static GrnPageFunctions GRNFunc = new GrnPageFunctions();
	private static GrnPageFunctions2 GRN2Func = new GrnPageFunctions2();
	private boolean flag = false;
	private static PurchaseOrderFunctions POfunc = new PurchaseOrderFunctions();
	private static GateEntryPageFunctions gateEntryPageFunc = new GateEntryPageFunctions();
	private static DBCommonFunctions dbCommonFunc = new DBCommonFunctions();

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier - General
	 * Items with Similar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 * @return booelan flag
	 */
	public boolean verifyFOCForDirectPODomesticSuplrGeneralItemSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L49"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("DomesticPoFOCUDGeneralSameUOM");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigatePurchaseOrderPage();
		}
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier - Material
	 * Items with Similar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 * @return booelan flag
	 */
	public boolean verifyFOCForDirectPODomesticSuplrMaterialItemSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L49"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("DomesticPoFOCUDMaterialSameUOM");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigatePurchaseOrderPage();
		}
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier - Service
	 * Items with Similar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 * @return booelan flag
	 */
	public boolean verifyFOCForDirectPODomesticSuplrServiceItemSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L49"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("DomesticPoFOCUDServiceSameUOM");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigatePurchaseOrderPage();
		}
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Import Supplier - General
	 * Items with Similar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 * @return booelan flag
	 */
	public boolean verifyFOCForDirectPOImportSuplrGeneralItemSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L49"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("ImportPoFOCUDGeneralSameUOM");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigatePurchaseOrderPage();
		}
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Import Supplier - Material
	 * Items with Similar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 * @return booelan flag
	 */
	public boolean verifyFOCForDirectPOImportSuplrMaterialItemSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L49"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("ImportPoFOCUDMaterialSameUOM");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigatePurchaseOrderPage();
		}
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Import Supplier - Service
	 * Items with Similar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 * @return booelan flag
	 */
	public boolean verifyFOCForDirectPOImportSuplrServiceItemSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L49"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("ImportPoFOCUDServiceSameUOM");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		commonFunc.navigatePurchaseOrderPage();
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier - General
	 * Items with Dissimilar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 * @return booelan flag
	 */
	public boolean verifyFOCForDirectPODomesticSuplrGeneralItemDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0001"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("DomesticPoFOCUDGeneralDiffUOM");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigatePurchaseOrderPage();
		}
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier - Material
	 * Items with Dissimilar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 * @return booelan flag
	 */
	public boolean verifyFOCForDirectPODomesticSuplrMaterialItemDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0001"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("DomesticPoFOCUDMaterialDiffUOM");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigatePurchaseOrderPage();
		}
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Import Supplier - General
	 * Items with Dissimilar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 * @return booelan flag
	 */
	public boolean verifyFOCForDirectPOImportSuplrGeneralItemDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0001"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("ImportPoFOCUDGeneralDiffUOM");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigatePurchaseOrderPage();
		}
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Import Supplier - Material
	 * Items with Dissimilar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 * @return booelan flag
	 */
	public boolean verifyFOCForDirectPOImportSuplrMaterialItemDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0001"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("ImportPoFOCUDMaterialDiffUOM");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigatePurchaseOrderPage();
		}
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify From PR to PO - Domestic Supplier - General Items
	 * with Similar Stock and Supplier UOM Free of Cost
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyFromPRtoPOFOCForDomesticSuplrGeneralItemSimilarUOM() {
		flag = POfunc.verifyFromPRtoPOWithMultipleItemInExcelSheetCustomizedForFOC(
				webDB.getDataFromProperties("Purchase-FocFileName"),
				webDB.getDataFromProperties("DomesticPRPoFOCUDGeneralSameUOM"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("PR_General"), commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		return flag;
	}

	/**
	 * This method used to verify From PR to PO - Domestic Supplier - Material Items
	 * with Similar Stock and Supplier UOM Free of Cost
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyFromPRtoPOFOCForDomesticSuplrMaterialItemSimilarUOM() {
		flag = POfunc.verifyFromPRtoPOWithMultipleItemInExcelSheetCustomizedForFOC(
				webDB.getDataFromProperties("Purchase-FocFileName"),
				webDB.getDataFromProperties("DomesticPRPoFOCUDMaterialSameUOM"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		return flag;
	}

	/**
	 * This method used to verify From PR to PO - Domestic Supplier - Service Items
	 * with Similar Stock and Supplier UOM Free of Cost
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyFromPRtoPOFOCForDomesticSuplrServiceItemSimilarUOM() {
		flag = POfunc.verifyFromPRtoPOWithMultipleItemInExcelSheetCustomizedForFOC(
				webDB.getDataFromProperties("Purchase-FocFileName"),
				webDB.getDataFromProperties("DomesticPRPoFOCUDServiceSameUOM"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("PR_Service"), commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		return flag;
	}

	/**
	 * This method used to verify From PR to PO - Import Supplier - General Items
	 * with Similar Stock and Supplier UOM Free of Cost
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyFromPRtoPOFOCForImportSuplrGeneralItemSimilarUOM() {
		flag = POfunc.verifyFromPRtoPOWithMultipleItemInExcelSheetCustomizedForFOC(
				webDB.getDataFromProperties("Purchase-FocFileName"),
				webDB.getDataFromProperties("ImportPRPoFOCUDGeneralSameUOM"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("PR_General"), commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"));
		return flag;
	}

	/**
	 * This method used to verify From PR to PO - Import Supplier - Material Items
	 * with Similar Stock and Supplier UOM Free of Cost
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyFromPRtoPOFOCForImportSuplrMaterialItemSimilarUOM() {
		flag = POfunc.verifyFromPRtoPOWithMultipleItemInExcelSheetCustomizedForFOC(
				webDB.getDataFromProperties("Purchase-FocFileName"),
				webDB.getDataFromProperties("ImportPRPoFOCUDMaterialSameUOM"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"));
		return flag;
	}

	/**
	 * This method used to verify From PR to PO - Import Supplier - Service Items
	 * with Similar Stock and Supplier UOM Free of Cost
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyFromPRtoPOFOCForImportSuplrServiceItemSimilarUOM() {
		flag = POfunc.verifyFromPRtoPOWithMultipleItemInExcelSheetCustomizedForFOC(
				webDB.getDataFromProperties("Purchase-FocFileName"),
				webDB.getDataFromProperties("ImportPRPoFOCUDServiceSameUOM"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("PR_Service"), commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"));
		return flag;
	}

	/**
	 * This method used to verify From PR to PO - Domestic Supplier - General Items
	 * with Dissimilar Stock and Supplier UOM Free of Cost
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyFromPRtoPOFOCForDomesticSuplrGeneralItemDissimilarUOM() {
		flag = POfunc.verifyFromPRtoPOWithMultipleItemInExcelSheetCustomizedForFOC(
				webDB.getDataFromProperties("Purchase-FocFileName"),
				webDB.getDataFromProperties("DomesticPRPoFOCUDGeneralDiffUOM"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("PR_General"), commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		return flag;
	}

	/**
	 * This method used to verify From PR to PO - Domestic Supplier - Material Items
	 * with Dissimilar Stock and Supplier UOM Free of Cost
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyFromPRtoPOFOCForDomesticSuplrMaterialItemDissimilarUOM() {
		flag = POfunc.verifyFromPRtoPOWithMultipleItemInExcelSheetCustomizedForFOC(
				webDB.getDataFromProperties("Purchase-FocFileName"),
				webDB.getDataFromProperties("DomesticPRPoFOCUDMaterialDiffUOM"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		return flag;
	}

	/**
	 * This method used to verify From PR to PO - Import Supplier - General Items
	 * with Dissimilar Stock and Supplier UOM Free of Cost
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyFromPRtoPOFOCForImportSuplrGeneralItemDissimilarUOM() {
		flag = POfunc.verifyFromPRtoPOWithMultipleItemInExcelSheetCustomizedForFOC(
				webDB.getDataFromProperties("Purchase-FocFileName"),
				webDB.getDataFromProperties("ImportPRPoFOCUDGeneralDiffUOM"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("PR_General"), commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"));
		return flag;
	}

	/**
	 * This method used to verify From PR to PO - Import Supplier - Material Items
	 * with Dissimilar Stock and Supplier UOM Free of Cost
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyFromPRtoPOFOCForImportSuplrMaterialItemDissimilarUOM() {
		flag = POfunc.verifyFromPRtoPOWithMultipleItemInExcelSheetCustomizedForFOC(
				webDB.getDataFromProperties("Purchase-FocFileName"),
				webDB.getDataFromProperties("ImportPRPoFOCUDMaterialDiffUOM"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"));
		return flag;
	}
}
