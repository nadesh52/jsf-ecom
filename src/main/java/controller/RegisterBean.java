package controller;

import dao.CustomerDao;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import model.Customer;

@Named("registerBean")
@RequestScoped
public class RegisterBean {

    private Customer customer = new Customer();

    public Customer getCustomer() {
        return customer;
    }

    public String register() {
        try {
            CustomerDao customerDao = new CustomerDao();
            customerDao.register(customer);

            return "login?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
