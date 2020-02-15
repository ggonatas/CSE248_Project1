package Model;

import java.util.ArrayList;

public class Customer extends Person {
    private ArrayList<PurchasedProduct> purchaseHistory;
    private ArrayList<CreditCard> creditCardList;

    public Customer(String userID, String password, String firstName, String lastName, String middleName,
                    String suffix, String prefix){
        super(userID, password, firstName, lastName, middleName, suffix, prefix);
        setUserType(UserType.CUSTOMER);
    }



    boolean reviewProduct(String serialNumber){
        return true;
    }
}
