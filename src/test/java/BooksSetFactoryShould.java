import cart.ShoppingCartItem;
import org.junit.Before;
import org.junit.Test;
import price.BooksSet;
import price.BooksSetDiscount;
import price.BooksSetFactory;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BooksSetFactoryShould {

    BooksSetFactory booksSetFactory;

    @Before
    public void setup(){
        List<BooksSetDiscount> byDifferentCopiesDiscountList = new ArrayList<>();

        byDifferentCopiesDiscountList.add(new BooksSetDiscount(2,5));
        byDifferentCopiesDiscountList.add(new BooksSetDiscount(3,10));
        byDifferentCopiesDiscountList.add(new BooksSetDiscount(4,20));
        byDifferentCopiesDiscountList.add(new BooksSetDiscount(5,25));

        booksSetFactory = new BooksSetFactory(byDifferentCopiesDiscountList);
    }


    @Test
    public void extract_one_set_of_4_books_when_cart_items_are_one_copy_for_first_four_books(){

        List<ShoppingCartItem> shoppingCartItems = GivenAShoppingCartItems(1,1,1,1,0);

        List<BooksSet> booksSets = booksSetFactory.getDifferentBooksSetsWithMaxTotalDiscount(shoppingCartItems);

        assertThat(booksSets.size(), is(1));
        assertThat(booksSets.get(0).getBooks().size(), is(4));
    }

    @Test
    public void extract_two_set_of_4_books_when_cart_items_are_two_copies_for_first_three_books_and_one_for_rest(){

        List<ShoppingCartItem> shoppingCartItems = GivenAShoppingCartItems(2,2,2,1,1);

        List<BooksSet> booksSets = booksSetFactory.getDifferentBooksSetsWithMaxTotalDiscount(shoppingCartItems);

        assertThat(booksSets.size(), is(2));
        assertThat(booksSets.get(0).getBooks().size(), is(4));
        assertThat(booksSets.get(1).getBooks().size(), is(4));

    }

    private List<ShoppingCartItem> GivenAShoppingCartItems(int quantityBookI, int quantityBookII, int quantityBookIII,
                                                           int quantityBookIV,int quantityBookV ){
        List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();

        if (quantityBookI > 0)
            shoppingCartItems.add(new ShoppingCartItem(Catalog.GivenAHarryPotterIBook(),quantityBookI));

        if (quantityBookII > 0)
            shoppingCartItems.add(new ShoppingCartItem(Catalog.GivenAHarryPotterIIBook(),quantityBookII));

        if (quantityBookIII > 0)
            shoppingCartItems.add(new ShoppingCartItem(Catalog.GivenAHarryPotterIIIBook(),quantityBookIII));

        if (quantityBookIV > 0)
            shoppingCartItems.add(new ShoppingCartItem(Catalog.GivenAHarryPotterIVBook(),quantityBookIV));

        if (quantityBookV > 0)
            shoppingCartItems.add(new ShoppingCartItem(Catalog.GivenAHarryPotterVBook(),quantityBookV));

        return shoppingCartItems;
    }

}
