package junit.controller;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.TradeController;
import junit.framework.TestCase;
import model.Constants;
import model.SettledTrade;
import model.TradeType;

public class TradeControllerTest extends TestCase{
	
private TradeController classtoTest;

@Before
protected void setUp(){
	classtoTest = new TradeController();	
}	

@Test
public void testCreateTrade() throws Exception {	
	SettledTrade trade = new SettledTrade("Cipla", LocalDate.of(2018, 9, 21), 
			Constants.CURRENCY_INR, 0.014f, 1000, TradeType.BUY, 95.01);		
	classtoTest.createTrade(trade);
	assertEquals(LocalDate.of(2018, 9, 21), trade.getSettleDate());		
	
	SettledTrade trade1 = new SettledTrade("Cipla", LocalDate.of(2018, 9, 21), 
			Constants.CURRENCY_AED, 0.014f, 500, TradeType.BUY, 95.01);		
	classtoTest.createTrade(trade1);
	assertEquals(LocalDate.of(2018, 9, 23), trade1.getSettleDate());
}

@Test
public void testGetTradeAggregrate() throws Exception {
	SettledTrade trade = new SettledTrade("Cipla", LocalDate.of(2018, 9, 21), 
			Constants.CURRENCY_INR, 0.014f, 1000, TradeType.BUY, 95.01);		
	classtoTest.createTrade(trade);
			
	SettledTrade trade1 = new SettledTrade("Cipla", LocalDate.of(2018, 9, 21), 
			Constants.CURRENCY_INR, 0.014f, 500, TradeType.BUY, 95.01);		
	classtoTest.createTrade(trade1);
	
	SettledTrade trade2 = new SettledTrade("ABCD", LocalDate.of(2018, 9, 21), 
			Constants.CURRENCY_INR, 0.014f, 600, TradeType.SELL, 195.01);		
	classtoTest.createTrade(trade2);
	
	assertEquals( 1995.2100615855306, classtoTest.getTradeAggregate(LocalDate.of(2018, 9, 21), TradeType.BUY));	
}

@Test
public void testGetAggregateNegative() throws Exception {	
	SettledTrade trade = new SettledTrade("Cipla", LocalDate.of(2018, 9, 21), 
			Constants.CURRENCY_INR, 0.014f, 1000, TradeType.BUY, 95.01);		
	classtoTest.createTrade(trade);
			
	SettledTrade trade1 = new SettledTrade("Cipla", LocalDate.of(2018, 9, 21), 
			Constants.CURRENCY_INR, 0.014f, 500, TradeType.BUY, 95.01);		
	classtoTest.createTrade(trade1);		
	
	assertEquals( 0.0, classtoTest.getTradeAggregate(LocalDate.of(2018, 9, 21), TradeType.SELL));
	
	assertEquals( 0.0, classtoTest.getTradeAggregate(LocalDate.of(2018, 9, 22), TradeType.BUY));		
}

@Test
public void testGetEntityRanking() throws Exception {	
	SettledTrade trade = new SettledTrade("Cipla", LocalDate.of(2018, 9, 21), 
			Constants.CURRENCY_INR, 0.014f, 1000, TradeType.BUY, 95.01);		
	classtoTest.createTrade(trade);
			
	SettledTrade trade1 = new SettledTrade("Cipla", LocalDate.of(2018, 9, 21), 
			Constants.CURRENCY_INR, 0.014f, 500, TradeType.BUY, 95.01);		
	classtoTest.createTrade(trade1);		
	
	SettledTrade trade2 = new SettledTrade("ABCD", LocalDate.of(2018, 9, 21), 
			Constants.CURRENCY_INR, 0.014f, 400, TradeType.BUY, 195.01);		
	classtoTest.createTrade(trade2);		
	
	assertEquals( "Cipla", classtoTest.getEntityRanking(LocalDate.of(2018, 9, 21), TradeType.BUY));
	
		
}

@Test
public void testNegativeEntityRanking() throws Exception {	
	SettledTrade trade = new SettledTrade("Cipla", LocalDate.of(2018, 9, 21), 
			Constants.CURRENCY_INR, 0.014f, 1000, TradeType.BUY, 95.01);		
	classtoTest.createTrade(trade);
			
	SettledTrade trade1 = new SettledTrade("Cipla", LocalDate.of(2018, 9, 21), 
			Constants.CURRENCY_INR, 0.014f, 500, TradeType.BUY, 95.01);		
	classtoTest.createTrade(trade1);		
	
	SettledTrade trade2 = new SettledTrade("ABCD", LocalDate.of(2018, 9, 21), 
			Constants.CURRENCY_INR, 0.014f, 400, TradeType.BUY, 195.01);		
	classtoTest.createTrade(trade2);		
	
	assertEquals( null, classtoTest.getEntityRanking(LocalDate.of(2018, 9, 21), TradeType.SELL));
	
		
}

@After
protected void tearDown(){
	classtoTest = null;
}
}
