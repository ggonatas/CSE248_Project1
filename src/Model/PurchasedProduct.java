package Model;

public class PurchasedProduct {
    private String serialNum;
    private boolean reviewed;
    private int quantityBought;

    PurchasedProduct(String serialNum, int quantityBought){
        this.serialNum = serialNum;
        reviewed = false;
        this.quantityBought = quantityBought;
    }
    //Edit quantity
    void updateQuantity(int quantityBought){
        this.quantityBought += quantityBought;
    }
    //Set to reviewed
    boolean reviewProduct(){
        if(!alreadyReviewed())
            reviewed = true;
        return true;
    }
    //Check if product already reviewed
    boolean alreadyReviewed(){
        return reviewed;
    }
    //Equals method
    public boolean equals(String serialNum){
        return this.serialNum.equals(serialNum);
    }
}
