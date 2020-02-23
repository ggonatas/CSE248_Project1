package Model;

import java.io.*;

public class Invoice {
    private String invoiceNum;
    private Address companyAddress;
    private Customer customer;
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
        total = customer.getShoppingCart().getSubtotal() * NYS_TAX;
    }
    //Generate an invoice file
    public void generateInvoice(){
        try {
            PrintWriter writeInvoice = new PrintWriter(invoiceNum + ".txt");
            writeInvoice.println("Clothing Co.\n" + companyAddress.getFullAddress() + "\n" + invoiceNum);
            writeInvoice.println("\n" + customer.getFullName() + "\n" + customer.getAddress() + "\n\nORDER:");
            writeInvoice.println("SerialNum |  Name  |  Qty.  |  Unit Price  |   Cost");
            writeInvoice.println("----------------------------------------------------");
            for(Product product : customer.getShoppingCart().getInventory().values()){
                writeInvoice.println(product.getProductInfo() + "\n");
            }
            writeInvoice.println("Subtotal: $" + customer.getShoppingCart().getSubtotal());
            writeInvoice.println("Taxes: $" + (customer.getShoppingCart().getSubtotal() * (NYS_TAX - 1)) );
            writeInvoice.println("Total: $" + total);
            writeInvoice.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
