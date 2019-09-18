package gr.bundles.api;

public class Bundle {

	private String productName;
	private float price;
	private String productCode;
	private String productExpirationDate;
	private String availabilityDate;
	private boolean active;
	
	public Bundle() {
		
	}

	public Bundle(String productName, float price, String productCode, String productExpirationDate,
			String availabilityDate, boolean active) {
		this.productName = productName;
		this.price = price;
		this.productCode = productCode;
		this.productExpirationDate = productExpirationDate;
		this.availabilityDate = availabilityDate;
		this.active = active;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductExpirationDate() {
		return productExpirationDate;
	}

	public void setProductExpirationDate(String productExpirationDate) {
		this.productExpirationDate = productExpirationDate;
	}

	public String getAvailabilityDate() {
		return availabilityDate;
	}

	public void setAvailabilityDate(String availabilityDate) {
		this.availabilityDate = availabilityDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}
