package delete;

public class Order {
	public Product selectedProduct;
	public int optionHotCold = 0;

	public Order(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public Order(Product selectedProduct, int optionHotCold) {
		this.selectedProduct = selectedProduct;
		this.optionHotCold = optionHotCold;
	}

}
