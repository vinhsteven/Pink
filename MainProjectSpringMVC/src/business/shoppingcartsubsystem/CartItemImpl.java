
package business.shoppingcartsubsystem;

import java.util.logging.Logger;

import business.exceptions.BackendException;
import business.externalinterfaces.CartItem;
import business.externalinterfaces.Product;
import business.productsubsystem.DbClassProduct;
import middleware.exceptions.DatabaseException;


public class CartItemImpl implements CartItem {
	private static final Logger log = Logger.getLogger(CartItem.class.getPackage().getName());
    Integer cartid;
    Integer productid;
    Integer cartItemId;
    String quantity;
    String totalprice;
	String productName;
	Integer catalogid;
	
	@Override
	public Integer getCatalogid() {
		return catalogid;
	}

	public void setCatalogid(Integer catalogid) {
		this.catalogid = catalogid;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	//	this is true if this cart item is data that has come from
    //database
    boolean alreadySaved;
    
    /** This version of constructor used when reading data from screen */
    public CartItemImpl(int productId, String productName, 
                    String quantity,
                    String totalprice) throws BackendException {
        this.productName = productName;
        this.quantity = quantity;
        this.totalprice = totalprice;
        alreadySaved = false;
        this.productid = productId;
        
    }
    
    /** This version of constructor used when reading from database */
    public CartItemImpl(Integer cartid, 
                    Integer productid, 
                    Integer lineitemid, 
                    String quantity, 
                    String totalprice,
                    boolean alreadySaved) throws BackendException {
        this.cartid = cartid;
        this.productid= productid;
        this.cartItemId = lineitemid;
        this.quantity = quantity;
        this.totalprice =totalprice;
        this.alreadySaved = alreadySaved;
    }
    
    //default constructor for testing only
    public CartItemImpl() {}

    public String toString(){
        StringBuffer buf = new StringBuffer();
        buf.append("cartid = <"+cartid+">,");
        buf.append("productid = <"+productid+">,");
        buf.append("lineitemid = <"+cartItemId+">,");
        buf.append("quantity = <"+quantity+">,");
        buf.append("totalprice = <"+totalprice+">");
        buf.append("alreadySaved = <"+alreadySaved+">");
        return buf.toString();
    }
	public boolean isAlreadySaved() {
		return alreadySaved;
	}
	public Integer getCartid() {
		return cartid;
	}
	public Integer getLineitemid() {
		return cartItemId;
	}
	public Integer getProductid() {
		return productid;
	}
	public String getProductName() {
		return productName;
	}
	public String getQuantity() {
		return quantity;
	}
	public String getTotalprice() {
		return totalprice;
	}
	public void setCartId(int id) {
		this.cartid = id;
	}
}
