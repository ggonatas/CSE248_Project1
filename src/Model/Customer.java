package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Customer extends Person implements Serializable {
    private ArrayList<PurchasedProduct> purchaseHistory;
    private ArrayList<CreditCard> creditCardList;

    public Customer(String userID, String password, String firstName, String lastName, String middleName,
                    String suffix, String prefix, String email){
        super(userID, password, firstName, lastName, middleName, suffix, prefix, email);
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
    //Deep copy method
    public Customer deepCopy(){
        Customer copy = new Customer(this.getUserID(), getPassword(), getFirstName(), getLastName(), getMiddleName(),
                getSuffix(), getPrefix(), getEmail());
        copy.setUserType(UserType.CUSTOMER);
        PurchasedProduct [] historyArray = Arrays.copyOf(purchaseHistory.toArray(new PurchasedProduct [0]), purchaseHistory.size());
        ArrayList<PurchasedProduct> newHistory = new ArrayList<>(Arrays.asList(historyArray));
        CreditCard [] cardArray = Arrays.copyOf(creditCardList.toArray(new CreditCard [0]), creditCardList.size());
        ArrayList<CreditCard> newCardList = new ArrayList<>(Arrays.asList(cardArray));
        copy.purchaseHistory = newHistory;
        copy.creditCardList = newCardList;
        copy.setAddress(getAddressObject());
        return copy;
    }
}
