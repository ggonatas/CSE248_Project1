package Control;

import Model.Product;

import java.util.ArrayList;

public class InventoryTest {

    public static void main(String [] args){
        Inventory inventory = new Inventory();
        Product p1 = new Product("123", "Shirt", "SWag", "Black",
                10.0, new ArrayList<>(), 0.0f, "pic1.jpg", 1);
        Product p2 = new Product("124", "Sweatshirt", "SWag", "Red",
                20.0, new ArrayList<>(), 0.0f, "pic2.jpg", 1);

        inventory.addToInventory(p1, p1.getQuantity());
        inventory.addToInventory(p2, p2.getQuantity());

        inventory.saveToFile();

        Inventory newInventory = Inventory.loadFromFile();
        System.out.println(newInventory.getProduct("124").getName());
    }
}
