package controller;

import dao.OrderDao;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import model.Order;

@Named("orderBean")
@ViewScoped
public class OrderBean implements Serializable {

    private int id;
    private Order order;
    
    @Inject
    LoginBean loginBean;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void load() throws Exception {
        try {
            OrderDao orderDao = new OrderDao();
            order = orderDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String create(int productId) {
        order = new Order();
        order.setProductId(productId);
        order.setCustomerId(loginBean.getCustomer().getId());
       
        try {
            OrderDao orderDao = new OrderDao();
            int orderId = orderDao.create(order);
            order.setId(orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "checkout?faces-redirect=true&orderId=" + order.getId();
    }
}
