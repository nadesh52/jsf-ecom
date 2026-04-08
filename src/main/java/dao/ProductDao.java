package dao;

import model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import utils.DbConnect;

public class ProductDao {

    private Connection connection;

    public ProductDao() {
        DbConnect db = new DbConnect();
        connection = db.connect();
    }

    public ArrayList<Product> findAll() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        Product product = null;

        Statement statement = connection.createStatement();
        String SQL = "SELECT product.*, seller.name AS seller_name, review.rating AS review_rating FROM product JOIN seller ON product.seller_id = seller.id LEFT JOIN review ON product.id = review.product_id";
        ResultSet resultSet = statement.executeQuery(SQL);

        while (resultSet.next()) {
            product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getDouble("price"));
            product.setDescription(resultSet.getString("description"));
            product.setImage(resultSet.getString("image"));
            product.setProduct_key("product_key");
            product.setSellerId(resultSet.getInt("seller_id"));

            product.setSellerName(resultSet.getString("seller_name"));
            product.setRating(resultSet.getInt("review_rating"));
            products.add(product);
        }

        resultSet.close();
        statement.close();

        return products;
    }

    public Product findById(int id) throws Exception {
        String SQL = "SELECT product.*, seller.name AS seller_name FROM product JOIN seller ON product.seller_id = seller.id WHERE product.id=?";
        PreparedStatement prepareStatement = connection.prepareStatement(SQL);
        prepareStatement.setInt(1, id);
        ResultSet resultSet = prepareStatement.executeQuery();

        Product product = null;
        if (resultSet.next()) {
            product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getDouble("price"));
            product.setDescription(resultSet.getString("description"));
            product.setImage(resultSet.getString("image"));
            product.setProduct_key("product_key");
            product.setSellerId(resultSet.getInt("seller_id"));
            product.setSellerName(resultSet.getString("seller_name"));
        }
        connection.close();
        return product;
    }

    public void create(Product product) throws Exception {
        String SQL = "INSERT INTO product (name, price, description, image, product_key, seller_id) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setString(3, product.getDescription());
        preparedStatement.setString(4, product.getImage());
        preparedStatement.setString(5, product.getProduct_key());
        preparedStatement.setInt(6, product.getSellerId());

        preparedStatement.executeUpdate();

        connection.close();
    }
}
