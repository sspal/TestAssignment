package junit.utility;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import exceptions.SettlementException;
import model.Currency;
import utility.SettlementUtility;

public class SettlementUtilityTest{
	
	@Before
	public void setUp(){
		
	}	
	
	@After
	public void tearDown(){
		
	}
	@Rule
	public ExpectedException expectedExc = ExpectedException.none();
	@Test
	public void testApplySettlementFriday() throws Exception {
			
		LocalDate settleDate = SettlementUtility.assignSettlementDate(LocalDate.of(2018, 9, 21), Currency.INR);
		
		assertEquals(LocalDate.of(2018, 9, 21), settleDate);			
	}
	
	@Test
	public void testApplySettlementNegtive() throws Exception {		
		expectedExc.expect(SettlementException.class);
		expectedExc.expectMessage("InstructionDate cannot be null");
		SettlementUtility.assignSettlementDate(null, Currency.INR);		
	}
	
	@Test
	public void testApplySettlementSatday() throws Exception {		
		LocalDate settleDate = SettlementUtility.assignSettlementDate(LocalDate.of(2018, 9, 22), Currency.INR);		
		assertEquals(LocalDate.of(2018, 9, 24), settleDate);	
	}
	@Test
	public void testApplySettlementSunday() throws Exception {	
		LocalDate settleDate = SettlementUtility.assignSettlementDate(LocalDate.of(2018, 9, 23) , Currency.INR);		
		assertEquals(LocalDate.of(2018, 9, 24), settleDate);	
	}
	@Test
	public void testApplySettlementMonday() throws Exception {				
		LocalDate settleDate = SettlementUtility.assignSettlementDate(LocalDate.of(2018, 9, 24), Currency.INR);		
		assertEquals(LocalDate.of(2018, 9, 24),settleDate);	
	}
	
	@Test
	public void testApplySettlementMondayGulf() throws Exception {			
		LocalDate settleDate = SettlementUtility.assignSettlementDate(LocalDate.of(2018, 9, 24), Currency.AED);		
		assertEquals(LocalDate.of(2018, 9, 24), settleDate);	
	}
	@Test
	public void testApplySettlementFridayGulf() throws Exception {				
		LocalDate settleDate = SettlementUtility.assignSettlementDate(LocalDate.of(2018, 9, 21), Currency.AED);		
		assertEquals(LocalDate.of(2018, 9, 23), settleDate);
	}
	@Test
	public void testApplySettlementSaturdayGulf() throws Exception {
		LocalDate settleDate = SettlementUtility.assignSettlementDate(LocalDate.of(2018, 9,22), Currency.AED);		
		assertEquals(LocalDate.of(2018, 9, 23), settleDate);	
	}
	
	@Test
	public void testApplySettlementSundayGulf() throws Exception {
		LocalDate settleDate = SettlementUtility.assignSettlementDate(LocalDate.of(2018, 9, 23), Currency.AED);
				assertEquals(LocalDate.of(2018, 9, 23), settleDate);	
	}
	
}
