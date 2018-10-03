package utility;

import java.time.DayOfWeek;
import java.time.LocalDate;

import exceptions.SettlementException;
import model.Currency;

public class SettlementUtility {
	
	/**
	 * public applySettlement() method for trade settlement
	 * @param trades - List<SettledTrade>
	 * @return void
	 * @throws Exception 
	 */
	public static LocalDate assignSettlementDate(LocalDate date, Currency currency) throws Exception {
		LocalDate sttlementDate = date;
		
		boolean nonGulfCurrency = (currency != Currency.AED && currency != Currency.SAR);
		if(date != null) {		
			DayOfWeek day = date.getDayOfWeek(); 
			switch(day){
			case FRIDAY :{
				if(!nonGulfCurrency) {
					sttlementDate = date.plusDays(2);
					}
				break;
				}
			
			case SATURDAY :{				
					sttlementDate = (nonGulfCurrency) ? date.plusDays(2) : date.plusDays(1);	
				break;
				}
			
			case SUNDAY :{				
				if(nonGulfCurrency) {
					sttlementDate = date.plusDays(1);
					}		
				break;
				}
			default:
				break;
				
			}			
		
		return sttlementDate;
		}
		throw new SettlementException("InstructionDate cannot be null");			
	}
}
