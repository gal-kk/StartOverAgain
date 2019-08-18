package gk.gk.Domain.Address;

import gk.gk.Domain.UserEntity;

import javax.persistence.*;

@Entity
public class AddressEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String addressId;

    private String city;

    private String country;

    private String streetName;

    private String postalCode;

    private String type;

    @ManyToOne
    @JoinColumn(name = "udemy_id")
    private UserEntity userBelongTo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public UserEntity getUserBelongTo() {
        return userBelongTo;
    }

    public void setUserBelongTo(UserEntity userBelongTo) {
        this.userBelongTo = userBelongTo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}