package controller;

import dao.CustomerDao;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import model.Customer;

@Named("customerBean")
@ViewScoped
public class CustomerBean implements Serializable {

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() throws Exception {
        CustomerDao customerDao = new CustomerDao();
        try {
            return customerDao.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
