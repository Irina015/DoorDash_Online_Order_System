package com.laioffer.onlineOrder.service;

import com.laioffer.onlineOrder.entity.Cart;
import com.laioffer.onlineOrder.entity.Customer;
import com.laioffer.onlineOrder.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.laioffer.onlineOrder.dao.CartDao;

@Service
public class CartService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartDao cartDao;


    public Cart getCart() {
        // customer的entity里面也有getCart()
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication(); // 验证
        String username = loggedInUser.getName(); // 获取登录的username
        Customer customer = customerService.getCustomer(username); // 从username获取客户信息

        if (customer != null) {
            Cart cart = customer.getCart(); // 获取客户的cart, entity的方法
            double totalPrice = 0;
            for (OrderItem item : cart.getOrderItemList()) { // entity的方法
                totalPrice += item.getPrice() * item.getQuantity();
            }
            cart.setTotalPrice(totalPrice);
            return cart;
        }
        return new Cart();
    }

    public void cleanCart() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Customer customer = customerService.getCustomer(username);
        if (customer  != null) cartDao.removeAllCartItems(customer.getCart());
    }

}
