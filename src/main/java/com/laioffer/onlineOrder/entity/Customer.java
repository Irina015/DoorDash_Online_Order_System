package com.laioffer.onlineOrder.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customers")
public class Customer implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private boolean enabled;  // 注销 deleted

    @OneToOne(cascade = CascadeType.ALL) // 所有关联操作同步，save-update, and delete
    @JoinColumn(unique = true) // 这个字段的值在这个表里不能重复，只能唯一
    private Cart cart;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
