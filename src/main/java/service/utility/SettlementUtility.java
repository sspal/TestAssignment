package service.utility;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;

import model.Constants;
import model.SettledTrade;

public class SettlementUtility {
	
	/**
	 * public applySettlement() method for trade settlement
	 * @param trades - List<SettledTrade>
	 * @return void
	 * @throws Exception 
	 */
	public static void applySettlement(SettledTrade trade) throws Exception {				
		trade = applySettlementDate(trade);
		trade.setTradeTotalValue(getTotalTradeAmount(trade.getUnitPrice(), trade.getUnits(), trade.getFxRate()));		
	}
	
	/**
	 * private applySettlementDate() method for trade settlement
	 * @param trade - SettledTrade
	 * @return void
	 * @throws Exception 
	 * @exception ParseException
	 */
	private static SettledTrade applySettlementDate(SettledTrade trade) throws Exception {		
		boolean nonGulfCurrency = checkNonGulfCurrency(trade.getCurrency());
		LocalDate date = trade.getInstructDate();
		if(date != null) {
		while((!nonGulfCurrency && date.getDayOfWeek() == DayOfWeek.FRIDAY) || (date.getDayOfWeek() == DayOfWeek.SATURDAY) 
				|| (nonGulfCurrency && date.getDayOfWeek() == DayOfWeek.SUNDAY))
		{
			date = date.plusDays(1);
		}		
		
		trade.setSettleDate(date);	
		return trade;
		}
		throw new Exception("InstructionDate cannot be null");
	}

	private static double getTotalTradeAmount(double unitPrice, long units, float fxRate) {
		return unitPrice * units * fxRate;
	}
	

	private static boolean checkNonGulfCurrency(String currency) {
		return (currency != Constants.CURRENCY_AED && currency != Constants.CURRENCY_SAR);
	}

}
