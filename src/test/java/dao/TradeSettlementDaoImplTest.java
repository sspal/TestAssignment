package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.Constants;
import model.SettledTrade;
import model.TradeType;

public class TradeSettlementDaoImplTest  extends TestCase{
	TradeSettlementDaoImpl classtoTest;
	/**
	 * Simpledateformat instance
	 */
	
	private SimpleDateFormat format;
	@Before
	protected void setUp(){
		classtoTest = new TradeSettlementDaoImpl();
		format = new SimpleDateFormat(Constants.Date_Pattern);
	}	
	
	@Test
	public void testSaveSettledTrade() throws Exception {	
		SettledTrade trade = new SettledTrade("Cipla", getDate("21/Sep/2018"), 
				"INR", 0.014f, 1000, TradeType.BUY, 95.01);
		trade.setSettleDate(getDate("21/Sep/2018"));
		trade.setTradeTotalValue(500);
		classtoTest.saveSettledTrade(trade);
		SettledTrade trade1 = new SettledTrade("Cipla", getDate("21/Sep/2018"), 
				"INR", 0.014f, 500, TradeType.BUY, 95.01);
		trade1.setSettleDate(getDate("21/Sep/2018"));
		trade1.setTradeTotalValue(100);
		classtoTest.saveSettledTrade(trade1);
		SettledTrade trade2 = new SettledTrade("Cipla", getDate("21/Sep/2018"),
				"INR", 0.014f, 700, TradeType.SELL, 95.01);
		trade2.setSettleDate(getDate("21/Sep/2018"));
		trade2.setTradeTotalValue(1200);
		classtoTest.saveSettledTrade(trade2);
		
		SettledTrade trade3 = new SettledTrade("Cipla", getDate("24/Sep/2018"), 
				"INR", 0.014f, 700, TradeType.SELL, 95.01);
		trade3.setSettleDate(getDate("24/Sep/2018"));
		trade3.setTradeTotalValue(1100);
		classtoTest.saveSettledTrade(trade3);
		
		SettledTrade trade4 = new SettledTrade("Cipla", getDate("24/Sep/2018"), 
				"INR", 0.014f, 700, TradeType.BUY, 95.01);
		trade4.setSettleDate(getDate("24/Sep/2018"));
		trade4.setTradeTotalValue(1300);
		classtoTest.saveSettledTrade(trade4);
		
		SettledTrade trade5 = new SettledTrade("Cipla", getDate("24/Sep/2018"),  
				"INR", 0.014f, 700, TradeType.SELL, 95.01);
		trade5.setSettleDate(getDate("24/Sep/2018"));
		trade5.setTradeTotalValue(1150);
		classtoTest.saveSettledTrade(trade5);
		
		Map<Date, Map<TradeType, List<SettledTrade>>> allTradesMap = classtoTest.getSettledTrades();
		assertEquals(2, allTradesMap.size());
		assertEquals(2, allTradesMap.get(getDate("21/Sep/2018")).get(TradeType.BUY).size());
	}
	
	@Test
	public void testNullTradeSaveSettledTrade() throws Exception {
		try {
				SettledTrade trade = null;				
				classtoTest.saveSettledTrade(trade);
			}catch(Exception e) {
				assertEquals("Trade or Trade Settlement Date or Trade Type cannot be Null",e.getMessage());
			}
	}
	@Test
	public void testNullTradeTypeSaveSettledTrade() throws Exception {
		try {
				SettledTrade trade = new SettledTrade("Cipla", getDate("21/Sep/2018"),  
						"INR", 0.014f, 700, null, 95.01);
				trade.setSettleDate(getDate("23/Sep/2018"));
				trade.setTradeTotalValue(1150);	
				classtoTest.saveSettledTrade(trade);
			}catch(Exception e) {
				assertEquals("Trade or Trade Settlement Date or Trade Type cannot be Null",e.getMessage());
			}
	}
	private Date getDate(String date) throws ParseException {		
		return format.parse(date);
	}
	
	@After
	protected void tearDown(){
		classtoTest = null;
		format = null;
	}
}
