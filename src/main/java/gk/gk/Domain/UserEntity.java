package gk.gk.Domain;

import gk.gk.Domain.Address.AddressEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity(name="udemy")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -563827228549939787L;
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, length = 120)
    private String email;

    @Column(nullable = false)
    private String encryptedPassword;

    @OneToMany(mappedBy = "userBelongTo", cascade = CascadeType.ALL)
    private List<AddressEntity> addresses;

    public UserEntity() {
    }

    public UserEntity(String firstName, String lastName, String userId, String email, String encryptedPassword, List<AddressEntity> addresses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.email = email;
        this.encryptedPassword = encryptedPassword;
        this.addresses = addresses;
    }

    public List<AddressEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressEntity> addresses) {
        this.addresses = addresses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

}
