package com.ken.infinity.services;


import com.ken.infinity.models.Artwork;
import com.ken.infinity.models.Orders;
import com.ken.infinity.models.User;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface OrdersService {

    void save(Orders orders, User user, Artwork artwork);
    List<Orders> getOrders();
    List<Orders> findOrdersByUser(int id);
    void updateOrders(Orders orders);
    Orders findByOrderId(int id);

}
