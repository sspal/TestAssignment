package junit.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.TradeSettlementDaoImpl;
import junit.framework.TestCase;
import model.Constants;
import model.SettledTrade;
import model.TradeType;

public class TradeSettlementDaoImplTest  extends TestCase{
	private TradeSettlementDaoImpl classtoTest;
	
	@Before
	protected void setUp(){
		classtoTest = new TradeSettlementDaoImpl();
	}	
	
	@Test
	public void testSaveSettledTrade() throws Exception {	
		SettledTrade trade = new SettledTrade("Cipla", LocalDate.of(2018, 9, 21), 
				Constants.CURRENCY_INR, 0.014f, 1000, TradeType.BUY, 95.01);
		trade.setSettleDate(LocalDate.of(2018, 9, 21));
		trade.setTradeTotalValue(500);
		classtoTest.saveSettledTrade(trade);
		SettledTrade trade1 = new SettledTrade("Cipla", LocalDate.of(2018, 9, 21), 
				Constants.CURRENCY_INR, 0.014f, 500, TradeType.BUY, 95.01);
		trade1.setSettleDate(LocalDate.of(2018, 9, 21));
		trade1.setTradeTotalValue(100);
		classtoTest.saveSettledTrade(trade1);
		SettledTrade trade2 = new SettledTrade("Cipla", LocalDate.of(2018, 9, 21),
				Constants.CURRENCY_INR, 0.014f, 700, TradeType.SELL, 95.01);
		trade2.setSettleDate(LocalDate.of(2018, 9, 21));
		trade2.setTradeTotalValue(1200);
		classtoTest.saveSettledTrade(trade2);
		
		SettledTrade trade3 = new SettledTrade("Cipla", LocalDate.of(2018, 9, 24), 
				Constants.CURRENCY_INR, 0.014f, 700, TradeType.SELL, 95.01);
		trade3.setSettleDate(LocalDate.of(2018, 9, 24));
		trade3.setTradeTotalValue(1100);
		classtoTest.saveSettledTrade(trade3);
		
		SettledTrade trade4 = new SettledTrade("Cipla", LocalDate.of(2018, 9, 24), 
				Constants.CURRENCY_INR, 0.014f, 700, TradeType.BUY, 95.01);
		trade4.setSettleDate(LocalDate.of(2018, 9, 24));
		trade4.setTradeTotalValue(1300);
		classtoTest.saveSettledTrade(trade4);
		
		SettledTrade trade5 = new SettledTrade("Cipla",LocalDate.of(2018, 9, 24),  
				Constants.CURRENCY_INR, 0.014f, 700, TradeType.SELL, 95.01);
		trade5.setSettleDate(LocalDate.of(2018, 9, 24));
		trade5.setTradeTotalValue(1150);
		classtoTest.saveSettledTrade(trade5);
		
		Map<LocalDate, Map<TradeType, List<SettledTrade>>> allTradesMap = classtoTest.getSettledTrades();
		assertEquals(2, allTradesMap.size());
		assertEquals(2, allTradesMap.get(LocalDate.of(2018, 9, 21)).get(TradeType.BUY).size());
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
				SettledTrade trade = new SettledTrade("Cipla", LocalDate.of(2018, 9, 21),  
						Constants.CURRENCY_INR, 0.014f, 700, null, 95.01);
				trade.setSettleDate(LocalDate.of(2018, 9, 23));
				trade.setTradeTotalValue(1150);	
				classtoTest.saveSettledTrade(trade);
			}catch(Exception e) {
				assertEquals("Trade or Trade Settlement Date or Trade Type cannot be Null",e.getMessage());
			}
	}
		
	@After
	protected void tearDown(){
		classtoTest = null;
	}
}
