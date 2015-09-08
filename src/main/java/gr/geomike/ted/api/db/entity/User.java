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

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;
        if (!password.equals(user.password)) return false;
        if (!role.equals(user.role)) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (socialSecurityNumber != null ? !socialSecurityNumber.equals(user.socialSecurityNumber) : user.socialSecurityNumber != null)
            return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) return false;
        if (country != null ? !country.equals(user.country) : user.country != null) return false;
        if (!bidder.equals(user.bidder)) return false;
        return seller.equals(user.seller);

    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (socialSecurityNumber != null ? socialSecurityNumber.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + bidder.hashCode();
        result = 31 * result + seller.hashCode();
        return result;
    }*/

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonView(Views.User.class)
    public Bidder getBidder() {
        return bidder;
    }
    public void setBidder(Bidder bidder) {
        this.bidder = bidder;
    }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonView(Views.User.class)
    public Seller getSeller() {
        return seller;
    }
    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
