package business.externalinterfaces;

import middleware.exceptions.DatabaseException;
import middleware.externalinterfaces.DbClass;

public interface DbClassCatalogForTest extends DbClass{
	public int saveNewCatalog(String catName) throws DatabaseException;
	public void deleteCatalog(Catalog catalog) throws DatabaseException;
	public Catalog getCatalogFromName(String catName) throws DatabaseException;
}
