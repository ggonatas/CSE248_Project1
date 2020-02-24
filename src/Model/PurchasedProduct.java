package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class PurchasedProduct implements Serializable {
    private String serialNum;
    private int quantityBought;
    private ArrayList<String> invoiceNumbers;

    PurchasedProduct(String serialNum, int quantityBought, String invoiceNumber){
        this.serialNum = serialNum;
        this.quantityBought = quantityBought;
        invoiceNumbers = new ArrayList<>();
        invoiceNumbers.add(invoiceNumber);
    }
    //Edit quantity
    void updateQuantity(int quantityBought){
        this.quantityBought += quantityBought;
    }
    //Equals method
    public boolean equals(String serialNum){
        return this.serialNum.equals(serialNum);
    }
}
