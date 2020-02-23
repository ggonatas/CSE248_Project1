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
        Product p = product.deepCopy();
        addToInventory(p, quantity);
        updateQuantity(p,quantity);
    }

    //Remove from cart method
    public boolean removeFromCart(String serialNum){
        Product itemToRemove = getProduct(serialNum);
        int quantity = itemToRemove.getQuantity();
        subtotal -= itemToRemove.getPrice() * quantity;
        itemCount -= quantity;
        return removeFromInventory(serialNum);
    }


    public void saveToFile(){
        try {
            FileOutputStream saveFile = new FileOutputStream("shoppingcart.sav");
            ObjectOutputStream save = new ObjectOutputStream(saveFile);
            save.writeObject(getInventory());
            save.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ShoppingCart loadFromFile(){
        ShoppingCart newShoppingCart = new ShoppingCart();
        try {
            FileInputStream fileInput = new FileInputStream("shoppingcart.sav");
            ObjectInputStream inputStream = new ObjectInputStream(fileInput);
            newShoppingCart.setInventory((HashMap<String, Product>) inputStream.readObject());
            inputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return newShoppingCart;
    }
}
