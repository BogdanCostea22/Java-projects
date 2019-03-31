package Model;

public class Product {
	private int id_product;
	private String product_name;
	private int quantity;
	private String producer;
	private String details;
	
	//Constructori
	public Product()
	{}
	public Product(String productName,String producer,int quantity,String details)
	{
		this.product_name=productName;
		this.producer=producer;
		this.quantity=quantity;
		this.details=details;
	}
	
	//Gettere si Settere
	public int getId_product() {
		return id_product;
	}

	public void setId_product(int id_product) {
		this.id_product = id_product;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
}

