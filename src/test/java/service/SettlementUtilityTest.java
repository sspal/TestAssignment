package service;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.Constants;
import model.SettledTrade;
import model.TradeType;
import service.utility.SettlementUtility;

public class SettlementUtilityTest extends TestCase{
	
	
	@Before
	protected void setUp(){
	
	}	
	
	@Test
	public void testApplySettlementFriday() throws Exception {
		SettledTrade trade = new SettledTrade("Cipla", LocalDate.of(2018, 9, 21), 
				Constants.CURRENCY_INR, 0.014f, 1000, TradeType.BUY, 95.01);		
		SettlementUtility.applySettlement(trade);
		
		assertEquals(LocalDate.of(2018, 9, 21), trade.getSettleDate());	
		assertEquals(1330.1400410570204, trade.getTradeTotalValue());	
	}
	
	@Test
	public void testApplySettlementNegtive() throws Exception {
		SettledTrade trade = new SettledTrade("Cipla", null, 
				Constants.CURRENCY_INR, 0.014f, 1000, TradeType.BUY, 95.01);		
		try {
		 trade.getSettleDate();
		}catch(Exception e){
			assertEquals("InstructionDate cannot be null", e.getMessage());	
		}
	}
	@Test
	public void testApplySettlementSatday() throws Exception {
		SettledTrade trade = new SettledTrade("Cipla", LocalDate.of(2018, 9, 22), 
				Constants.CURRENCY_INR, 0.014f, 1000, TradeType.BUY, 95.01);		
		SettlementUtility.applySettlement(trade);
		
		assertEquals(LocalDate.of(2018, 9, 24), trade.getSettleDate());	
	}
	@Test
	public void testApplySettlementSunday() throws Exception {
		SettledTrade trade = new SettledTrade("Cipla", LocalDate.of(2018, 9, 23), 
				Constants.CURRENCY_INR, 0.014f, 1000, TradeType.BUY, 95.01);		
		SettlementUtility.applySettlement(trade);
		
		assertEquals(LocalDate.of(2018, 9, 24), trade.getSettleDate());	
	}
	@Test
	public void testApplySettlementMonday() throws Exception {
		SettledTrade trade = new SettledTrade("Cipla", LocalDate.of(2018, 9, 24), 
				Constants.CURRENCY_INR, 0.014f, 1000, TradeType.BUY, 95.01);		
		SettlementUtility.applySettlement(trade);
		
		assertEquals(LocalDate.of(2018, 9, 24), trade.getSettleDate());	
	}
	
	@Test
	public void testApplySettlementMondayGulf() throws Exception {
		SettledTrade trade = new SettledTrade("Cipla", LocalDate.of(2018, 9, 24), 
				Constants.CURRENCY_AED, 0.014f, 1000, TradeType.BUY, 95.01);		
		SettlementUtility.applySettlement(trade);
		
		assertEquals(LocalDate.of(2018, 9, 24), trade.getSettleDate());	
	}
	@Test
	public void testApplySettlementFridayGulf() throws Exception {
		SettledTrade trade = new SettledTrade("Cipla", LocalDate.of(2018, 9, 21), 
				Constants.CURRENCY_SAR, 0.014f, 1000, TradeType.BUY, 95.01);		
		SettlementUtility.applySettlement(trade);
		
		assertEquals(LocalDate.of(2018, 9, 23), trade.getSettleDate());	
	}
	@Test
	public void testApplySettlementSaturdayGulf() throws Exception {
		SettledTrade trade = new SettledTrade("Cipla", LocalDate.of(2018, 9, 22), 
				Constants.CURRENCY_AED, 0.014f, 1000, TradeType.BUY, 95.01);		
		SettlementUtility.applySettlement(trade);
		
		assertEquals(LocalDate.of(2018, 9, 23), trade.getSettleDate());	
	}
	
	@Test
	public void testApplySettlementSundayGulf() throws Exception {
		SettledTrade trade = new SettledTrade("Cipla", LocalDate.of(2018, 9, 23), 
				Constants.CURRENCY_AED, 0.014f, 1000, TradeType.BUY, 95.01);		
		SettlementUtility.applySettlement(trade);
		
		assertEquals(LocalDate.of(2018, 9, 23), trade.getSettleDate());	
	}
	@After
	protected void tearDown(){
			
	}
}
