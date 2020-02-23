package Model;

import java.io.*;
import java.util.HashMap;

public class ShoppingCart extends Inventory implements Serializable {
    private double subtotal;
    private int itemCount;
    //Constructor
    public ShoppingCart(){
        subtotal = 0.0;
        itemCount = 0;
    }

    //Add to cart method
    public void addToCart(Product product, int quantity){
        subtotal += product.getPrice() * quantity;
        itemCount =+ quantity;
        Product p = product;
        if(getInventory().containsKey(p.getSerialNum())){
            p = getInventory().get(p.getSerialNum());
        }
        else{
            p.updateQuantity(quantity);
        }
        addToInventory(p, quantity);
    }

    //Remove from cart method
    public boolean removeFromCart(String serialNum){
        Product itemToRemove = getProduct(serialNum);
        int quantity = itemToRemove.getQuantity();
        subtotal -= itemToRemove.getPrice() * quantity;
        itemCount -= quantity;
        return removeFromInventory(serialNum);
    }

}