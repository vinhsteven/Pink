package business.util;


import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import business.SessionCache;
import business.SessionCache;
import business.externalinterfaces.CartItem;
import business.externalinterfaces.CustomerSubsystem;

public class DataUtil {
	
	public static boolean custIsAdmin() {
		CustomerSubsystem cust = readCustFromCache();
        return (cust != null && cust.isAdmin());
	}
	
	public static boolean isLoggedIn() {
		return (Boolean)SessionCache.getInstance().get(SessionCache.LOGGED_IN);
	}
	
	public static CustomerSubsystem readCustFromCache() {
		return (CustomerSubsystem)SessionCache.getInstance().get(SessionCache.CUSTOMER);
	}
	public static double computeTotal(List<CartItem> list) {
		DoubleSummaryStatistics summary 
		  = list.stream().collect(
		       Collectors.summarizingDouble(item -> Double.parseDouble(item.getTotalprice())));
		return summary.getSum();
	}
}
