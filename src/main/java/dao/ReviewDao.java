package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import utils.DbConnect;
import model.Review;

public class ReviewDao {

    Connection connection;

    public ReviewDao() {
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.connect();
    }

    public ArrayList<Review> findAll() throws Exception {
        String SQL = "SELECT * FROM review";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);

        ArrayList<Review> reviews = new ArrayList<>();
        Review review = null;
        while (resultSet.next()) {
            review = new Review();
            review.setId(resultSet.getInt("id"));
            review.setRating(resultSet.getInt("rating"));
            review.setProductId(resultSet.getInt("product_id"));
            review.setCustomerId(resultSet.getInt("customer_id"));
            review.setCreatedAt(resultSet.getString("created_at"));

            reviews.add(review);
        }
        connection.close();
        statement.close();
        return reviews;

    }

    public Review findById(int id) throws Exception {
        String SQL = "SELECT * FROM review WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Review review = null;
        if (resultSet.next()) {
            review = new Review();
            review.setId(resultSet.getInt("id"));
            review.setRating(resultSet.getInt("rating"));
            review.setProductId(resultSet.getInt("product_id"));
            review.setCustomerId(resultSet.getInt("customer_id"));
            review.setCreatedAt(resultSet.getString("created_at"));
        }

        connection.close();
        return review;
    }

    public ArrayList<Review> findByProductId(int productId) throws Exception {
        String SQL = "SELECT review.*, customer.name AS customer_name\n"
                + "FROM review\n"
                + "JOIN customer ON review.customer_id = customer.id\n"
                + "WHERE review.product_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, productId);

        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Review> reviews = new ArrayList<>();

        while (resultSet.next()) {
            Review review = new Review();

            review.setId(resultSet.getInt("id"));
            review.setRating(resultSet.getInt("rating"));
            review.setProductId(resultSet.getInt("product_id"));
            review.setCustomerId(resultSet.getInt("customer_id"));
            review.setCustomerName(resultSet.getString("customer_name"));
            review.setCreatedAt(resultSet.getString("created_at"));

            reviews.add(review);
        }

        connection.close();
        return reviews;
    }
}
