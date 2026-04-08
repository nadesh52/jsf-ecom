package controller;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import dao.ProductDao;
import dao.ReviewDao;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import model.Product;
import model.Review;

@Named("productBean")
@ViewScoped
public class ProductBean implements Serializable {

    private int id;
    private Product product = new Product();
    private ArrayList<Review> reviews;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public Product getProduct() {
        return product;
    }

    public void load() {
        try {
            ProductDao productDAO = new ProductDao();
            product = productDAO.findById(id);

            ReviewDao reviewDAO = new ReviewDao();
            reviews = reviewDAO.findByProductId(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> getList() {

        ProductDao productDao = new ProductDao();
        try {
            return productDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String create() throws Exception {
        try {
            ProductDao productDao = new ProductDao();
            productDao.create(product);
            return "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
