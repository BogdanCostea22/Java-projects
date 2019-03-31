package BLL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import Dao.ProductDAO;
import Model.Product;

public class ProductBLL {
private final ProductDAO operation=new ProductDAO();

public List<Object> getProduct()
{
	List<Object> obj=new ArrayList<Object>();
	for(Product product:operation.getALL())
		obj.add(product);
	return obj;
}
public int checkQuantity(int id,int quantity)
{
	Product product=operation.findById(id);
	if(product.getQuantity()<quantity)return -1;
	else{
		product.setQuantity(product.getQuantity()-quantity);
		operation.update(product, id);
	}
	return 0;
}

public Product findById(int id)
{
	return operation.findById(id);
}
public boolean deleteProduct(int id)
{
	return operation.deleteById(id);
}
public boolean findId(int id)
{
	List<Product> list=operation.getALL();
	for(Product product:list)
	{
		if(product.getId_product()==id)return true;
	}
	return false;
}
public boolean updateProduct(int id,String productName,String producer,int quantity,String details)
{
	Product product=new Product(productName,producer,quantity,details);
	return operation.update(product,id);
}
public boolean addProduct(String productName,String producer,int quantity,String details)
{	
	Product product=new Product(productName,producer,quantity,details);
	return operation.insert(product);
}
public JTable createTable()
{
	List<Object>list=getProduct();
	return operation.newTable(list);
}
}
