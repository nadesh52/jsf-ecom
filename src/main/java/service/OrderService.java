package service;

import dao.OrderDao;
import java.sql.Connection;
import model.Order;
import utils.DbConnect;

public class OrderService {

    public int processOrder(Order order) throws Exception {
        DbConnect db = new DbConnect();
        Connection connection = db.connect();

        try {
            connection.setAutoCommit(false);

            OrderDao orderDao = new OrderDao();
            int orderId = orderDao.create(connection, order);

            connection.commit();
            return orderId;

        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;

        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }
}
