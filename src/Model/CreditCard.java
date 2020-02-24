package Model;

import java.io.Serializable;

public class CreditCard implements Serializable {
    private String cardNum;
    private int expDate;
    private int cvv;

    public CreditCard(String cardNum, int expDate, int cvv) {
        this.cardNum = encryptCardNum(cardNum);
        this.expDate = encryptInt(expDate);
        this.cvv = encryptInt(cvv);
    }

    public String getCardNum(){ return decryptCardNum(); }
    public int getExpDate(){ return decryptInt(expDate); }
    public int getCvv(){ return decryptInt(cvv);}

    private String encryptCardNum(String cardNum) {
        return cardNum;
    }
    private String decryptCardNum(){
        return cardNum;
    }
    private int encryptInt(int i){
        return i;
    }
    private int decryptInt(int i){
        return i;
    }
    //Equals method
    boolean equals(String cardNum){
        return this.cardNum.equals(cardNum);
    }
}
