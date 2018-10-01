package service.utility;

import java.text.ParseException;
import java.util.Calendar;

import model.Constants;
import model.SettledTrade;

public class SettlementUtility {
	/**
	 * Calendar instance
	 */
	private static Calendar c = Calendar.getInstance();
	/**
	 * public applySettlement() method for trade settlement
	 * @param trades - List<SettledTrade>
	 * @return void
	 * @throws Exception 
	 */
	public static void applySettlement(SettledTrade trade) throws Exception {				
		applySettlementDate(trade);
		trade.setTradeTotalValue(getTotalTradeAmount(trade.getUnitPrice(), trade.getUnits(), trade.getFxRate()));		
	}
	
	/**
	 * private applySettlementDate() method for trade settlement
	 * @param trade - SettledTrade
	 * @return void
	 * @exception ParseException
	 */
	private static void applySettlementDate(SettledTrade trade) {
		c.setTime(trade.getInstructDate());		
		
		boolean nonGulfCurrency = checkNonGulfCurrency(trade.getCurrency());
		
		int dayInput = c.get(Calendar.DAY_OF_WEEK);
		int dayDjustment = 0;
		
		switch(dayInput) {
		case 1 : {
			dayDjustment = (nonGulfCurrency) ? 1 : 0;			
			break;
		}
		case 2 : case 3 : case 4 : case 5 : {
			dayDjustment = 0;			
			break;
		}		
		case 6 : {
			dayDjustment = (nonGulfCurrency) ?0 : 2;	
			break;
		}
		case 7 :  {
			dayDjustment =  (nonGulfCurrency) ? 2 : 1;
			break;
		}		
	}
		c.add(Calendar.DAY_OF_MONTH, dayDjustment);
		trade.setSettleDate(c.getTime());
		
	}

	private static double getTotalTradeAmount(double unitPrice, long units, float fxRate) {
		return unitPrice * units * fxRate;
	}
	

	private static boolean checkNonGulfCurrency(String currency) {
		return (currency != Constants.CURRENCY_AED && currency != Constants.CURRENCY_SAR);
	}

}
