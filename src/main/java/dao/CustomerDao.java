package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DbConnect;
import model.Customer;

public class CustomerDao {

    private Connection connection;

    public CustomerDao() {
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.connect();
    }

    public Customer findById(int id) throws Exception {
        String SQL = "SELECT * FROM customer WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Customer customer = null;
        if (resultSet.next()) {
            customer = new Customer();
            customer.setId(resultSet.getInt("id"));
            customer.setName(resultSet.getString("name"));
            customer.setAddress(resultSet.getString("address"));
            customer.setTelephone(resultSet.getString("telephone"));
            customer.setCreatedAt(resultSet.getString("created_at"));
            customer.setEmail(resultSet.getString("email"));
            customer.setPassword(resultSet.getString("password"));
        }
        connection.close();
        return customer;
    }

    public Customer login(String email, String password) throws Exception {
        String SQL = "SELECT * FROM customer WHERE email=? AND password=?";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        Customer customer = null;

        if (resultSet.next()) {
            customer = new Customer();
            customer.setId(resultSet.getInt("id"));
            customer.setName(resultSet.getString("name"));

            customer.setEmail(resultSet.getString("email"));
            customer.setRole(resultSet.getString("role"));
        }
        resultSet.close();
        preparedStatement.close();
        return customer;
    }

    public void register(Customer customer) throws Exception {
        String SQL = "INSERT INTO customer (name, email, password, address, telephone, created_at) VALUES (?, ?, ?, ?, ?, NOW())";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getEmail());
        preparedStatement.setString(3, customer.getPassword());
        preparedStatement.setString(4, customer.getAddress());
        preparedStatement.setString(5, customer.getTelephone());

        preparedStatement.executeUpdate();

        connection.close();

    }
}
