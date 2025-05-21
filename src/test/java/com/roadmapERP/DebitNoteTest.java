package com.roadmapERP;

import org.testng.Assert;
import org.testng.annotations.Test;

import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.DebitNoteFunctions;
import commonfunctions.supplychainmanagement.SalesInvoiceFunctions;
import utils.WebDriverBase;

public class DebitNoteTest extends RoadMapERPTest {

	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static WebDriverBase webDB = new WebDriverBase();
	static DebitNoteFunctions DebitNoteFunc = new DebitNoteFunctions();

//	/**
//	 * Create Debit Memo With  GRN (General)
//	 * 
//	 * @author Vimal - RMITS
//	 */
//	@Test(priority = 1, groups = { "DebitNote" }, description = "      381 - Create Debit Memo With  GRN (General)")
//	public void CreateDebitMemoWithGRNGeneral() {
//		flag = DebitNoteFunc.createDebitMemoWithGRNGeneral();
//		Assert.assertTrue(flag);
//	}
	/**
	 * Create Debit Memo With  GRN (General)
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 2, groups = { "DebitNote" }, description = "      384 - Create Debit Memo without GRN reference")
	public void CreateDebitMemoWithoutGRNreference() {
		flag = DebitNoteFunc.createDebitMemoWithoutGRNreference();
		Assert.assertTrue(flag);
	}
	/**
	 * Create Debit Memo With GRN (General)
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 3, groups = { "DebitNote" }, description = "      386 - Cancel Debit Memo document")
	public void CancelDebitMemodocument() {
		flag = DebitNoteFunc.cancelDebitMemodocument();
		Assert.assertTrue(flag);
	}
	/**
	 * Create Debit Memo With GRN (General)
	 * 
	 * @author Vimal - RMITS
	 */
	@Test(priority = 4, groups = { "DebitNote" }, description = "      385 - Cancel Debit Memo Line")
	public void CancelDebitMemoLine() {
		flag = DebitNoteFunc.cancelDebitMemoLine();
		Assert.assertTrue(flag);
	}
}
