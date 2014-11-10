package fi.utu.library;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BookTest {
	
	Book book;
	
	@Before
	public void setupTest(){
		book = new Book();
	}

	// constructors
	@Test
	public void testBookDefaultConstructor() {
		// first constructor
		assertNotNull(book);
		assertEquals(0.0, book.getBasePrice(), 0.1);
		assertEquals(0.0, book.getDiscount(), 0.1);
		assertEquals(0.0, book.getVat(), 0.1);
		assertFalse(book.getBestSeller());
	}
	
	@Test
	public void testBookSecondConstructor(){
		// second constructor
		book = new Book(3.0, 4.0, 5.0, true);
		assertNotNull(book);
		assertEquals(3.0, book.getBasePrice(), 0.1);
		assertEquals(4.0, book.getDiscount(), 0.1);
		assertEquals(5.0, book.getVat(), 0.1);
		assertTrue(book.getBestSeller());
	}
	
	@Test
	public void testSecondConstructorConstraints(){
		// check constraints
		book = new Book(-3.0, -4.0, -5.0, true);
		assertNotNull(book);
		assertEquals(1.0, book.getBasePrice(), 0.01);
		assertEquals(0.0, book.getDiscount(), 0.01);
		assertEquals(0.0, book.getVat(), 0.01);
	}

	// 1
	@Test
	public void testBasePrice() {
		book.setBasePrice(3.0);
		assertEquals(3.0, book.getBasePrice(), 0.001);
	}
	
	// 2
	@Test
	public void testBasePriceNotNegative() {
		book.setBasePrice(-3.0);
		assertTrue(book.getBasePrice() > 0);

		book.setBasePrice(0.0);
		assertTrue(book.getBasePrice() > 0);
		
		book.setBasePrice(Double.MIN_VALUE);
		assertTrue(book.getBasePrice() > 0);
	}
	
	// 12
	@Test
	public void testBasePriceMin1() {
		book.setBasePrice(-3.0);
		assertEquals(1.0, book.getBasePrice(), 0.001);

		book.setBasePrice(0.0);
		assertEquals(1.0, book.getBasePrice(), 0.001);
		
		book.setBasePrice(Double.MIN_VALUE);
		assertEquals(1.0, book.getBasePrice(), 0.001);
	}

	// 3
	@Test
	public void testVat() {
		book.setVat(3.0);
		assertEquals(3.0, book.getVat(), 0.001);
		book.setVat(0.0);
		assertEquals(0.0, book.getVat(), 0.001);
	}

	// 4
	@Test
	public void testVatNotNegative() {
		book.setVat(-3.0);
		assertTrue(book.getVat() >= 0);
	}
	
	// 13
	@Test
	public void testVatMin0() {
		book.setVat(-3.0);
		assertEquals(0.0, book.getVat(), 0.001);
		
		book.setVat(Double.MIN_VALUE);
		assertEquals(0.0, book.getVat(), 0.001);
	}

	// 5
	@Test
	public void testDiscount() {
		book.setDiscount(3.0);
		assertEquals(3.0, book.getDiscount(), 0.001);
	}
	
	// 6
	@Test
	public void testDiscountNotNegative() {
		book.setDiscount(-3.0);
		assertTrue(book.getDiscount() >= 0.0);
		assertEquals(0.0, book.getDiscount(), 0.001);
	}
	
	// 14
	@Test
	public void testDiscountMin0() {
		book.setDiscount(-3.0);
		assertEquals(0.0, book.getDiscount(), 0.001);
		book.setDiscount(Double.MIN_VALUE);
		assertEquals(0.0, book.getDiscount(), 0.001);
	}
	
	// 7
	@Test
	public void testDiscountMax() {
		book.setDiscount(50);
		assertEquals(50, book.getDiscount(), 0.001);
		book.setDiscount(50.1);
		assertTrue(book.getDiscount() <= 50);
	}
	
	// 15
	@Test
	public void testDiscountMax50(){
		book.setDiscount(50.1);
		assertEquals(50, book.getDiscount(), 0.001);
		book.setDiscount(Double.MAX_VALUE);
		assertEquals(50, book.getDiscount(), 0.001);
	}
	
	// 10
//	@Test 
//	public void testDiscountMaxBestseller(){
//		book.setBestSeller();
//		
//		book.setDiscount(50);
//		assertEquals(25.0, book.getDiscount(), 0.001);
//		book.setDiscount(25);
//		assertEquals(12.5, book.getDiscount(), 0.001);
//		book.setDiscount(0);
//		assertEquals(0.0, book.getDiscount(), 0.001);
//	}

	// 11
	@Test
	public void testBestSeller() {
		book.setBestSeller();
		assertTrue(book.getBestSeller());
	}
	
	// 8
	@Test
	public void testFinalPrice(){

		double base = 100D;
		double vat = 25D;
		double discount = 50D;
		book.setBasePrice(base);
		book.setVat(vat);
		book.setDiscount(discount);
		
		assertEquals(finalPrice(base, vat, discount), book.getSellPrice(), 0.001); 
		
		base = 1D;
		vat = 0;
		discount = 0;
		book.setBasePrice(base);
		book.setVat(vat);
		book.setDiscount(discount);
		
		assertEquals(finalPrice(base, vat, discount), book.getSellPrice(), 0.001);
	}
	
	// 10
	@Test
	public void testFinalPriceBestseller(){

		double base = 100D;
		double vat = 25D;
		double discount = 50D;
		book.setBasePrice(base);
		book.setVat(vat);
		book.setDiscount(discount);
		
		book.setBestSeller();
		
		assertEquals(finalPrice(base, vat, discount/2), book.getSellPrice(), 0.001); 
		
		base = 1D;
		vat = 0;
		discount = 0;
		book.setBasePrice(base);
		book.setVat(vat);
		book.setDiscount(discount);
		
		assertEquals(finalPrice(base, vat, discount/2), book.getSellPrice(), 0.001);
	}
	
	private double finalPrice(double base, double vat, double discount){
		return base * (100 + vat - discount) / 100;
	}
	
	// 9
	@Test
	public void testFinalPriceNotNegative(){
		book = new Book(-10.0, -5.0, -3.0, true);
		assertTrue(book.getSellPrice() > 0.0);

		book = new Book(10.0, 5.0, 3.0, true);
		assertTrue(book.getSellPrice() > 0.0);
		
		book = new Book(10.0, 5.0, 3.0, false);
		assertTrue(book.getSellPrice() > 0.0);
	}

}
