package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Product {
    private String serialNum, name, description, color;
    private double price;
    private ArrayList<String> tags;
    private float score;
    private File image;

    public Product(String serialNum, String name, String description, String color, double price, ArrayList<String> tags, float score, File pic) {
        this.serialNum = serialNum;
        this.name = name;
        this.description = description;
        this.color = color;
        this.price = price;
        this.tags = tags;
        this.score = score;
        setImage(pic);
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

    public void setImage(File pic) {
        try {
            BufferedImage bi = ImageIO.read(pic);
            ImageIO.write(bi, "jpg", new File("productImages/" + name + ".jpg"));
        }
        catch (IOException e) {
        }
    }

    public Product deepCopy() {
        return null;
    }
}
