package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Customer extends Person implements Serializable {
    private ArrayList<PurchasedProduct> purchaseHistory;
    private CreditCard creditCard;
    private ShoppingCart shoppingCart;

    public Customer(String userID, String password, String firstName, String lastName, String middleName,
                    String suffix, String prefix, String email) {
        super(userID, password, firstName, lastName, middleName, suffix, prefix, email);
        setUserType(UserType.CUSTOMER);
        purchaseHistory = new ArrayList<>();
        shoppingCart = new ShoppingCart();
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
    //Get credit card
    public CreditCard getCreditCard(){
        return creditCard;
    }
    //Add a credit card
    public boolean addCreditCard(String cardNum, int expDate, int cvv){
        if( (cardNum.length() < 12 || cardNum.length() > 16) && (expDate < 0 || expDate > 9999) && (cvv < 0 || cvv > 999)){
            creditCard = new CreditCard(cardNum, expDate, cvv);
            return true;
        }
        return false;
    }

    //Get shopping cart
    public ShoppingCart getShoppingCart(){
        return shoppingCart;
    }
    //Deep copy method
    public Customer deepCopy(){
        Customer copy = new Customer(this.getUserID(), getPassword(), getFirstName(), getLastName(), getMiddleName(),
                getSuffix(), getPrefix(), getEmail());
        copy.setPasswordSalt(getPasswordSalt());
        copy.setUserType(UserType.CUSTOMER);
        PurchasedProduct [] historyArray = Arrays.copyOf(purchaseHistory.toArray(new PurchasedProduct [0]), purchaseHistory.size());
        ArrayList<PurchasedProduct> newHistory = new ArrayList<>(Arrays.asList(historyArray));
        copy.purchaseHistory = newHistory;
        copy.setAddress(getAddressObject());
        return copy;
    }
}
