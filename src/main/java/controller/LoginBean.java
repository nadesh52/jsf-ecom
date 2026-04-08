package controller;

import dao.CustomerDao;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import model.Customer;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String email;
    private String password;
    private Customer customer;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        try {
            CustomerDao customerDao = new CustomerDao();
            customer = customerDao.login(email, password);

            if (customer != null) {
                System.out.println("loged in");
                return "index?faces-redirect=true";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String logout() {
        customer = null;
        return "index?faces-redirect=true";
    }

}
