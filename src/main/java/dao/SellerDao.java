package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Seller;
import utils.DbConnect;

public class SellerDao {

    Connection connection;

    public SellerDao() {
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.connect();
    }

    public Seller findById(int id) throws Exception {
        String SQL = "SELECT * FROM seller WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Seller seller = null;
        if (resultSet.next()) {
            seller = new Seller();
            seller.setId(resultSet.getInt("id"));
            seller.setName(resultSet.getString("name"));
            seller.setCreatedAt(resultSet.getString("created_at"));
        }
        connection.close();
        return seller;
    }
}
