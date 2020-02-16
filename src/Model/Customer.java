package Model;

import java.util.ArrayList;

public class Customer extends Person {
    private ArrayList<PurchasedProduct> purchaseHistory;
    private ArrayList<CreditCard> creditCardList;

    public Customer(String userID, String password, String firstName, String lastName, String middleName,
                    String suffix, String prefix){
        super(userID, password, firstName, lastName, middleName, suffix, prefix);
        setUserType(UserType.CUSTOMER);
        purchaseHistory = new ArrayList<>();
        creditCardList = new ArrayList<>();
    }
    //Add product to purchased list
    public boolean purchaseProduct(String serialNum, int quantity){
        for(PurchasedProduct product : purchaseHistory){
            if(product.equals(serialNum)){
                product.updateQuantity(quantity);
                return true;
            }
        }
        PurchasedProduct newPurchase = new PurchasedProduct(serialNum, quantity);
        if(newPurchase != null){
            purchaseHistory.add(newPurchase);
            return true;
        }
        else return false;

    }
    //Sets review boolean for customer purchased list
    boolean reviewProduct(String serialNum){
        boolean customerReviewedProduct = false;
        for(PurchasedProduct product : purchaseHistory){
            if(product.equals(serialNum)){
                customerReviewedProduct = product.reviewProduct();
            }
        }
        return customerReviewedProduct;
    }
    //Add a credit card
    public boolean addCreditCard(String cardNum, int expDate, int cvv){
        for(CreditCard card : creditCardList){
            if(card.equals(cardNum)) return false;
        }
        CreditCard newCard = new CreditCard(cardNum, expDate, cvv);
        if(newCard != null) {
            creditCardList.add(newCard);
            return true;
        }
        else return false;
    }
    //Remove a credit card
    public boolean removeCreditCard(String password, String cardNum){
        if(this.checkPassword(password)){
            for(CreditCard card : creditCardList){
                if(card.equals(cardNum)){
                    creditCardList.remove(card);
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
