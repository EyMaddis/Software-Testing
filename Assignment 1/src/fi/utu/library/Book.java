package fi.utu.library;
public class Book {
	public static final double SALE_DISCOUNT = 60;
	
	private double basePrice = 0;
	private double vat = 0;
	private double discount = 0;
	private double sellPrice;
	private boolean bestseller = false;
	private boolean onSale = false;
	
	public Book() {
		this(0,0,0,false);
	}

	public Book(double basePrice, double discount, double vat, boolean bestseller) {
		setBasePrice(basePrice);
		setDiscount(discount);
		setVat(vat);
		if(bestseller) setBestSeller();
	}

	public boolean getOnSale(){
		return onSale;
	}
	
	public void setOnSale(){
		if(!bestseller) {
			onSale = true;
			setSellPrice();
		}
		else {
			System.out.println("Book can't be on sale if it's a bestseller");
		}
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
		if(onSale) {
			System.out.println("Book on sale can't be bestseller");
		}
		else {
			this.bestseller = true;
			setSellPrice();			
		}
	}

	public double getSellPrice() {
		return sellPrice;
	}

	private void setSellPrice() {
		if(bestseller == true){
			sellPrice = basePrice *((100 + (vat - discount* 0.5)) / 100);
		}
		else{
			double tempDiscount = discount;
			if(onSale) {
				tempDiscount = SALE_DISCOUNT;
			} 
			sellPrice = basePrice * ((100 + (vat - tempDiscount)) / 100);
		}
	}
}