package Model;

import View.MainApplication;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;

public class Inventory implements Serializable {
    private HashMap<String, Product> inventory;

    public Inventory(){
        inventory =  new HashMap<>();
    }

    public HashMap<String, Product> getInventory(){
        return inventory;
    }

    public void setInventory(HashMap<String, Product> inventory) {
        this.inventory = inventory;
    }

    //Add an item to inventory
    public boolean addToInventory(Product product, int quantity){
        if(inventory.containsKey(product.getSerialNum())){
            return updateQuantity(product, MainApplication.inventory.getProduct(product.getSerialNum()).getQuantity() + quantity);
        }
        else{
            inventory.put(product.getSerialNum(), product);
        }
        return true;
    }
    //Remove item from inventory
    public boolean removeFromInventory(String serialNum){
        Product productToRemove = inventory.get(serialNum);
        if(productToRemove.equals(serialNum)) {
            inventory.remove(serialNum);
            return true;
        }
        else {
            return false;
        }
    }
    //Update quantity of a product
    public boolean updateQuantity(Product product, int quantity){
        Product productToUpdate = inventory.get(product.getSerialNum());
        if(productToUpdate != null){
            productToUpdate.updateQuantity(quantity);
            return true;
        }
        else return false;
    }
    //Get a product from the inventory
    public Product getProduct(String serialNum){
        return inventory.get(serialNum);
    }
    //Search for a product by name
    public Product getProductByName(String name){
        Collection<Product> products = inventory.values();
        for(Product product : products){
            if(product != null){
                if(product.getName().equals(name)){
                    return product.deepCopy();
                }
            }
            else return null;
        }
        return null;
    }

    //Save to file
    public void saveToFile(){
        try {
            FileOutputStream saveFile = new FileOutputStream("inventory.txt");
            ObjectOutputStream save = new ObjectOutputStream(saveFile);
            save.writeObject(inventory);
            save.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Load from file
    public static Inventory loadFromFile(){
        Inventory newInventory = new Inventory();
        try {
            FileInputStream fileInput = new FileInputStream("inventory.txt");
            ObjectInputStream inputStream = new ObjectInputStream(fileInput);
            newInventory.setInventory((HashMap<String, Product>) inputStream.readObject());
            inputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return newInventory;
    }
}
