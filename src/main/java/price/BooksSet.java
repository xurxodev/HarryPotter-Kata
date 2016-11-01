package price;

import cart.Book;

import java.util.HashSet;

public class BooksSet {
    private HashSet<Book> books;
    private int discount;

    public BooksSet(HashSet<Book> books, int discount){
        this.books = books;
        this.discount = discount;
    }

    public HashSet<Book> getBooks() {
        return books;
    }

    public int getDiscount() {
        return discount;
    }
}
