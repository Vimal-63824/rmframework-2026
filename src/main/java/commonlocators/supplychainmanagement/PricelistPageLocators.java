package commonlocators.supplychainmanagement;

import commonfunctions.CommonFunctions;

/**
 * @author Durai
 * @company Roadmap May 02, 2025
 */
public class PricelistPageLocators {
	/** @locatorType Xpath */
	public static final String AddPriceList = "//button[contains(@data-testid,'Add_1')]";
	/** @locatorType Xpath */
	public static final String FindSearchButton = "//button[contains(@data-testid,'Find')]";
	/** @locatorType Xpath */
	public static final String LocationLov = "//input[@data-testid='P1615131025212_PSPL_PLNT_LOC_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String LocationSearch = "//input[@class='a-PopupLOV-search apex-item-text']";
	/** @locatorType Xpath */
	public static final String SuppliernameLov = "//input[@data-testid='P1615131025212_PSPL_SUPLR_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ItemLov = "//input[@data-testid='P1615131025212_PROD_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ItemSearch = "//div[@class='a-PopupLOV-searchBar a-PopupLOV--incremental']";
	/** @locatorType Xpath */
	public static final String PriceField = "//input[@data-testid='P1615131025212_PSPL_UNIT_COST']";
	/** @locatorType Xpath */
	public static final String EffFrom = "//input[@id='P1615131025214_PSPL_EFF_DATE_FROM_input']";
	/** @locatorType Xpath */
	public static final String EffTo = "//input[@id='P1615131025214_PSPL_EFF_DATE_TO_input']";
	/** @locatorType Xpath */
	public static final String FindCreateButton = "//button[@data-testid='Create20']";
	/** @locatorType Xpath */
	public static final String DiscountField = "//table[@class='a-GV-table']/tbody/tr/td[14]";
	/** @locatorType Xpath */
	public static final String Discountinput = "//input[@data-testid='PSPL_DISC_PCT']";
	/** @locatorType Xpath */
	public static final String SAVEBtn = "//button[@data-testid='save']";
	/** @locatorType Xpath */
	public static final String ActiveButton = "//td[contains(@class,'a-GV-cell u-tC is-readonly')]";
	/** @locatorType Xpath */
	public static final String PriceListWorkflowFrame = "//iframe[@title='Workflow Approval']";
	/** @locatorType Xpath */
	public static final String PricelistForwardlov = "//input[@data-testid='P236131090_FWD_PERSON']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PricelistworkflowOK = "//button[@data-testid='Entry']";
	/** @locatorType Xpath */
	public static final String Pricelist_HeaderLoc = "//td[@class='a-GV-cell u-tS is-readonly']";
	/** @locatorType Xpath */
	public static final String Pricelist_HeaderSupplier = "//td[@class='a-GV-cell u-tS rfield'][2]";
	/** @locatorType Xpath */
	public static final String Pricelist_HeaderItem = "//td[@class='a-GV-cell u-tS rfield'][3]";
	/** @locatorType Xpath */
	public static final String PricelistDocNum = "//table[@class='a-GV-table']/tbody/tr/td[4]";
	/** @locatorType Xpath */
	public static final String Successmsg = "//*[@id=\"t_Alert_Success\"]/div/div[2]/div/h6";
	/** @locatorType Xpath */
	public static final String StopAlert = "//li[@class='a-Notification-item htmldbStdErr']";
	/** @locatorType Xpath */
	public static final String CancellButton = "//*[@id=\"price_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr/td[22]/a";
	/** @locatorType Xpath */
	public static final String RefindButton = "//button[@data-testid='Find_Record']";
	/** @locatorType Xpath */
	public static final String find = "//button[@data-testid='Search']";
	/** @locatorType Xpath */
	public static final String Refindsearch = "//input[@id='PLE_search_field']";
	/** @locatorType Xpath */
	public static final String Refindsearchok = "//*[@id=\"PLE_search_button\"]/span";
	/** @locatorType Xpath */
	public static final String CancellStatus = "//table[contains(@aria-label,'Result(s)')]/tbody/tr/td[22]";
	/** @locatorType Xpath */
	public static final String FindLocLov = "//input[@data-testid='P1615131025212_LOCATION']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindUnitLov = "//input[@data-testid='P1615131025212_UNIT']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindItemLov = "//input[@data-testid='P1615131025212_ITEM']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindClearButton = "//button[@data-testid='Clear']";
	/** @locatorType Xpath */
	public static final String FindSupplierlov = "//input[@data-testid='P1615131025212_SUPPLIER']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindEfffromDate = "//input[@id='P1615131025212_DATE_FROM_input']";
	/** @locatorType Xpath */
	public static final String FindEffToDate = "//input[@id='P1615131025212_DATE_TO_input']";
	/** @locatorType Xpath */
	public static final String FindItemDesc = "//input[@data-testid='P1615131025212_ITEM_DESC']";
	/** @locatorType Xpath */
	public static final String FindStatusField = "//select[@data-testid='P1615131025212_STATUS']";
	/** @locatorType Xpath */
	public static final String FindDocNumFieldlov = "//input[@data-testid='P1615131025212_DOCP_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindDocNumsearchField = "//*[@id=\"PopupLov_1615131025212_P1615131025212_DOCP_NO_dlg\"]/div[1]/input";
	/** @locatorType Xpath */
	public static final String FindDocNumDrilldown = "//table[contains(@aria-label,'Result(s)')]/tbody/tr/td[6]/a";
	/** @locatorType Xpath */
	public static final String AmendmentButton = "//*[@id=\"price_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr/td[20]/a";
	/** @locatorType Xpath */
	public static final String AmendcloseDate = "//*[@data-testid='P161513102524_CLOSE_DATE']/input";
	/** @locatorType Xpath */
	public static final String AmendFromDate = "//*[@data-testid='P161513102524_EFF_FROM_DATE']/input";
	/** @locatorType Xpath */
	public static final String AmendToDate = "//*[@data-testid='P161513102524_EFF_TO_DATE']/input";
	/** @locatorType Xpath */
	public static final String AmendReason = "//input[@data-testid='P161513102524_REASON']";
	/** @locatorType Xpath */
	public static final String PriceListAmndFrame = "//iframe[@title='Amendment']";
	/** @locatorType Xpath */
	public static final String PriceListAmndOk = "//button[@data-testid='OK']";
	/** @locatorType Xpath */
	public static final String SucessMsg = "//h6[@class='succ_msg']";
	/** @locatorType Xpath */
	public static final String CostField = "//div[@id='price_ig_grid_vc']/*[3]/*[3]/table/tbody/tr/td[13]";
	/** @locatorType Xpath */
	public static final String Costinput = "//input[@data-testid='PSPL_UNIT_COST']";
	/** @locatorType Xpath */
	public static final String PricelistBody = "//*[@id=\"R1781878780902047238\"]/div/div[1]/div/div";
	/** @locatorType Xpath */
	public static final String PricelistOverallsave = "//button[@data-testid='save']";
	/** @locatorType Xpath */
	public static final String Pricelistfind = "//button[@data-testid='Search']";
	/** @locatorType Xpath */
	public static final String PricelistNumField = "//input[@data-testid='P1615131025212_DOCP_NO']/following-sibling::button";

	/** @locatorType Xpath */
	public static final String PRLISTDetailsInfoBtn(String PRLISTNum) {
		return "//td[contains(text(),'" + PRLISTNum + "')]/../td[3]/*";
	}

	/** @locatorType Xpath */
	public static final String PRLISTSelectCheckBox(String PRLISTNum) {
		return "//td[contains(text(),'" + PRLISTNum + "')]/../td[1]/*";
	}

	/** @locatorType Xpath */
	public static final String ItemMasterAdd = "//*[@id=\"B1287636716616788407\"]/span";
	/** @locatorType Xpath */
	public static final String FindScreenItemField = "//input[@id='P3670200_ITEM_1']";
	/** @locatorType Xpath */
	public static final String ItemSearchBtn = "//button[@data-testid='Search1']";
	/** @locatorType Xpath */
	public static final String ItemFindResultTable = "//table[@data-testid='RIE_Result_Data']//tr/th";

	/** @locatorType Xpath */
	public static final String ItemDrillDownBtn() {
		return "(" + CommonFunctions.checkAndReturnLocatorForFindPageTable(ItemFindResultTable, "Item") + ")[1]//a";
	}

	/** @locatorType Xpath */
	public static final String ItemGeneral = "(//div[@aria-label='General']//button)[1]";
	/** @locatorType Xpath */
	public static final String ItemCopy = "//button[@data-testid='Copy_Item']";
	/** @locatorType Xpath */
	public static final String ItemIdInputCopy = "//input[@data-testid='P367020004_ITEM']";
	/** @locatorType Xpath */
	public static final String ItemDescInputCopy = "//input[@data-testid='P367020004_ITEM_DESC']";
	/** @locatorType Xpath */
	public static final String ItemOkCopy = "//button[@data-testid='ok']";
	/** @locatorType Xpath */
	public static final String ItemPost = "//button[@data-testid='Entry_Completed']";
	/** @locatorType Xpath */
	public static final String ItemFindBtn = "//*[@id=\"B215430562028062028\"]/span";
	/** @locatorType Xpath */
	public static final String ItemGetText = "//input[@data-testid='P367020004_PROD_ID']";
	/** @locatorType Xpath */
	public static final String ItemPostSucessMsg = "//*[@id='t_Alert_Success']/div/div[2]/div/h6";
	/** @locatorType Xpath */
	public static final String PRLForwardLov = "//button[@id='P236131090_FWD_PERSON_lov_btn']";
	/** @locatorType Xpath */
	public static final String PRLForwardOkBtn = "//button[@data-testid='Entry']";
	/** @locatorType Xpath */
	public static final String PRLFrame = "//iframe[@title='Workflow Approval']";
	/** @locatorType Xpath */
	public static final String LocId = "//input[@data-testid='P1615131025212_LOCATION']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String Unit = "//input[@data-testid='P1615131025212_UNIT']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PRLNO = "//input[@data-testid=\"P1615131025212_DOCP_NO\"]/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SupplierNameLovButton = "//input[@data-testid='P1615131025212_SUPPLIER']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ItemId = "//input[@data-testid='P1615131025212_ITEM']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ItemDesc = "//input[@data-testid='P1615131025212_ITEM_DESC']";
	/** @locatorType Xpath */
	public static final String PRL_Status = "//select[@data-testid='P1615131025212_STATUS']";
	/** @locatorType Xpath */
	public static final String PRLSearchButton = "//button[@data-testid='Find']";
	/** @locatorType Xpath */
	public static final String PRLFindTable = "//table[@data-testid='H_Result_Data']/tbody/tr[2]/td[1]";
	/** @locatorType Xpath */
	public static final String ClearButton = "//button[@data-testid='Clear']";
	/** @locatorType Xpath */
	public static final String PRLDtFrom = "//input[@id='P1615131025212_DATE_FROM_input']";
	/** @locatorType Xpath */
	public static final String PRLDtTo = "//input[@id='P1615131025212_DATE_TO_input']";

	/** @locatorType Xpath */
	public static final String PRLFindTableData(int i) {
		return "//table[@id='1457343167982292320_orig']/tbody/tr/td[" + i + "]";
	}

	/** @locatorType Xpath */
	public static final String ItemDrillDown(String DocNo) {
		return "//table[contains(@aria-label,'Result(s)')]/tbody/tr/td[2]/a[text()='" + DocNo + "']";
	}

	/** @locatorType Xpath */
	public static final String PricelistAddFrame = "//iframe[@title='Create Price List Entry ']";
	/** @locatorType Xpath */
	public static final String ItemLovButton = "//button[@id='P3670200_ITEM_1_lov_btn']";
	/** @locatorType Xpath */
	public static final String PriceListSingle1 = "//button[@id='B1347663667009762073']";
	/** @locatorType Xpath */
	public static final String CustomerIdFieldDb = "//*[@id='SO_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[4]";
	/** @locatorType Xpath */
	public static final String CancelOk = "//button[contains(text(),'OK')]";
	/** @locatorType Xpath */
	public static final String CancelSearch = "//button[@data-testid='Search']";

}
