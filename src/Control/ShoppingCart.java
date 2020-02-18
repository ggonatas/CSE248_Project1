package Control;

import Model.Product;

public class ShoppingCart extends Inventory{
    private double subtotal;
    private int itemCount;
    //Constructor
    ShoppingCart(){
        super();
        subtotal = 0.0;
        itemCount = 0;
    }
    //Add to cart method
    public boolean addToCart(Product product, int quantity){
        subtotal += product.getPrice() * quantity;
        itemCount =+ quantity;
        return super.addToInventory(product.deepCopy(), quantity);
    }
    //Remove from cart method
    public boolean removeFromCart(String serialNum){
        Product itemToRemove = getProduct(serialNum);
        int quantity = itemToRemove.getQuantity();
        subtotal -= itemToRemove.getPrice() * quantity;
        itemCount -= quantity;
        return super.removeFromInventory(serialNum);
    }
    //Update an items quantity
    public boolean updateQuantity(Product product, int quantity){

        return super.updateQuantity(product, quantity);
    }
}
