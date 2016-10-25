package presentation.control;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import business.exceptions.BackendException;
import business.exceptions.BusinessException;
import business.externalinterfaces.CartItem;
import business.externalinterfaces.Catalog;
import business.externalinterfaces.ShoppingCart;
import business.usecasecontrol.BrowseAndSelectController;
import business.usecasecontrol.ManageProductsController;
import presentation.data.BrowseSelectData;
import presentation.data.CartItemData;
import presentation.data.CartItemPres;
import presentation.data.CatalogPres;
import presentation.data.ManageProductsData;
import presentation.data.ProductPres;
import presentation.util.Util;

@Controller
public class BrowseSelectUIControl {
	@Autowired BrowseAndSelectController browseAndSelectController;
	@Autowired BrowseSelectData browseSelectData;
	@Autowired ManageProductsData manageProductsData;
	@Autowired ManageProductsController manageProductsController;
	
	/** do Online purchase */
	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		//Catalog
		List<CatalogPres> catalogs = populateCatalog(request.getSession());
		browseSelectData.setCatalogs(catalogs);
		//Hot products
		List<List<ProductPres>> sumProd = new ArrayList<List<ProductPres>>();
		List<ProductPres> latestProducts = null;
		List<ProductPres> recommendedProducts = null;
		try {
			latestProducts = browseSelectData.getProductList(catalogs.get(0));
			browseSelectData.setSelectedCatalog(catalogs.get(0));
			browseSelectData.addProductsToMap(catalogs.get(0), latestProducts);
			
			recommendedProducts = browseSelectData.getProductList(catalogs.get(1));
			//add recommended products list and products to sumProd
			sumProd.add(latestProducts);
			sumProd.add(recommendedProducts);
			
//			request.getSession().setAttribute("products", products);
			request.getSession().setAttribute("products", sumProd);
			
		} catch (BackendException e) {
			e.printStackTrace();
		}		
		
		return "home";
	}
	 
	private List<CatalogPres> populateCatalog(HttpSession session){
		if(session.getAttribute("catalogs") == null){
			List<CatalogPres> catalogs = null;
			try {
				catalogs = browseSelectData.getCatalogList();
			} catch (BackendException e) {
				e.printStackTrace();
			}
			session.setAttribute("catalogs", catalogs);
		}
		
		return (List<CatalogPres>) session.getAttribute("catalogs");
	}

	
	/** View Product List */
	@RequestMapping(value = "/catalog/{catalogId}", method = RequestMethod.GET)
	public String viewProductListHandler(@PathVariable int catalogId, ModelMap modelMap) {		
		List<ProductPres> products = null;
		try {
			CatalogPres selectedCatalog = browseSelectData.getCatalogs().stream()
					.filter(cat -> cat.getId() == catalogId)
					.collect(Collectors.toList()).get(0);
			browseSelectData.setSelectedCatalog(selectedCatalog);

			products = browseSelectData.getProductList(selectedCatalog);
			browseSelectData.addProductsToMap(selectedCatalog, products);
			
			modelMap.addAttribute("products", products);			
		} catch (BackendException e) {
			e.printStackTrace();
		}
		
		return "product_list";
	}
	
	
	/** View Product Details */
	@RequestMapping("/product/{id}")
	public String viewProductDetailHandler(@PathVariable int id,HttpSession session, ModelMap modelMap) {
		try{
			CatalogPres selectedCatalog = browseSelectData.getSelectedCatalog();
			List<ProductPres> productList = new ArrayList<ProductPres>();
			if(selectedCatalog != null)
				productList = browseSelectData.getProductsByCatalog(selectedCatalog);
			ProductPres selectedProduct = null;
			if(productList != null){
				List<ProductPres> products = productList.stream()
															.filter(cat -> cat.getId() == id)
															.collect(Collectors.toList());
				if(products != null && products.size() > 0)
					selectedProduct = products.get(0);
			}
			
			if(selectedProduct == null){
				selectedProduct = browseSelectData.getProductById(id);
			}
			browseSelectData.setSelectedProduct(selectedProduct);
			modelMap.addAttribute("product", selectedProduct);

		} catch (BackendException e) {
			e.printStackTrace();
		}
		return "product_details";
	}
	
	@RequestMapping("/shoppingcart")
	public String viewShoppingCart(HttpSession session, ModelMap modelMap) {	
		return "shopping_cart";
	}
	
	@RequestMapping(value = { "/shoppingcart/{id}" })
	public String removeCartItem(@PathVariable int id, HttpSession session){
		
		List<CartItemData> cartDataItems = getCartDataItems(session);
		cartDataItems.remove(id);
		return "redirect:/shoppingcart";
	}
	
	@RequestMapping(value = "/shoppingcart/{index}/{val}")
	public @ResponseBody String updateShoppingCart(@PathVariable int index, @PathVariable int val, HttpSession session) {
		String result = "true";
		
		List<CartItemData> cartDataItems = getCartDataItems(session);		
		int productId = cartDataItems.get(index).getProductId();
		int availQuantity = 1;
		try {
			ProductPres prodPres = browseSelectData.getProductById(productId);
			availQuantity = prodPres.getProduct().getQuantityAvail();
			
			browseAndSelectController.runQuantityRules(prodPres.getProduct(), String.valueOf(val));	
			
			//Pass rule check
			cartDataItems.get(index).setQuantity(val);
			
		} catch (BackendException e) {
			e.printStackTrace();
			result = e.getMessage();
		}	
		catch (BusinessException e) {
			e.printStackTrace();
			result = String.valueOf(availQuantity);
		}		
		
		return result;
	}
	
	/** Input Quantity Rule --> run Quantity Rules */
	@RequestMapping(value = "/shoppingcart/check/{productId}/{quantity}")
	public @ResponseBody String checkAvailable(@PathVariable int productId, @PathVariable int quantity) {		
		String result = "true";
		try {
			ProductPres prodPres = browseSelectData.getSelectedProduct();
			
			if(prodPres.getId() != productId) 
				prodPres = browseSelectData.getProductById(productId);	
			
			browseAndSelectController.runQuantityRules(prodPres.getProduct(), String.valueOf(quantity));			
		} catch (BackendException e) {
			e.printStackTrace();
			result = "false";
		}	
		catch (BusinessException e) {
			e.printStackTrace();
			result = "false";
		}
		
		return result;
	}
	
	/** Add to Cart */
	@RequestMapping(value = "/addToCart/{productId}")
	public String addToCartHandler(@PathVariable int productId, HttpServletRequest request) {
		
		

		try {
			List<CartItemData> cartDataItems = getCartDataItems(request.getSession());
			ProductPres prodPres = browseSelectData.getSelectedProduct();
			
			if(prodPres.getId() != productId) 
				prodPres = browseSelectData.getProductById(productId);	
			
			CartItemData itemData = new CartItemData();
			itemData.setItemName(prodPres.getName());
			itemData.setPrice(Double.parseDouble(prodPres.getUnitPrice()));
			itemData.setQuantity(Integer.parseInt(request.getParameter("quantity")));
			itemData.setProductId(productId);
			itemData.setCatalogid(prodPres.getCatalogid());
			cartDataItems.add(itemData);
		} catch (BackendException e) {
			e.printStackTrace();
		}
		return "shopping_cart";

	}
	
	@RequestMapping("/myshoppingcart")
	public String getSavedShoppingCart(HttpSession session, ModelMap modelMap) {
		
		try {
			ShoppingCart cart = browseSelectData.retrieveSavedCart();
			List<CartItem> cartItems = cart.getCartItems();
			List<CartItemData> cartDataItems = getCartDataItems(session);
			cartDataItems.clear();
			cartDataItems.addAll(Util.cartItemsToCartItemData(cartItems));
		
		} catch (BackendException e) {
			e.printStackTrace();
		}
		
		return "shopping_cart";
	}
	
	
	
	@RequestMapping("/myshoppingcart/save")
	public String saveShoppingCart(HttpSession session, ModelMap modelMap) {		 
		try {
			List<CartItemData> cartDataItems = getCartDataItems(session);
			
			List<CartItemPres> cartItemPresList = cartDataItems
					.stream()
					.map(d -> {CartItemPres p = new CartItemPres(); p.setCartItem(d); return p;})
					.collect(Collectors.toList());
					
			browseAndSelectController.updateShoppingCartItems(Util.cartItemPresToCartItemList(cartItemPresList));
			browseAndSelectController.saveLiveCart();
		} catch (BackendException e) {
			e.printStackTrace();
		}		
		modelMap.addAttribute("savecompleted", true);		
		return "shopping_cart";
	}
	
	private List<CartItemData> getCartDataItems(HttpSession session){
		List<CartItemData> cartDataItems = (List<CartItemData>)session.getAttribute("cartDataItems");
		if(cartDataItems == null){
			cartDataItems =  new ArrayList<CartItemData>();
			session.setAttribute("cartDataItems", cartDataItems);	
		}
		
		return cartDataItems;
	}
	
	//22-10-2016 test new layout Tam-Le start
	/** test new layout */
	@RequestMapping("/bigshot")
	public String bigShot(HttpServletRequest request) {		
		return "indexnew";
	}
	//22-10-2016 test new layout Tam-Le end
	


}
