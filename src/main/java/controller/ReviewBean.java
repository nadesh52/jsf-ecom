package controller;

import dao.ReviewDao;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Review;

@Named("reviewBean")
@ViewScoped
public class ReviewBean implements Serializable {

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Review> getList() throws Exception {

        ReviewDao reviewDao = new ReviewDao();
        try {
            return reviewDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Review getReview(int id) throws Exception {
        ReviewDao reviewDao = new ReviewDao();
        try {
            return reviewDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
