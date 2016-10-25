package presentation.util;

import business.SessionCache;
import business.externalinterfaces.CustomerSubsystem;

/**
 * Provides convenience methods to extract values from SessionCache
 *
 */
public class CacheReader {
	
	public static CustomerSubsystem readCustomer() {
		return (CustomerSubsystem)SessionCache.getInstance().get(SessionCache.CUSTOMER);
	}
	public static boolean readLoggedIn() {
		return (Boolean)SessionCache.getInstance().get(SessionCache.LOGGED_IN);
	}
	
	public static boolean custIsAdmin() {
		return readCustomer().isAdmin();
	}
}
