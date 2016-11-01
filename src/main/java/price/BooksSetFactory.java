package price;

import cart.Book;
import cart.ShoppingCartItem;

import java.util.*;


public class BooksSetFactory {

    List<BooksSetDiscount> discounts;

    public BooksSetFactory(List<BooksSetDiscount> discounts){
        this.discounts = discounts;
    }

    public List<BooksSet> getDifferentBooksSetsWithMaxTotalDiscount(List<ShoppingCartItem> shoppingCartItems) {

        List<BooksSet> optimizeSetList;

        optimizeSetList = getBestCombinationBooksSets(shoppingCartItems);

        return optimizeSetList;
    }

    private List<BooksSet> getBestCombinationBooksSets(List<ShoppingCartItem> shoppingCartItems) {
        List<List<BooksSet>> differentBooksSetsCombinations = new ArrayList<>();

        for (int i = shoppingCartItems.size();i>=1;i--){
            differentBooksSetsCombinations.add(calculateDifferentBooksSetsByMaxSize(shoppingCartItems,i));
        }

        List<BooksSet> optimizeSetList;

        if(differentBooksSetsCombinations.size() > 1)
            optimizeSetList = selectBooksSetsWithMaxDiscount(differentBooksSetsCombinations);
        else
            optimizeSetList = differentBooksSetsCombinations.get(0);
        return optimizeSetList;
    }

    private List<BooksSet> calculateDifferentBooksSetsByMaxSize(List<ShoppingCartItem> shoppingCartItems, int maxSizeSet) {
        List<ShoppingCartItem> remainingShoppingCartItems = cloneShoppingCartItems(shoppingCartItems);
        List<BooksSet> setsOfDifferentBooks = new ArrayList<>();

        while (remainingShoppingCartItems.size() > 0) {
            final BooksSet oneSetOfDifferentBooks = createNextSet(remainingShoppingCartItems,maxSizeSet);
            setsOfDifferentBooks.add(oneSetOfDifferentBooks);
        }

        return setsOfDifferentBooks;
    }

    private BooksSet createNextSet(List<ShoppingCartItem> remainingShoppingCartItems, int maxSizeSet) {
        HashSet<Book> books = new HashSet<>();

        for (ShoppingCartItem item:new ArrayList<>(remainingShoppingCartItems)) {

            books.add(item.getBook());

            if (item.getQuantity() == 1)
                remainingShoppingCartItems.remove(item);
            else
                item.changeQuantity(item.getQuantity() - 1);

            if (books.size() == maxSizeSet)
                break;
        }

        BooksSet booksSet = new BooksSet(books,getDiscount(books.size()));

        return booksSet;
    }

    private List<BooksSet> selectBooksSetsWithMaxDiscount(List<List<BooksSet>> booksSetsCombinations) {
        List<BooksSet> maxDiscountBooksSets = null;
        int maxBooksSetsDiscount = 0;
        int totalBooksSetsDiscount = 0;

        for (List<BooksSet> booksSets:booksSetsCombinations) {
            for (BooksSet booksSet:booksSets) {
                totalBooksSetsDiscount += booksSet.getDiscount();
            }

            if (maxBooksSetsDiscount < totalBooksSetsDiscount) {
                maxDiscountBooksSets = booksSets;
                maxBooksSetsDiscount = totalBooksSetsDiscount;
            }

            totalBooksSetsDiscount = 0;
        }

        return maxDiscountBooksSets;
    }


    private List<ShoppingCartItem> cloneShoppingCartItems (List<ShoppingCartItem> shoppingCartItems){
        List<ShoppingCartItem> shoppingCartItemsCopy = new ArrayList<>();

        for (ShoppingCartItem item:shoppingCartItems) {
            shoppingCartItemsCopy.add(new ShoppingCartItem(item.getBook(), item.getQuantity()));
        }

        return shoppingCartItemsCopy;
    }

    private int getDiscount(int differentBooksCount){
        int defaultDiscount = 0;

        for (BooksSetDiscount discount:discounts){
            if (differentBooksCount == discount.getDifferentCopies())
                return discount.getDiscount();
        }

        return defaultDiscount;
    }
}

