package com.roadmapERP;

/**
 * @author Harikrishna - RMITS
 * @company RoadMap IT Solutions Oct 08, 2025
 */
import static org.testng.Assert.assertTrue;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.ShareOfBusinessFunctions;
import commonfunctions.supplychainmanagement.StockAdjustmentFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class StockAdjustmentTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static WebDriverBase webDB = new WebDriverBase();
	static StockAdjustmentFunctions stockAdjFunc = new StockAdjustmentFunctions();

	/**
	 * create StockAdjustment
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Test(priority = 1, groups = { "StockAdjustment" }, description = " Create Stock Adjustment")
	public void CreateStockAdjustmentOB() {
		flag = stockAdjFunc.createStockAdjustmentOB();
	}

}
