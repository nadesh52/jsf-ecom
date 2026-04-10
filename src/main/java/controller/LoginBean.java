package controller;

import dao.CustomerDao;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
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
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

                ec.getSessionMap().put("currentUser", customer);
                ec.getSessionMap().put("userRole", customer.getRole());

                System.out.println("loged in: " + customer.getEmail());
                return "index?faces-redirect=true";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

}
