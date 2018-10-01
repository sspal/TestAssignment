package service;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import dao.ITradeSettlementDao;
import model.SettledTrade;
import model.TradeType;
import service.utility.SettlementUtility;
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
	public void saveSettledTrade(SettledTrade trade) throws Exception {
		SettlementUtility.applySettlement(trade);
		dao.saveSettledTrade(trade);		
	}
	/**
	 * This method gets aggregated figures of trades for a date.
	 * @param requestDate -  Date
	 * @param tradeType - TradeType
	 * @return double  -double value of gross trade value
	 * @throws Exception 
	 */
	public double getTradeAggregate(Date requestDate, TradeType tradeType) throws Exception {
		Map<Date, Map<TradeType, List<SettledTrade>>> allTradesMap = dao.getSettledTrades();
		Map<TradeType, List<SettledTrade>> tradeMapbyDate = allTradesMap.get(requestDate);
		double grossValue = 0.0;
		if(tradeMapbyDate != null) {
			List<SettledTrade> tradeList = tradeMapbyDate.get(tradeType);
			if(tradeList != null && tradeList.size() > 0) {
			for(SettledTrade trade : tradeList) {
				grossValue = grossValue + trade.getTradeTotalValue();
				}			
			}			
		}
		return grossValue;
	}
	
	public String getEntityRanking(Date requestDate, TradeType tradeType) throws Exception {
		Map<Date, Map<TradeType, List<SettledTrade>>> allTradesMap = dao.getSettledTrades();
		Map<TradeType, List<SettledTrade>> tradeMapbyDate = allTradesMap.get(requestDate);
		String topEntity = null;		
		
		if(tradeMapbyDate != null) {
			List<SettledTrade> tradeList = tradeMapbyDate.get(tradeType);
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
