package BLL;

import Model.Comnada;

import Dao.OrderDAO;

import java.util.*;
public class OrderBLL {
private final OrderDAO operation=new OrderDAO();
public int getLastId()
{	
	List<Comnada> list=operation.getALL();
	return list.get(list.size()-1).getId_comnada();
}
public boolean insertOrder(int id_product,int id_client)
{
	Integer idp=id_product;
	Integer idc=id_client;
	Comnada order=new Comnada(idp.toString(),idc.toString());
	return operation.insert(order);
}
}
