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

    void reviewProduct(){
        if(!alreadyReviewed())
            reviewed = true;
    }
    boolean alreadyReviewed(){
        return reviewed;
    }
}
