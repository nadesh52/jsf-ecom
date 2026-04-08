package model;

public class Review {

    private int id;
    private int rating;
    private int productId;
    private int customerId;
    private String productName;
    private String customerName;
    private String createdAt;

    public Review() {
    }

    public Review(int id, int rating, int productId, int customerId, String productName, String customerName, String createdAt) {
        this.id = id;
        this.rating = rating;
        this.productId = productId;
        this.customerId = customerId;
        this.productName = productName;
        this.customerName = customerName;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
