package fi.utu.library;
import java.util.ArrayList;
import java.util.Collections;

public class Library {
	ArrayList<Book> bookList = new ArrayList<Book>();
        
	public ArrayList<Book> SortByBasePrice(ArrayList<Book> books){
		   boolean sorted = false;
		   // sort the array		   
	       while (!sorted) {
	         sorted = true;
	         for (int i=0; i < books.size()-1; i++) {
	           if (bookList.get(i).getBasePrice() < bookList.get(i+1).getBasePrice()) {
	        		   Collections.swap(books, i, i+1);
	        		   sorted = true;
	           }  
	         }
	       }
	       return books;
	       }

}