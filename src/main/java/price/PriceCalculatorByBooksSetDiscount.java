package price;

import cart.Book;
import cart.ShoppingCart;
import cart.ShoppingCartItem;

import java.util.List;

public class PriceCalculatorByBooksSetDiscount implements ShoppingCart.PriceCalculator {

    private BooksSetFactory booksSetFactory;

    public PriceCalculatorByBooksSetDiscount(BooksSetFactory booksSetFactory){
        this.booksSetFactory = booksSetFactory;
    }

    @Override
    public Double calculate(List<ShoppingCartItem> shoppingCartItems) {
        List<BooksSet> setsOfDifferentBooks =
                booksSetFactory.getDifferentBooksSetsWithMaxTotalDiscount(shoppingCartItems);

        double totalPrice =0.0;
        double setPrice =0.0;

        for (BooksSet booksSet:setsOfDifferentBooks){
            for (Book book:booksSet.getBooks()) {
                setPrice += book.getPrice();
            }

            setPrice = setPrice * (1.0 - (booksSet.getDiscount()/100.0));
            totalPrice +=setPrice;
            setPrice = 0;
        }

        return totalPrice;
    }

}
