package model;

public class Order {

    private int id;
    private int customerId;
    private int productId;
    private String customerName;
    private String productName;
    private String createdAt;
    private double productPrice;
    private String product_key;

    public Order() {
    }

    public Order(int id, int customerId, int productId, String customerName, String productName, String createdAt, double productPrice, String product_key) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.customerName = customerName;
        this.productName = productName;
        this.createdAt = createdAt;
        this.productPrice = productPrice;
        this.product_key = product_key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProduct_key() {
        return product_key;
    }

    public void setProduct_key(String product_key) {
        this.product_key = product_key;
    }

}
