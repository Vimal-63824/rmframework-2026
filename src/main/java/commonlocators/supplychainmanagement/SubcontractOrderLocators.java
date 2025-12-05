package commonlocators.supplychainmanagement;

public class SubcontractOrderLocators {

	/** @locatorType XPATH */
	public static final String FrameProdPoc = "//iframe[@title='Pend. Prod. Proc(OS)']";
	/** @locatorType XPATH */
	public static final String ProdOrderNo = "//input[@data-testid='P161713106509_FIND_PROD_ORD_NO']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String SearchButton = "//button[@data-testid='Search']";
	/** @locatorType XPATH */
	public static final String SubcontractorLovButton = "//input[@data-testid='P161713106509_SUPPLIER_ID']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String OkButton = "//button[@data-testid='Ok']";
	/** @locatorType XPATH */
	public static final String LocationId = "//span[@data-testid='P161713106501_POH_PLNT_LOC_ID']";
	/** @locatorType XPATH */
	public static final String UnitId = "//span[@data-testid='P161713106501_POH_PLANT']";
	/** @locatorType XPATH */
	public static final String SupplierName = "//span[@id='P161713106501_POH_SUPLR_NAME_DISPLAY']";
	/** @locatorType XPATH */
	public static final String MRPButton = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[1]/a";
	/** @locatorType XPATH */
	public static final String FrameMaterial = "//iframe[@title='Material']";
	/** @locatorType XPATH */
	public static final String Item = "//div[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[3]";
	/** @locatorType XPATH */
	public static final String Uom = "//div[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[6]";
	/** @locatorType XPATH */
	public static final String TarProcess = "//div[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[14]";
	/** @locatorType XPATH */
	public static final String CloseButton = "//h1[text()='Material']/../button";
	/** @locatorType XPATH */
	public static final String ScoNumber = "//span[@data-testid='P161713106501_POH_ORDER_NO']";
	/** @locatorType XPATH */
	public static final String FrameDetails = "//iframe[@title='Details']";
	/** @locatorType XPATH */
	public static final String SourceSFCode = "//input[@data-testid='P161713106504_POL_SF_CODE']";
	/** @locatorType XPATH */
	public static final String TargetSFCode = "//*[@data-testid='P161713106504_POL_TARG_SF_CODE']";
	/** @locatorType XPATH */
	public static final String TarProcessDetails = "//span[@data-testid='P161713106504_POL_TAR_PROC_DESC']";
	/** @locatorType XPATH */
	public static final String UnitCost = "//span[@data-testid='P161713106504_POL_SC_UNIT_COST']";
	/** @locatorType XPATH */
	public static final String CloseDetails = "//h1[text()='Details']/../button";
	/** @locatorType XPATH */
	public static final String LineDetails = "(//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td/a)[2]";
	/** @locatorType XPATH */
	public static final String OrderedQuantity = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[7]";
	/** @locatorType XPATH */
	public static final String LineUnitCost = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[8]";
	/** @locatorType XPATH */
	public static final String ConvFactor = "//div[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[7]";
	/** @locatorType XPATH */
	public static final String BatchQuantity = "//div[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[5]";
	/** @locatorType XPATH */
	public static final String ProcessQuantity = "//div[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[11]";
	/** @locatorType XPATH */
	public static final String BatchCost = "//div[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[6]";
	/** @locatorType XPATH */
	public static final String AssembleValue = "//div[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[14]";
	/** @locatorType XPATH */
	public static final String GLAccNo = "//div[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[20]";
	/** @locatorType XPATH */
	public static final String GLAccNoLovButton = "//input[@data-testid='SCOP_PUR_ACCT']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String IncludeCharges = "//button[@data-testid='Include_Charge']";
	/** @locatorType XPATH */
	public static final String Save = "//button[@data-testid='SAVE']";
	/** @locatorType XPATH */
	public static final String HeaderPost = "//button[@data-testid='Entry_Completed']";
	/** @locatorType XPATH */
	public static final String ScoNo = "//input[@data-testid='P16171310651_DOC_NO']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String ScoSearchButton = "//button[@data-testid='Find_Lines']";
	/** @locatorType XPATH */
	public static final String TaxPercentage = "//div[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[13]";
	/** @locatorType XPATH */
	public static final String IgstValue = "//div[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[15]";

	/** @locatorType XPATH */
	public static final String ApprovedStatus(String status) {
		return "//a[text()='" + status + "']/../../td/div[contains(@style,'green')]";
	}

	/** @locatorType XPATH */
	public static final String CheckBox(String PONumber) {
		return "//table[@id='6864046438135183543_orig']//a[text()='" + PONumber + "']/../../td[13]";
	}

	/** @locatorType XPATH */
	public static final String CgstValue = "//div[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[16]";
	/** @locatorType XPATH */
	public static final String SgstValue = "//div[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[17]";
	/** @locatorType XPATH */
	public static final String LineSave = "//button[@data-testid='Active']";
	/** @locatorType XPATH */
	public static final String HsnTable = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[9]";
	/** @locatorType XPATH */
	public static final String HsnLovButton = "//input[@data-testid='POL_HSN_CODE']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String TickButton = "//button[@data-testid='Save_1']";
	/** @locatorType XPATH */
	public static final String ProcessName = "//table[@id='5935146900431362256_orig']/tbody/tr[2]/td[10]";
	/** @locatorType XPATH */
	public static final String QueueQty = "//table[@id='5935146900431362256_orig']/tbody/tr[2]/td[11]";
	/** @locatorType XPATH */
	public static final String CompQty = "(//table[@id='5935146900431362256_orig']/tbody/tr[2]/td/input)[1]";
	/** @locatorType XPATH */
	public static final String OutsideQty = "//table[@id='5935146900431362256_orig']/tbody/tr[2]/td[15]";
	/** @locatorType XPATH */
	public static final String CreateSoc = "//button[@data-testid='CREATE_SCO']";
	/** @locatorType XPATH */
	public static final String Open_SCO_Find_AddIcon = "//button[@data-testid='Add']";
	/** @locatorType XPATH */
	public static final String OpenSCO_LocID_Input = "//input[@data-testid='P193151010_SPRCHD_PLNT_LOC_ID']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String Select_Type_field = "//select[@data-testid='P193151010_SPRCHD_LVL_TYPE']";
	/** @locatorType XPATH */
	public static final String OpenSCO_ItemID_Input = "//input[@data-testid='P193151010_SPRCHD_PROD_ID']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String OpenSCO_Supl_Input = "//input[@data-testid='P193151010_SPRCHD_SUPLR_ID']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String Open_SCO_FROMDATE_Field = "//*[@id=\"P193151011_SPRCHD_EFF_FROM_input\"]";
	/** @locatorType XPATH */
	public static final String Open_SCO_FROMDATE_input = "//*[@id=\"P193151011_SPRCHD_EFF_FROM_dialog\"]/div/div[3]/div/button";
	/** @locatorType XPATH */
	public static final String Open_SCO_ToDATE_input = "//input[@id='P193151011_SPRCHD_EFF_TO_input']";
	/** @locatorType XPATH */
	public static final String OpenScoCreate_TickIcon = "//button[@data-testid='Create']";
	/** @locatorType XPATH */
	public static final String OpenSCO_NoteAlert_Close = "(//button[@title='Close'])[1]";
	/** @locatorType XPATH */
	public static final String OpenPriceDocNO = "//span[@data-testid='P1931510101_SPRCHD_PO_NO']";
	/** @locatorType XPATH */
	public static final String OP_LineAdd_Icon = "//button[@data-testid='ADD_PROC']";
	/** @locatorType XPATH */
	public static final String OpenSCO_Proc_Input = "//input[@data-testid='SPRCLN_PROC_ID']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String OP_BatchQty_Input = "//input[@data-testid='SPRCLN_BATCH_QTY']";
	/** @locatorType XPATH */
	public static final String OP_BatchCost_Input = "//input[@data-testid='SPRCLN_UNIT_COST']";
	/** @locatorType XPATH */
	public static final String OP_LineWise_Save = "//button[@data-testid='SAVE_PROC']";
	/** @locatorType XPATH */
	public static final String OP_Post_Btn = "//button[@data-testid='POST']";
	/** @locatorType XPATH */
	public static final String OP_Forward_Ok_Btn = "//button[@ID='B1615685367453460714']";
	/** @locatorType XPATH */
	public static final String OP_Find_DocNO = "//input[@data-testid='P193151010_FIND_PO_NO']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String OP_Find_Search_Btn = "//button[@ID='B2159422498365147350']";
	/** @locatorType XPATH */
	public static final String OP_DrillDown = "//*[@id=\"2405795063194951027_orig\"]/tbody/tr[2]/td[5]/a";
	/** @locatorType XPATH */
	public static final String OP_Reverse_Btn = "//button[@data-testid='REVERSE']";
	/** @locatorType XPATH */
	public static final String OP_Reverse_CloseDate_Btn = "(//button[@class='a-Button a-Button--calendar'])[1]";
	/** @locatorType XPATH */
	public static final String OP_Reverse_CloseDate_input = "(//button[@class='a-Button a-DatePicker--today'])[1]";
	/** @locatorType XPATH */
	public static final String OP_Reverse_FromDate_input = "(//button[@class='a-Button a-DatePicker--today'])[2]";
	/** @locatorType XPATH */
	public static final String OP_Reverse_ToDate_input = "(//button[@class='a-Button a-DatePicker--today'])[3]";
	/** @locatorType XPATH */
	public static final String OP_Reverse_OK_Btn = "//button[@data-testid='OK']";
	/** @locatorType XPATH */
	public static final String OP_Reverse_ToDate = "//input[@ID='P1931510101_EFF_TO_input']";
	/** @locatorType XPATH */
	public static final String OP_AfterReverse_NewDoc_NoteAlert = "//*[@id=\"t_Alert_Success\"]/div/div[2]/div/h6";
	/** @locatorType XPATH */
	public static final String OP_Cancel_Btn = "//button[@data-testid='CANCEL']";
	/** @locatorType XPATH */
	public static final String OP_Iframe = "//iframe[@title='Open SCO Price']";
	/** @locatorType XPATH */
	public static final String SubcontractFindAddIcon = "//button[@data-testid='Add']";
	/** @locatorType XPATH */
	public static final String SubcontractFindIframe = "//iframe[@title='Subcontract Order']";
	/** @locatorType XPATH */
	public static final String SubcontractLocId = "//input[@data-testid='P16171310651_POH_PLNT_LOC_ID']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String SubcontractSupplierId = "//input[@data-testid='P16171310651_POH_SUPLR_ID']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String SubcontractCreateTickIcon = "//button[@data-testid='Create_PO_Form']";
	/** @locatorType XPATH */
	public static final String SCO_NoteAlertMsg = "(//button[@title='Close'])[1]";
	/** @locatorType XPATH */
	public static final String SubcontractDocNO = "//span[@data-testid='P161713106501_POH_ORDER_NO']";
	/** @locatorType XPATH */
	public static final String SubcontractlineAddIcon = "//button[@data-testid='CREATE_1']";
	/** @locatorType XPATH */
	public static final String SubcontractItemId = "//input[@data-testid='POL_PROD_REV_DUM']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String SubcontractQtyField = "//*[@id=\"ig_line_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[7]";
	/** @locatorType XPATH */
	public static final String SubcontractQtyInput = "//input[@data-testid='POL_ORDERED_QTY']";
	/** @locatorType XPATH */
	public static final String SubcontractLineSaveIcon = "//button[@data-testid='Save_1']";
	/** @locatorType XPATH */
	public static final String SubcontractProcessAddIcon = "//button[@data-testid='CREATE_3']";
	/** @locatorType XPATH */
	public static final String SubcontractProcessId = "//input[@data-testid='SCOP_PROCESS']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String SubcontractProcessSaveIcon = "//button[@data-testid='Active']";
	/** @locatorType XPATH */
	public static final String SubcontractGenMRPBtn = "//button[@data-testid='Gen_MRP']";
	/** @locatorType XPATH */
	public static final String SubcontractInclCostButton = "//button[@data-testid='Include_Charge']";
	/** @locatorType XPATH */
	public static final String SubcontractPostButton = "//button[@data-testid='Entry_Completed']";
	/** @locatorType XPATH */
	public static final String Subcontract_Pending_Icon = "//button[@data-testid='Create_SCO']";
	/** @locatorType XPATH */
	public static final String Pending_CreateSCO_Button = "//button[@data-testid='CREATE_SCO']";
	/** @locatorType XPATH */
	public static final String MaterialReq_AddIcon = "//button[@data-testid='Add_MR']";
	/** @locatorType XPATH */
	public static final String PendingMIV = "//button[@data-testid='create_issuance']";
	/** @locatorType XPATH */
	public static final String PendingCreateMIV = "//button[@id='B535610713637463114']";
	/** @locatorType XPATH */
	public static final String GRNSCO_Pending_Icon = "//button[@data-testid='Pending_PO_SCO']";
	/** @Locator XPATH */
	public static final String WorkApprovalFrame = "//iframe[@title='Approval']";
	/** @Locator XPATH */
	public static final String ForwardApprovalLov = "//*[@id='P236131090_FWD_PERSON_lov_btn']";
	/** @Locator XPATH */
	public static final String OkButtonForward = "//BUTTON[@ID='B479819303601188310']";

	/** @Locator XPATH */
	public static final String BatchQtyField(int i) {
		return "//*[@id='PROC_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[4]";
	}

	/** @Locator XPATH */
	public static final String BatchCostField(int i) {
		return "//*[@id='PROC_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[5]";
	}

	/** @Locator XPATH */
	public static final String SCO_Vou_Type_lov = "//input[@data-testid='P16171310651_POH_TYPE']/following-sibling::button";
	/** @Locator XPATH */
	public static final String ValueAdditionPfx = "//input[@data-testid='P16171310651_POH_ORDER_PFX_1']/following-sibling::button";
	/** @Locator XPATH */
	public static final String ProcessGLacct_lov = "//input[@data-testid='SCOP_PUR_ACCT']/following-sibling::button";
	/** @Locator XPATH */
	public static final String GLacctField = "//*[@id=\"ig_line4_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[20]";
	/** @Locator XPATH */
	public static final String SCO_Cancel_Btn = "//button[@data-testid='Cancel']";
	/** @Locator XPATH */
	public static final String SCO_LineDetail_Btn = "//span[@class='fa fa-list']";
	/** @Locator XPATH */
	public static final String SCO_SO_Ref_Lov = "//input[@data-testid='P161713106504_POL_SO_SCHLD_DESC']/following-sibling::button";
	/** @Locator XPATH */
	public static final String SCO_LineDet_Save_Icon = "//button[@data-testid='Save']";
	/** @Locator XPATH */
	public static final String SCO_LocID = "//span[@data-testid='P1931510101_SPRCHD_PLNT_LOC_ID']";
	/** @Locator XPATH */
	public static final String SCO_Unit = "//span[@data-testid='P1931510101_SPRCHD_PLNT']";
	/** @Locator XPATH */
	public static final String SCO_DocNO = "//span[@data-testid='P1931510101_SPRCHD_PO_NO']";
	/** @Locator XPATH */
	public static final String SCO_Pfx = "//input[@data-testid='P1931510101_SPRCHD_PO_PFX']";
	/** @Locator XPATH */
	public static final String SCO_Type = "//span[@data-testid='P1931510101_SPRCHD_LVL_TYPE']";
	/** @Locator XPATH */
	public static final String SCO_Item = "//span[@data-testid='P1931510101_SPRCHD_PROD_ID']";
	/** @Locator XPATH */
	public static final String SCO_SupplierID = "//span[@data-testid='P1931510101_SPRCHD_SUPLR_ID']";
	/** @Locator XPATH */
	public static final String SCO_Status = "//span[@data-testid='P1931510101_SPRCHD_STATUS']";
	/** @Locator XPATH */
	public static final String OSP_ProcessField = "//*[@id=\'PROC_ig_grid_vc\']/div[3]/div[3]/table/tbody/tr/td[2]";
	/** @Locator XPATH */
	public static final String OSP_UOMField = "//*[@id=\'PROC_ig_grid_vc\']/div[3]/div[3]/table/tbody/tr/td[3]";
	/** @Locator XPATH */
	public static final String OSP_BatchqtyField = "//*[@id=\'PROC_ig_grid_vc\']/div[3]/div[3]/table/tbody/tr/td[4]";
	/** @Locator XPATH */
	public static final String OSP_BatchCostField = "//*[@id=\'PROC_ig_grid_vc\']/div[3]/div[3]/table/tbody/tr/td[5]";
	/** @Locator XPATH */
	public static final String OSP_UOM = "//select[@data-testid='SPRCLN_UOM']";
	/** @locatorType XPATH */
	public static final String OpenSCO_Proc_Field = "//input[@data-testid='SPRCLN_PROC_ID']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String SCO_Header_LocID = "//span[@data-testid='P161713106501_POH_PLNT_LOC_ID']";
	/** @locatorType XPATH */
	public static final String SCO_Header_Pfx = "//span[@data-testid='P161713106501_POH_ORDER_PFX']";
	/** @locatorType XPATH */
	public static final String SCO_Header_DocNo = "//span[@data-testid='P161713106501_POH_ORDER_NO']";
	/** @locatorType XPATH */
	public static final String SCO_Header_Type = "//span[@data-testid='P161713106501_POH_TYPE']";
	/** @locatorType XPATH */
	public static final String SCO_Header_SuplID = "//span[@data-testid='P161713106501_POH_SUPLR_ID']";
	/** @locatorType XPATH */
	public static final String SCO_Header_SuplDesc = "//span[@id='P161713106501_POH_SUPLR_NAME_DISPLAY']";
	/** @locatorType XPATH */
	public static final String SCO_Header_Curcy = "//span[@id='P161713106501_POH_CURRENCY_DISPLAY']";
	/** @locatorType XPATH */
	public static final String SCO_Header_ExRate = "//input[@id='P161713106501_POH_EXCHANGE_RAT']";
	/** @locatorType XPATH */
	public static final String SCO_Header_PurAmount = "//input[@id='P161713106501_PUR_AMT']";
	/** @locatorType XPATH */
	public static final String SCO_Header_TaxAmount = "//input[@id='P161713106501_TAX_AMT_D']";
	/** @locatorType XPATH */
	public static final String SCO_Header_TotalAmount = "//input[@id='P161713106501_POH_TOT_AMT']";
	/** @locatorType XPATH */
	public static final String SCO_Header_Status = "//input[@id='P161713106501_STATUS']";
	/** @locatorType XPATH */
	public static final String SCO_Item_Field = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[3]";
	/** @locatorType XPATH */
	public static final String SCO_Qty_Field = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[7]";
	/** @locatorType XPATH */
	public static final String SCO_UnitCost_Field = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[8]";
	/** @locatorType XPATH */
	public static final String SCO_HSN_Field = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[9]";
	/** @locatorType XPATH */
	public static final String SCO_ProcessDesc_Field = "//*[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[3]";
	/** @locatorType XPATH */
	public static final String SCO_ProcessUOM_Field = "//*[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[4]";
	/** @locatorType XPATH */
	public static final String SCO_Batchqty_Field = "//*[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[5]";
	/** @locatorType XPATH */
	public static final String SCO_Batchcost_Field = "//*[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[6]";
	/** @locatorType XPATH */
	public static final String SCO_ConvFactor_Field = "//*[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[7]";
	/** @locatorType XPATH */
	public static final String SCO_JobItem_Field = "//*[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[8]";
	/** @locatorType XPATH */
	public static final String SCO_JobItemDesc_Field = "//*[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[9]";
	/** @locatorType XPATH */
	public static final String SCO_SACcode_Field = "//*[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[10]";
	/** @locatorType XPATH */
	public static final String SCO_ProcessQty_Field = "//*[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[11]";
	/** @locatorType XPATH */
	public static final String SCO_ProcessUnitcost_Field = "//*[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[12]";
	/** @locatorType XPATH */
	public static final String SCO_ProcessTax_Field = "//*[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[13]";
	/** @locatorType XPATH */
	public static final String SCO_ProcessAsblvalue_Field = "//*[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[14]";
	/** @locatorType XPATH */
	public static final String SCO_ProcessIGSTamt_Field = "//*[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[15]";
	/** @locatorType XPATH */
	public static final String SCO_ProcessCGSTamt_Field = "//*[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[16]";
	/** @locatorType XPATH */
	public static final String SCO_ProcessSGSTamt_Field = "//*[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[17]";
	/** @locatorType XPATH */
	public static final String SCO_ProcessUTGSTamt_Field = "//*[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[18]";
	/** @locatorType XPATH */
	public static final String SCO_ProcessCESSTamt_Field = "//*[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[19]";
	/** @locatorType XPATH */
	public static final String SCO_ContrPfx_Field = "//*[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[25]";
	/** @locatorType XPATH */
	public static final String SCO_ContrDocNO_Field = "//*[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[26]";
	/** @locatorType XPATH */
	public static final String SCO_MaterialType_Field = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[1]";
	/** @locatorType XPATH */
	public static final String SCO_MaterialWHdesc_Field = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[2]";
	/** @locatorType XPATH */
	public static final String SCO_MaterialItemID_Field = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[3]";
	/** @locatorType XPATH */
	public static final String SCO_MaterialReqQty_Field = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[12]";
	/** @locatorType XPATH */
	public static final String SCO_MaterialTarNo_Field = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[13]";
	/** @locatorType XPATH */
	public static final String SCO_MaterialTarProc_Field = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[14]";
	/** @locatorType XPATH */
	public static final String SCO_MaterialTab = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[1]/a";
	/** @locatorType XPATH */
	public static final String SCO_LineDetails_Inputtype = "//span[@data-testid='P161713106504_POL_GST_INPUT_TYPE']";
	/** @locatorType XPATH */
	public static final String SCO_LineDetails_GSTtype = "//span[@data-testid='P161713106504_POL_GST_EXEMPT_FLAG']";
	/** @locatorType XPATH */
	public static final String SCO_LineDetails_ProdNo = "//span[@data-testid='P161713106504_POL_PROD_ORD_NO']";
	/** @locatorType XPATH */
	public static final String SCO_LineDetails_SouSFcode = "//input[@data-testid='P161713106504_POL_SF_CODE']";
	/** @locatorType XPATH */
	public static final String SCO_LineDetails_TarSFcode = "//span[@data-testid='P161713106504_POL_TARG_SF_CODE']";
	/** @locatorType XPATH */
	public static final String SCO_LineDetails_Tar_OprnNo = "//span[@data-testid='P161713106504_POL_TAR_OPRN_SEQ']";
	/** @locatorType XPATH */
	public static final String SCO_LineDetails_Tar_OprnProc = "//span[@data-testid='P161713106504_POL_TAR_PROC_DESC']";
	/** @locatorType XPATH */
	public static final String SCO_LineDetails_Sou_OprnNo = "//span[@data-testid='P161713106504_POL_SOU_OPRN_SEQ']";
	/** @locatorType XPATH */
	public static final String SCO_LineDetails_Sou_OprnProc = "//span[@data-testid='P161713106504_POL_SOU_PROC_DESC']";
	/** @locatorType XPATH */
	public static final String SCO_LineDetails_SaleOrderRefNo = "//input[@data-testid='P161713106504_POL_SO_SCHLD_DESC']";
	/** @locatorType XPATH */
	public static final String SCO_LineDetails_WH_ID = "//span[@data-testid='P161713106504_POL_STORE_ID']";
	/** @locatorType XPATH */
	public static final String SCO_LineDetails_WH_Desc = "//span[@data-testid='P161713106504_POL_STORE_NAME']";
	/** @locatorType XPATH */
	public static final String SCO_LineDetails_CS_Qty = "//input[@data-testid='P161713106504_POL_CLS_QTY']";
	/** @locatorType XPATH */
	public static final String SCO_LineDetails_Status = "//span[@data-testid='P161713106504_POL_STATUS']";
	/** @locatorType XPATH */
	public static final String SCO_Location_Details = "//button[@data-testid='Edit_Location']";
	/** @locatorType XPATH */
	public static final String SCO_Location_Details_Frame = "//iframe[@title='Location']";
	/** @locatorType XPATH */
	public static final String SCO_Location_Details_GSTtype = "//Select[@data-testid='P1615131060412_POH_GST_TYPE_1']";
	/** @locatorType XPATH */
	public static final String SCO_Location_Details_PurGSTClstype = "//Select[@data-testid='P1615131060412_POH_GST_CLF_TYPE_1']";
	/** @locatorType XPATH */
	public static final String SCO_Location_Details_SaveIcon = "//button[@data-testid='Save']";
	/** @Locator XPATH */
	public static final String Pend_SerialWise_Btn = "//button[@data-testid='Serialwise-Pending']";
	/** @locatorType XPATH */
	public static final String Pend_Serial_Iframe = "//iframe[@title='Serialwise Pending Outside Process']";
	/** @Locator XPATH */
	public static final String Pend_SerialWise_Prod_lovBtn = "//input[@data-testid='P19631108_SOSD_ORD_NO']/following-sibling::button";
	/** @Locator XPATH */
	public static final String Pend_SerialWise_SearchBtn = "//button[@data-testid='Find']";
	/** @locatorType XPATH */
	public static final String SerialCheckBox(String SCONumber) {
		return "//a[text()='" + SCONumber + "']/../../td[17]";
	}
	/** @Locator XPATH */
	public static final String Pend_SerialWise_CreateSCOBtn = "//button[@data-testid='Create_SCO']";
	/** @Locator XPATH */
	public static final String Pend_SerialWise_CreateSCO_SubconLOV = "//input[@data-testid='P19631108_SUPLR_ID']/following-sibling::button";
	/** @Locator XPATH */
	public static final String Pend_SerialWise_CreateSCO_OKBtn = "//button[@data-testid='OK']";
}
