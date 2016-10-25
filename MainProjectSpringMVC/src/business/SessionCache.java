
package business;

import java.util.HashMap;

/**
 * Standalone version of a web session context. Intended to provide
 * a simple cache for the app during execution.
 *
 */
public class SessionCache {
    public static final String CUSTOMER = "Customer";
    public static final String LOGGED_IN = "LoggedIn";
    public static final String LAST_REQUEST_URL = "LastRequestURL";
    public static final String RESOURCES = "resources";
    //public interface
    public static SessionCache getInstance() {
        return instance;
        
    }   
    public void add(Object name, Object value){
        if(context != null){
            context.put(name,value);
        }
    }
    
    public Object get(Object name){
        if(context == null){
            return null;
        }
        return context.get(name);
    }
    public void remove(Object name){
        context.remove(name);
    }
    
    //private 
    private static SessionCache instance = new SessionCache();
    private HashMap<Object,Object> context;
    private SessionCache(){
        context = new HashMap<Object,Object>();
        context.put(LOGGED_IN, Boolean.FALSE);      
    }
}
