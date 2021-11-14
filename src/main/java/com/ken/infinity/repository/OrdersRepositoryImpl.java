package com.ken.infinity.repository;

import com.ken.infinity.models.Artwork;
import com.ken.infinity.models.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class OrdersRepositoryImpl implements OrdersRepository{

    JdbcTemplate jdbcTemplate;

    @Autowired
    public OrdersRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Orders> ordersRowMapper = new RowMapper<Orders>() {
        @Override
        public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
            Orders orders = new Orders();
            orders.setId(rs.getInt("id"));
            orders.setEmail(rs.getString("email"));
            orders.setPrice(rs.getInt("price"));
            orders.setAddress(rs.getString("address"));
            orders.setStatus(rs.getString("status"));
            orders.setOrdered_at(rs.getTimestamp("ordered_at"));
            orders.setUser_id(rs.getInt("user_id"));
            orders.setArtwork_id(rs.getInt("artwork_id"));
            return orders;
        }
    };



    @Override
    public void save(Orders orders) {
        String query = "insert into orders(email, price, address, status, ordered_at, user_id, artwork_id) values(?,?,?,?,?,?,?)";
        jdbcTemplate.update(query, orders.getEmail(), orders.getPrice(), orders.getAddress(), orders.getStatus(), orders.getOrdered_at(), orders.getUser_id(), orders.getArtwork_id());

    }

    @Override
    public Orders findByOrderId(int id) {
        String query = "select * from orders where id='" + id + "'";
        return jdbcTemplate.queryForObject(query, ordersRowMapper);
    }

    @Override
    public void updateOrder(Orders orders) {

    }

    @Override
    public List<Orders> getOrders() {
        String query = "select * from orders";
        List<Orders> ordersList = jdbcTemplate.query(query, ordersRowMapper );
        return ordersList;
    }

    @Override
    public List<Orders> findOrderByUser(int id) {
        String query = "select * from orders where user_id='" + id + "'";
        List<Orders> ordersList = jdbcTemplate.query(query, ordersRowMapper);
        return ordersList;
    }
}
