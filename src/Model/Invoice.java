package Model;

import java.io.*;

public class Invoice {
    private String invoiceNum;
    private Address companyAddress;
    private Customer customer;
    private String name;
    private Address address;
    private double total;
    private final double NYS_TAX = 1.08625;

    public Invoice(Customer customer){
        invoiceNum = customer.getUserID() + 1;
        int i = 2;
        while( (new File(invoiceNum + ".txt")).exists() ){
            invoiceNum = customer.getUserID() + i;
            i++;
        }
        companyAddress = new Address("1103 E Jericho Tpke", "Huntington", "NY", "11743", "info@clothingstore.com");
        this.customer = customer;
        name = customer.getFullName();
        address = customer.getAddressObj();
        total = customer.getShoppingCart().getSubtotal() * NYS_TAX;
    }
    public Invoice(Customer customer, String name, String street, String city, String state, String zipCode, String email){
        invoiceNum = "guestuser" + 1;
        int i = 2;
        while( (new File(invoiceNum + ".txt")).exists() ){
            invoiceNum = "guestuser" + i;
            i++;
        }
        companyAddress = new Address("1103 E Jericho Tpke", "Huntington", "NY", "11743", "info@clothingstore.com");
        this.customer = customer;
        this.name = name;
        address = new Address(street, city, state, zipCode, email);
        total = customer.getShoppingCart().getSubtotal() * NYS_TAX;
    }
    //Get invoice number
    public String getInvoiceNum(){ return invoiceNum; }
    //Generate an invoice file
    public void generateInvoice(){
        try {
            PrintWriter writeInvoice = new PrintWriter(invoiceNum + ".txt");
            writeInvoice.println("Clothing Co.\n" + companyAddress.getFullAddress() + "\nInvoice Number: " + invoiceNum);
            writeInvoice.println("\n" + name + "\n" + address.getFullAddress() + "\n\nORDER:");
            for(Product product : customer.getShoppingCart().getInventory().values()){
                writeInvoice.println(product.getProductInfo() + "\n");
            }
            writeInvoice.printf("Subtotal: $%.2f\n", customer.getShoppingCart().getSubtotal());
            writeInvoice.printf("Taxes: $%.2f\n", (customer.getShoppingCart().getSubtotal() * (NYS_TAX - 1)) );
            writeInvoice.printf("Total: $%.2f\n", total);
            writeInvoice.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
