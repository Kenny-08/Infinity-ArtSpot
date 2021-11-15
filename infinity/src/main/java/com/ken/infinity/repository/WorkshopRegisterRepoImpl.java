package com.ken.infinity.repository;

import com.ken.infinity.models.WorkshopRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@Repository
public class WorkshopRegisterRepoImpl implements WorkshopRegisterRepo{

    JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkshopRegisterRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<WorkshopRegister> workshopRegisterRowMapper =  new RowMapper<WorkshopRegister>() {
        @Override
        public WorkshopRegister mapRow(ResultSet rs, int rowNum) throws SQLException {

            WorkshopRegister workshopRegister = new WorkshopRegister();
            workshopRegister.setId(rs.getInt("id"));
            workshopRegister.setConfirm(rs.getString("confirm"));
            workshopRegister.setUser_id(rs.getInt("user_id"));
            workshopRegister.setWorkshop_id(rs.getInt("workshop_id"));
            return  workshopRegister;

        }
    };


    @Override
    public void save(WorkshopRegister workshopRegister) {
        String query = "insert into workshopRegister(confirm, user_id, workshop_id) values(?,?,?)";
        jdbcTemplate.update(query, workshopRegister.getConfirm(), workshopRegister.getUser_id(), workshopRegister.getWorkshop_id());

    }
}
