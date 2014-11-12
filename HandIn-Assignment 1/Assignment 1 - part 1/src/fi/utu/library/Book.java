package fi.utu.library;
public class Book {
	private double basePrice = 0;
	private double vat = 0;
	private double discount = 0;
	private double sellPrice;
	private boolean bestseller = false;

	public Book() {
		this(0,0,0,false);
	}

	public Book(double basePrice, double discount, double vat, boolean bestseller) {
		setBasePrice(basePrice);
		setDiscount(discount);
		setVat(vat);
		if(bestseller) setBestSeller();
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = Math.max(basePrice, 1D);
		setSellPrice();
	}

	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
		this.vat = Math.max(vat, 0);
		setSellPrice();
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		if (discount > 50){
			System.out.println("discount can not be larger than 50 %");
			this.discount = 50;
		} 
		else {
			this.discount = Math.max(discount, 0);
		}
		setSellPrice();
	}
	
	public boolean getBestSeller(){
		return bestseller; 
	}
	
	public void setBestSeller(){
		this.bestseller = true;
		setSellPrice();
	}

	public double getSellPrice() {
		return sellPrice;
	}

	private void setSellPrice() {
    if(bestseller == true){
      sellPrice = basePrice *((100 + (vat - discount* 0.5)) / 100);
    }
    else{
      sellPrice = basePrice * ((100 + (vat - discount)) / 100);
    }
    if(sellPrice < 0) sellPrice = 0; // free
	}

}