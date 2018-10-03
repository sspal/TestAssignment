package service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import dao.ITradeSettlementDao;
import model.SettledTrade;
import model.TradeType;
import utility.SettlementUtility;
/**
 * 
 * Trade Settlement Service Class
 *
 */
public class TradeSettlementServiceImpl implements ITradeSettlementService{
	private final ITradeSettlementDao dao;
	
	public TradeSettlementServiceImpl(ITradeSettlementDao dao){
		this.dao = dao;
	}
	/**
	 * This method creates trade entry after doing settlement.
	 * @param trade - SettledTrade
	 * @throws Exception 
	 */
	public void saveTrade(SettledTrade trade) throws Exception {
		trade.setSettleDate(SettlementUtility.assignSettlementDate(trade.getInstructDate(), trade.getCurrency()));
		trade.setTradeTotalValue(getTotalTradeAmount(trade.getUnitPrice(), trade.getUnits(), trade.getFxRate()));	
		dao.saveSettledTrade(trade);		
	}
	
	private double getTotalTradeAmount(double unitPrice, long units, float fxRate) {
		return unitPrice * units * fxRate;
	}
	
	/**
	 * This method gets aggregated figures of trades for a date.
	 * @param requestDate -  LocalDate
	 * @param tradeType - TradeType
	 * @return double  -double value of gross trade value
	 * @throws Exception 
	 */
	public double getTradeAggregate(LocalDate requestDate, TradeType tradeType) throws Exception {
		
		double grossValue = 0.0;		
			List<SettledTrade> tradeList = dao.getSettledTrades(requestDate, tradeType);
			if(tradeList != null && tradeList.size() > 0) {
			for(SettledTrade trade : tradeList) {
				grossValue = grossValue + trade.getTradeTotalValue();
				}			
			}		
		return grossValue;
	}
	/**
	 * public getEntityRanking() method for trade ranking figure calculation
	 * @param requestDate - LocalDate
	 * @param TradeType - tradeType
	 * @return String
	 * @throws Exception 
	 */
	public String getEntityRanking(LocalDate requestDate, TradeType tradeType) throws Exception {
		
		String topEntity = null;		
		
			List<SettledTrade> tradeList = dao.getSettledTrades(requestDate, tradeType);
			if(tradeList != null && tradeList.size() > 0) {
				HashMap<String, Double> entityMap = new HashMap<String, Double>();
				Double value = 0.0;
				for(SettledTrade trade : tradeList) {					
					value = entityMap.get(trade.getTradeEntity());
					value = (value != null) ? value : 0.0;
					entityMap.put(trade.getTradeEntity(), (value + trade.getTradeTotalValue()));					
				}				
				Map<String, Double> sortedEntities = new TreeMap<String, Double>(new RatingCompare(entityMap));
				sortedEntities.putAll(entityMap);
				entityMap = null;
				topEntity = sortedEntities.entrySet().iterator().next().getKey();
			}		
		return topEntity;		
	}
	/**
	 * 
	 * class that implements comparator to sort EntityDetails
	 * sorting by TradeGrossValue
	 *
	 */
	private class RatingCompare implements  Comparator<Object> 
	{ 
			HashMap<String, Double> map;
		    public RatingCompare(HashMap<String, Double> map) {
		        this.map = map;
		    }
		/**
		 * 
		 * overriden compare method that to sort EntityDetails
		 * sorting by TradeGrossValue
		 */		
	    public int compare(Object o1, Object o2) {
	        return ((Double) map.get(o2)).compareTo((Double) map.get(o1));
	    }
	}
	
	

	
	
	
}
