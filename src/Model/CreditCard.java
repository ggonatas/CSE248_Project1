package Model;

public class CreditCard {
    private String cardNum;
    private int expDate;
    private int cvv;

    public CreditCard(String cardNum, int expDate, int cvv) {
        this.cardNum = encryptCardNum(cardNum);
        this.expDate = encryptInt(expDate);
        this.cvv = encryptInt(cvv);
    }

    String getCardNum(){ return decryptCardNum(); }
    int getExpDate(){ return decryptInt(expDate); }
    int getCvv(){ return decryptInt(cvv);}

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
}
