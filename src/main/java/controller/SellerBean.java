package controller;

import dao.SellerDao;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import model.Seller;

@Named("sellerBean")
@ViewScoped
public class SellerBean implements Serializable {

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Seller getSeller(int id) throws Exception {
        SellerDao sellerDao = new SellerDao();
        try {
            return sellerDao.findById(id);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
