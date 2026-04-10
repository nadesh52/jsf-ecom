package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import utils.DbConnect;
import model.Order;

public class OrderDao {

    Connection connection;

    public OrderDao() {
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.connect();
    }

    public ArrayList<Order> findAll() throws Exception {
        String SQL = "SELECT * FROM purchase_order";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);

        ArrayList<Order> orders = new ArrayList<>();
        Order order = null;

        while (resultSet.next()) {
            order = new Order();
            order.setId(resultSet.getInt("id"));
            order.setCustomerId(resultSet.getInt("customer_id"));
            order.setProductId(resultSet.getInt("product_id"));
            order.setCreatedAt(resultSet.getString("created_at"));

            orders.add(order);
        }
        connection.close();
        statement.close();

        return orders;
    }

    public Order findById(int id) throws Exception {
        String SQL = "SELECT po.*, p.name AS product_name, p.price AS product_price, p.product_key AS productKey, p.image AS product_image\n"
                + "FROM purchase_order po\n"
                + "LEFT JOIN product p ON po.product_id = p.id\n"
                + "WHERE po.id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Order order = null;
        if (resultSet.next()) {
            order = new Order();
            order.setId(resultSet.getInt("id"));
            order.setCustomerId(resultSet.getInt("customer_id"));
            order.setProductId(resultSet.getInt("product_id"));
            order.setCreatedAt(resultSet.getString("created_at"));

            order.setProductName(resultSet.getString("product_name"));
            order.setProductPrice(resultSet.getDouble("product_price"));
            order.setProductKey(resultSet.getString("productKey"));
            order.setProductImage(resultSet.getString("product_image"));
        }
        return order;
    }

    public int create(Connection con, Order order) throws Exception {
        String SQL = "INSERT INTO purchase_order (product_id, customer_id, created_at) VALUES (?, ?, NOW())";

        PreparedStatement preparedStatement = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, order.getProductId());
        preparedStatement.setInt(2, order.getCustomerId());
        preparedStatement.executeUpdate();

        try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }

}
