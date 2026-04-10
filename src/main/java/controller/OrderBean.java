package controller;

import dao.OrderDao;
import dao.ProductDao;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import model.Order;
import model.Product;
import service.OrderService;

@Named("orderBean")
@ViewScoped
public class OrderBean implements Serializable {

    private int id;
    private int productId;
    private Order order;

    @Inject
    private LoginBean loginBean;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void load() {
        try {
            if (id > 0) {
                OrderDao orderDao = new OrderDao();
                order = orderDao.findById(id);
            }
        } catch (Exception e) {

        }
    }

    public String initOrder() {
        if (productId > 0) {
            return create(productId);
        }
        return null;
    }

    public String create(int productId) {
        System.out.println("CREATE CALLED");
        try {
            System.out.println("CREATE CALLED1");
            if (loginBean == null || loginBean.getCustomer() == null) {
                System.out.println("CREATE CALLED2");
                return "login?faces-redirect=true";
            }

            ProductDao productDao = new ProductDao();
            Product p = productDao.findById(productId);
            if (p == null) {
                System.out.println("CREATE CALLED3");
                return "index?faces-redirect=true";
            }

            this.order = new Order();
            this.order.setProductId(p.getId());
            this.order.setProductName(p.getName());
            this.order.setProductPrice(p.getPrice());
            this.order.setCustomerId(loginBean.getCustomer().getId());
            this.order.setProductKey(p.getProduct_key());
            this.order.setProductImage(p.getImage());
            OrderService orderService = new OrderService();
            int newId = orderService.processOrder(this.order);

            if (newId > 0) {
                System.out.println("CREATE CALLED4");
                this.order.setId(newId);
                return "checkout?faces-redirect=true&orderId=" + newId;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String confirmOrder() {
        try {
            if (this.order == null) {
                return null;
            }

            return "success?faces-redirect=true&orderId=" + this.order.getId();

        } catch (Exception e) {

            return null;
        }
    }
}
