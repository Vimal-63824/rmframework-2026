package commonfunctions;

import java.io.File;
import utils.ExcelUtils;
import utils.WebDriverBase;

public class ItemDetailsHandler {
	private static ExcelUtils excelUtils = new ExcelUtils();
	private static WebDriverBase webDB = new WebDriverBase();
	private static ThreadLocal<String[][]> PR_LineDetails = ThreadLocal.withInitial(() -> null);
	private static String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
			+ "ItemDetails.xlsx";
	private static ThreadLocal<String[][]> PO_LineDetails = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String[][]> POA_LineDetails = ThreadLocal.withInitial(() -> null);

	/**
	 * This Constructor used to set PR_LineDetails data from ItemDetails.xlsx file.
	 * 
	 * @author Sakthi-GWL
	 */
	public ItemDetailsHandler() {
		if (PR_LineDetails.get() == null) {
			PR_LineDetails.set(
					excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("PRItemDetailsSheetName")));
		}
		if (PO_LineDetails.get() == null) {
			PO_LineDetails.set(
					excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POItemDetailsSheetName")));
		}
		if (POA_LineDetails.get() == null) {
			POA_LineDetails.set(
					excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POAItemDetailsSheetName")));
		}
	}

	/**
	 * This Enum 'PR Line Details Name column Name'
	 */
	public enum PRLineDetailsName {
		ITEM_NAME, CLASS, SUBCLASS, WAREHOUSE, CONVENTION_FACTOR, PURCHASE_UOM, GL_ACC_NO, GL_ACC_DESC, CPC_CODE,
		CPC_DESC
	}

	/**
	 * This Enum 'PO Line Details Name column Name'
	 */
	public enum POLineDetailsName {
		ITEMID, ITEM_DESCRIPTION, UOM, HSN_CODE, TAX, SUBCLASS, CLASS, WH_DEPTNAME, COST_BASIS, STOCK_UOM,
		CONVERSION_FACTOR, GL_ACC_NO, GL_ACC_DESC, CPC_CODE, CPC_DESC, CONTRACT_NO, CONTRACT_PRFX, CONTRACT_AMEND_NO,
		PO_CLS_QTY, RCM_CAT_ID
	}

	/**
	 * This Enum 'POA Line Details Name column Name'
	 */
	public enum POALineDetailsName {
		ITEMID, ITEM_DESCRIPTION, UOM, HSN_CODE, WH_DEPTNAME, COST_BASIS, SUPL_UOM, CONVERSION_FACTOR, GL_ACC_NO,
		GL_ACC_DESC, CPC_CODE, CPC_DESC
	}

	/**
	 * This method used to return specific items particular Line Detail Data from
	 * Item Details Sheet.
	 * 
	 * @param itemName
	 * @param LineDetailName
	 * @return String resultData
	 */
	public String getPRLineDetailsforGivenItem(String itemName, PRLineDetailsName LineDetailName) {
		String resultData = null;
		int columnIndex = 0;
		int itemColumnIndex = 0;
		if (PR_LineDetails.get() == null || PR_LineDetails.get().length == 0) {
			PR_LineDetails.set(excelUtils.readAllDataInExcel(Filepath, "PR Details"));
		}
		String[] headers = PR_LineDetails.get()[0];
		for (int i = 0; i < headers.length; i++) {
			if (headers[i].trim().equalsIgnoreCase(LineDetailName.name().trim())) {
				columnIndex = i;
			}
			if (headers[i].trim().equalsIgnoreCase(PRLineDetailsName.ITEM_NAME.name().trim())) {
				itemColumnIndex = i;
			}
		}
		for (int i = 1; i < PR_LineDetails.get().length; i++) {
			if (PR_LineDetails.get()[i][itemColumnIndex].trim().equalsIgnoreCase(itemName.trim())) {
				resultData = PR_LineDetails.get()[i][columnIndex];
				break;
			}
		}
		return resultData;
	}

	/**
	 * get PO Line Details for Given Item
	 * 
	 * @author Deepan-GWL
	 * @param itemName
	 * @param LineDetailName
	 * @return
	 */
	public String getPOLineDetailsforGivenItem(String itemName, POLineDetailsName LineDetailName) {
		String result = null;
		int columnIndex = 0;
		int itemColumnIndex = 0;
		if (PO_LineDetails.get() == null || PO_LineDetails.get().length == 0) {
			PO_LineDetails.set(excelUtils.readAllDataInExcel(Filepath, "PO Details"));
		}
		String[] headers = PO_LineDetails.get()[0];
		for (int i = 0; i < headers.length; i++) {
			if (headers[i].trim().equalsIgnoreCase(LineDetailName.name().trim())) {
				columnIndex = i;
			}
			if (headers[i].trim().equalsIgnoreCase(POLineDetailsName.ITEMID.name().trim())) {
				itemColumnIndex = i;
			}
		}
		for (int i = 1; i < PO_LineDetails.get().length; i++) {
			if (PO_LineDetails.get()[i][itemColumnIndex].trim().equalsIgnoreCase(itemName.trim())) {
				result = PO_LineDetails.get()[i][columnIndex];
				break;
			}
		}
		return result;
	}

	/**
	 * get POA Line Details for Given Item
	 * 
	 * @author Deepan-GWL
	 * @param itemName
	 * @param LineDetailName
	 * @return
	 */
	public String getPOALineDetailsforGivenItem(String itemName, POALineDetailsName LineDetailName) {
		String result = null;
		int columnIndex = 0;
		int itemColumnIndex = 0;
		if (POA_LineDetails.get() == null || POA_LineDetails.get().length == 0) {
			POA_LineDetails.set(excelUtils.readAllDataInExcel(Filepath, "POA Details"));
		}
		String[] headers = POA_LineDetails.get()[0];
		for (int i = 0; i < headers.length; i++) {
			if (headers[i].trim().equalsIgnoreCase(LineDetailName.name().trim())) {
				columnIndex = i;
			}
			if (headers[i].trim().equalsIgnoreCase(POALineDetailsName.ITEMID.name().trim())) {
				itemColumnIndex = i;
			}
		}
		for (int i = 1; i < POA_LineDetails.get().length; i++) {
			if (POA_LineDetails.get()[i][itemColumnIndex].trim().equalsIgnoreCase(itemName.trim())) {
				result = POA_LineDetails.get()[i][columnIndex];
				break;
			}
		}
		return result;
	}

}
