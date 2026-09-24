package commonlocators.qualitycontrol;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks October 27, 2025
 */
public class InspectionPlanLocators {
	/** @locatorType Xpath */
	public static final String AddIconInFindPage = "//button[@title='Add']";
	/** @locatorType Xpath */
	public static final String CreateInspectionIframe = "//iframe[contains(@title,'Create')]";
	/** @locatorType Xpath */
	public static final String CreateInprocessButton = "//span[text()='TSA/In Process Inspec.']/..";
	/** @locatorType Xpath */
	public static final String CreateFinalInspectionButton = "//button[@id='B727815822732776868']";
	/** @locatorType Xpath */
	public static final String CreatePreDispatchButton = "//button[@id='B727817440892776870']";
	/** @locatorType Xpath */
	public static final String HeaderLocationLovBtn = "//input[contains(@id,'TIPHD_PLNT_LOC_ID')]/following-sibling::button";
	/** @locatorType Xpath */
	public static final String HeaderCustomerLovBtn = "//input[contains(@id,'TIPHD_CUST_ID')]/following-sibling::button";
	/** @locatorType Xpath */
	public static final String HeaderItemIdLovBtn = "//input[contains(@id,'ITEM_ID')]/following-sibling::button";
	/** @locatorType Xpath */
	public static final String HeaderPlanNameInput = "//input[contains(@id,'TIPHD_INS_PLAN_NAME')]";
	/** @locatorType Xpath */
	public static final String HeaderPlanTypeSelect = "//select[@id='P367024201_TIPHD_ASS_TYPE']";
	/** @locatorType Xpath */
	public static final String HeaderPlanTypeSelectInProcess = "//select[@id='P367024401_TIPHD_ASS_TYPE']";
	/** @locatorType Xpath */
	public static final String HeaderReferenceInput = "//input[contains(@id,'TIPHD_REF')]";
	/** @locatorType Xpath */
	public static final String HeaderDateFromInput = "//input[contains(@id,'TIPHD_DT_FROM_input')]";
	/** @locatorType Xpath */
	public static final String HeaderDateToInput = "//input[contains(@id,'TIPHD_DT_TO_input')]";
	/** @locatorType Xpath */
	public static final String DisplayPlanNo = "//input[contains(@id,'TIPHD_INS_PLAN_NO')]";
	/** @locatorType Xpath */
	public static final String DisplayBOMNo = "//input[@id='P367024401_BOM_NO']";
	/** @locatorType Xpath */
	public static final String CreationTickButton = "//button[@title='Create']";
	/** @locatorType Xpath */
	public static final String HeaderSaveIcon = "//button[@id='Post']/preceding-sibling::button[@title='Save']";
	/** @locatorType Xpath */
	public static final String PostButton = "//button[@id='Post']";
	/** @locatorType Xpath */
	public static final String CancelButton = "//button[@id='B1721110404731754014']";
	/** @locatorType Xpath */
	public static final String LineAddIcon = "//div[@id='ig_line']//button[contains(@title,'Add')]";
	/** @locatorType Xpath */
	public static final String LineSaveIcon = "//div[@id='ig_line']//button[@title='Save']";
	/** @locatorType Xpath */
	public static final String CharacteristicsTableData = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[2])[1]";
	/** @locatorType Xpath */
	public static final String CharacteristicsTableLovButton = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[2])[1]//button";
	/** @locatorType Xpath */
	public static final String ModInspectionTableData = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[3])[1]";
	/** @locatorType Xpath */
	public static final String ModInspectionTableInput = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[3])[1]//input";
	/** @locatorType Xpath */
	public static final String ProcessIdTableData = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[5])[1]";
	/** @locatorType Xpath */
	public static final String ProcessIdTableLovButton = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[5])[1]//button";
	/** @locatorType Xpath */
	public static final String UOMTableData = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[5])[1]";
	/** @locatorType Xpath */
	public static final String UOMTableSelect = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[5])[1]//select";
	/** @locatorType Xpath */
	public static final String StdValTextTableData = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[6])[1]";
	/** @locatorType Xpath */
	public static final String StdValTextTableInput = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[6])[1]//input";
	/** @locatorType Xpath */
	public static final String StdOperatorData = "((//table[@data-testid='ig_line_Result_Data'])[2]/tbody//td[8])[1]";
	/** @locatorType Xpath */
	public static final String StdOperatorSelect = "((//table[@data-testid='ig_line_Result_Data'])[2]/tbody//td[8])[1]//select";
	/** @locatorType Xpath */
	public static final String SpecValueTableData = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[9])[1]";
	/** @locatorType Xpath */
	public static final String SpecValueTableInput = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[9])[1]//input";
	/** @locatorType Xpath */
	public static final String LowerMinTableData = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[12])[1]";
	/** @locatorType Xpath */
	public static final String LowerMinTableInput = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[12])[1]//input";
	/** @locatorType Xpath */
	public static final String HigherMaxTableData = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[13])[1]";
	/** @locatorType Xpath */
	public static final String HigherMaxTableInput = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[13])[1]//input";
	/** @locatorType Xpath */
	public static final String AttributeSpecTableData = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[14])[1]";
	/** @locatorType Xpath */
	public static final String AttributeSpecTableLovButton = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[14])[1]//button";
	/** @locatorType Xpath */
	public static final String NoOfSampleData = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[11])[1]";
	/** @locatorType Xpath */
	public static final String NoOfSampleInput = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[11])[1]//input";
	/** @locatorType Xpath */
	public static final String NoOfObservationData = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[12])[1]";
	/** @locatorType Xpath */
	public static final String NoOfObservationInput = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[12])[1]//input";
	/** @locatorType Xpath */
	public static final String CreateIncomingButton = "//span[text()='Incoming']/../../button";
	/** @locatorType Xpath */
	public static final String CreateInspectionFrame = "//iframe[@title='Create Inspection']";
	/** @locatorType Xpath */
	public static final String UOMTableDataInProcess = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[8])[1]";
	/** @locatorType Xpath */
	public static final String UOMTableSelectInProcess = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[8])[1]//select";
	/** @locatorType Xpath */
	public static final String StdValTextTableDataInProcess = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[9])[1]";
	/** @locatorType Xpath */
	public static final String StdValTextTableInputInProcess = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[9])[1]//input";
	/** @locatorType Xpath */
	public static final String StdOperatorDataInProcess = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[10])[1]";
	/** @locatorType Xpath */
	public static final String StdOperatorSelectInProcess = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[10])[1]//select";
	/** @locatorType Xpath */
	public static final String SpecValueTableDataInProcess = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[11])[1]";
	/** @locatorType Xpath */
	public static final String SpecValueTableInputInProcess = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[11])[1]//input";
	/** @locatorType Xpath */
	public static final String AttributeSpecTableDataInProcess = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[14])[1]";
	/** @locatorType Xpath */
	public static final String AttributeSpecTableLovButtonInProcess = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[14])[1]//button";
	/** @locatorType Xpath */
	public static final String NoOfSampleDataInProcess = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[15])[1]";
	/** @locatorType Xpath */
	public static final String NoOfSampleInputInProcess = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[15])[1]//input";
	/** @locatorType Xpath */
	public static final String NoOfObservationDataInProcess = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[16])[1]";
	/** @locatorType Xpath */
	public static final String NoOfObservationInputInProcess = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//td[16])[1]//input";
}
