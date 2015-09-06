package gr.geomike.ted.api.db.entity;

import gr.geomike.ted.Views;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "USER")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
        @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
        //@NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
        @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
        @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
        @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
        @NamedQuery(name = "User.findByUsernameAndPassword",
                query = "SELECT u FROM User u WHERE u.username = :username and u.password = :password" )})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;

    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String email;
    private String socialSecurityNumber;
    private String address;
    private String phoneNumber;
    private String country;

    private Bidder bidder;
    private Seller seller;

    public User(){
    }
    public User(String username) {
        this.username = username;
    }
    public User(String username, String password,String role, String firstName, String lastName,  String email,
                String socialSecurityNumber, String address, String phoneNumber, Bidder bidder, Seller seller) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.socialSecurityNumber = socialSecurityNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.bidder = bidder;
        this.seller = seller;
    }

    @Id
    @Column(name = "USERNAME")
    @JsonView(Views.Basic.class)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "PASSWORD")
    @JsonView(Views.Basic.class)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "ROLE")
    @JsonView(Views.Basic.class)
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "FIRST_NAME")
    @JsonView(Views.Basic.class)
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LAST_NAME")
    @JsonView(Views.Basic.class)
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "EMAIL")
    @JsonView(Views.Basic.class)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "SOCIAL_SECURITY_NUMBER")
    @JsonView(Views.Basic.class)
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }
    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    @Basic
    @Column(name = "COUNTRY")
    @JsonView(Views.Basic.class)
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "ADDRESS")
    @JsonView(Views.Basic.class)
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "PHONE_NUMBER")
    @JsonView(Views.Basic.class)
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!getUsername().equals(user.getUsername())) return false;
        if (!getPassword().equals(user.getPassword())) return false;
        if (!getRole().equals(user.getRole())) return false;
        if (getFirstName() != null ? !getFirstName().equals(user.getFirstName()) : user.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(user.getLastName()) : user.getLastName() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        if (getSocialSecurityNumber() != null ? !getSocialSecurityNumber().equals(user.getSocialSecurityNumber()) : user.getSocialSecurityNumber() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(user.getAddress()) : user.getAddress() != null) return false;
        if (getPhoneNumber() != null ? !getPhoneNumber().equals(user.getPhoneNumber()) : user.getPhoneNumber() != null)
            return false;
        if (getBidder() != null ? !getBidder().equals(user.getBidder()) : user.getBidder() != null) return false;
        return !(getSeller() != null ? !getSeller().equals(user.getSeller()) : user.getSeller() != null);

    }

    @Override
    public int hashCode() {
        int result = getUsername().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getRole().hashCode();
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getSocialSecurityNumber() != null ? getSocialSecurityNumber().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        result = 31 * result + (getBidder() != null ? getBidder().hashCode() : 0);
        result = 31 * result + (getSeller() != null ? getSeller().hashCode() : 0);
        return result;
    }

    @OneToOne(mappedBy = "user")
    @JsonView(Views.User.class)
    public Bidder getBidder() {
        return bidder;
    }
    public void setBidder(Bidder bidder) {
        this.bidder = bidder;
    }

    @OneToOne(mappedBy = "user")
    @JsonView(Views.User.class)
    public Seller getSeller() {
        return seller;
    }
    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
