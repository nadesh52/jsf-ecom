package controller;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import dao.ProductDao;
import dao.ReviewDao;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.util.Base64;
import model.Product;
import model.Review;
import org.primefaces.event.FileUploadEvent;

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

    public void checkAdminAccess() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String role = (String) ec.getSessionMap().get("userRole");

        if (role == null || !role.equalsIgnoreCase("ADMIN")) {
            try {
                ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    public void handleFileUpload(FileUploadEvent event) {
        try {
            byte[] bytes = event.getFile().getContent();
            String contentType = event.getFile().getContentType();

            String base64Str = "data:" + contentType + ";base64," + Base64.getEncoder().encodeToString(bytes);

            this.product.setImage(base64Str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
