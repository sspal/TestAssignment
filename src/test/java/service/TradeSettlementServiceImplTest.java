package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.TradeSettlementDaoImpl;
import junit.framework.TestCase;
import model.Constants;
import model.SettledTrade;
import model.TradeType;

public class TradeSettlementServiceImplTest  extends TestCase{
	TradeSettlementServiceImpl classtoTest;
	/**
	 * Simpledateformat instance
	 */
	
	private SimpleDateFormat format;
	@Before
	protected void setUp(){
		classtoTest = new TradeSettlementServiceImpl(new TradeSettlementDaoImpl());
		format = new SimpleDateFormat(Constants.Date_Pattern);
	}	
	
	@Test
	public void testSaveSettledTrade() throws Exception {	
		SettledTrade trade = new SettledTrade("Cipla", getDate("21/Sep/2018"), 
				"INR", 0.014f, 1000, TradeType.BUY, 95.01);		
		classtoTest.saveSettledTrade(trade);
		assertEquals(getDate("21/Sep/2018"), trade.getSettleDate());		
		
		SettledTrade trade1 = new SettledTrade("Cipla", getDate("21/Sep/2018"), 
				"AED", 0.014f, 500, TradeType.BUY, 95.01);		
		classtoTest.saveSettledTrade(trade1);
		assertEquals(getDate("23/Sep/2018"), trade1.getSettleDate());
		
	}
	@Test
	public void testGetTradeAggregate() throws Exception {	
		SettledTrade trade = new SettledTrade("Cipla", getDate("21/Sep/2018"), 
				"INR", 0.014f, 1000, TradeType.BUY, 95.01);		
		classtoTest.saveSettledTrade(trade);
				
		SettledTrade trade1 = new SettledTrade("Cipla", getDate("21/Sep/2018"), 
				"INR", 0.014f, 500, TradeType.BUY, 95.01);		
		classtoTest.saveSettledTrade(trade1);
		
		SettledTrade trade2 = new SettledTrade("ABCD", getDate("21/Sep/2018"), 
				"INR", 0.014f, 600, TradeType.SELL, 195.01);		
		classtoTest.saveSettledTrade(trade2);
		
		assertEquals( 1995.2100615855306, classtoTest.getTradeAggregate(getDate("21/Sep/2018"), TradeType.BUY));		
	}
	@Test
	public void testGetAggregateNegative() throws Exception {	
		SettledTrade trade = new SettledTrade("Cipla", getDate("21/Sep/2018"), 
				"INR", 0.014f, 1000, TradeType.BUY, 95.01);		
		classtoTest.saveSettledTrade(trade);
				
		SettledTrade trade1 = new SettledTrade("Cipla", getDate("21/Sep/2018"), 
				"INR", 0.014f, 500, TradeType.BUY, 95.01);		
		classtoTest.saveSettledTrade(trade1);		
		
		assertEquals( 0.0, classtoTest.getTradeAggregate(getDate("21/Sep/2018"), TradeType.SELL));
		
		assertEquals( 0.0, classtoTest.getTradeAggregate(getDate("22/Sep/2018"), TradeType.BUY));		
	}
	
	@Test
	public void testGetEntityRanking() throws Exception {	
		SettledTrade trade = new SettledTrade("Cipla", getDate("21/Sep/2018"), 
				"INR", 0.014f, 1000, TradeType.BUY, 95.01);		
		classtoTest.saveSettledTrade(trade);
				
		SettledTrade trade1 = new SettledTrade("Cipla", getDate("21/Sep/2018"), 
				"INR", 0.014f, 500, TradeType.BUY, 95.01);		
		classtoTest.saveSettledTrade(trade1);		
		
		SettledTrade trade2 = new SettledTrade("ABCD", getDate("21/Sep/2018"), 
				"INR", 0.014f, 400, TradeType.BUY, 195.01);		
		classtoTest.saveSettledTrade(trade2);		
		
		assertEquals( "Cipla", classtoTest.getEntityRanking(getDate("21/Sep/2018"), TradeType.BUY));
		
			
	}
	@Test
	public void testNegativeEntityRanking() throws Exception {	
		SettledTrade trade = new SettledTrade("Cipla", getDate("21/Sep/2018"), 
				"INR", 0.014f, 1000, TradeType.BUY, 95.01);		
		classtoTest.saveSettledTrade(trade);
				
		SettledTrade trade1 = new SettledTrade("Cipla", getDate("21/Sep/2018"), 
				"INR", 0.014f, 500, TradeType.BUY, 95.01);		
		classtoTest.saveSettledTrade(trade1);		
		
		SettledTrade trade2 = new SettledTrade("ABCD", getDate("21/Sep/2018"), 
				"INR", 0.014f, 400, TradeType.BUY, 195.01);		
		classtoTest.saveSettledTrade(trade2);		
		
		assertEquals( null, classtoTest.getEntityRanking(getDate("21/Sep/2018"), TradeType.SELL));
		
			
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
