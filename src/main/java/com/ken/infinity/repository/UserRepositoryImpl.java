package com.ken.infinity.repository;

import com.ken.infinity.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepositoryImpl implements UserRepository {
    JdbcTemplate jdbcTemplate;
    private RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {

            User user = new User();
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setEmail(rs.getString("email"));
            user.setPhoneNo(rs.getString("phoneNo"));
            user.setRole(rs.getString("role"));
            user.setPassword(rs.getString("password"));
            user.setAddress(rs.getString("address"));
            return user;
        }
    };

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findByEmail(String email) {
        String query = "select * from user where email='" + email + "'";
        return jdbcTemplate.queryForObject(query, userRowMapper);
    }

    @Override
    public boolean userExists(String email) {
        String query = "select count(*) from user where email='" + email + "'";
        int cnt = jdbcTemplate.queryForObject(query, Integer.class);
        if (cnt > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void save(User user) {
        String query = "insert into user(firstName,lastName,email,phoneNo,password,address, role) values(?,?,?,?,?,?,?)";
        System.out.println(user.getPassword());
        jdbcTemplate.update(query, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNo(), user.getPassword(), user.getAddress(), user.getRole());
    }

    @Override
    public User findByUserId(int id) {
        String query = "select * from user where id='" + id + "'";
        return jdbcTemplate.queryForObject(query, userRowMapper);
    }


}
