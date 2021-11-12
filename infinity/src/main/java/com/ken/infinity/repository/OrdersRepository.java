package com.ken.infinity.repository;

import com.ken.infinity.models.Orders;


import java.util.List;


public interface OrdersRepository {
    public void save(Orders orders);
    public Orders findByOrderId(int  id);
    public void updateOrder(Orders orders);
    public List<Orders> getOrders();
    public List<Orders> findOrderByUser(int id);

}
