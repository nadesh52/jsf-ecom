package model;

public class Product {

    private int id;
    private int sellerId;
    private double price;
    private String name;
    private String description;
    private String image;
    private String product_key;
    private String sellerName;
    private int rating;

    public Product() {
    }

    public Product(int id, int sellerId, double price, String name, String description, String image, String product_key, String sellerName, int rating) {
        this.id = id;
        this.sellerId = sellerId;
        this.price = price;
        this.name = name;
        this.description = description;
        this.image = image;
        this.product_key = product_key;
        this.sellerName = sellerName;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProduct_key() {
        return product_key;
    }

    public void setProduct_key(String product_key) {
        this.product_key = product_key;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
