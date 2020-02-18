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
        setImage(pic);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public void setImage(String pic) {
        File image = new File(pic);
        try {
            BufferedImage bi = ImageIO.read(image);
            ImageIO.write(bi, "jpg", new File("productImages/" + name + ".jpg"));
        }
        catch (IOException e) {
        }
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
