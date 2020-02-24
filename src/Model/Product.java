package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {
    private String serialNum, name, description, color, pic;
    private double price;
    private ArrayList<String> tags;
    private float score;
    private int quantity;

    public Product(String serialNum, String name, String description, String color, double price, ArrayList<String> tags, float score, String pic, int quantity) {
        this.serialNum = serialNum;
        this.name = name;
        this.description = description;
        this.color = color;
        this.price = price;
        this.tags = tags;
        this.score = score;
        this.pic = pic;
        this.quantity = quantity;
    }

    public int getQuantity() {   return quantity;    }

    public void updateQuantity(int quantity) {  this.quantity = quantity;    }

    public String getSerialNum() { return serialNum;    }

    public String getPic() { return pic;    }

    public void setPic(String pic) { this.pic = pic; }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Product deepCopy() {
        ArrayList<String> tagss = new ArrayList<>();
        for(String tag: tags) {
            tagss.add(new String(tag.toCharArray()));
        }

        return new Product(new String(serialNum.toCharArray()), new String(name.toCharArray()), new String(description.toCharArray()), new String(color.toCharArray()),
                price, tagss, score, new String(pic.toCharArray()), quantity);
    }

    public boolean equals(String serialNum) {
        return this.serialNum.equals(serialNum);
    }
}
