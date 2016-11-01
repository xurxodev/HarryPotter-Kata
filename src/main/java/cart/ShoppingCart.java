package cart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();

    private PriceCalculator priceCalculator;

    public ShoppingCart(PriceCalculator priceCalculator){
        this.priceCalculator = priceCalculator;
    }

    public void Add(Book book) {

        ShoppingCartItem existedItem = null;

        for (ShoppingCartItem item:shoppingCartItems){
            if (item.getBook().equals(book))
                existedItem = item;
        }

        if (existedItem != null)
            existedItem.changeQuantity(existedItem.getQuantity() + 1);
        else
            shoppingCartItems.add(new ShoppingCartItem(book, 1));
    }

    public double getTotalPrice(){
        return priceCalculator.calculate(shoppingCartItems);
    }

    public interface PriceCalculator {
        Double calculate(List<ShoppingCartItem> shoppingCartItems);
    }
}
