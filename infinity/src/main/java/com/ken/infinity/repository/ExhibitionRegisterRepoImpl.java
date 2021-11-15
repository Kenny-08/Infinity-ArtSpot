package com.ken.infinity.repository;

import com.ken.infinity.models.ExhibitionRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ExhibitionRegisterRepoImpl implements ExhibitionRegisterRepo{

    JdbcTemplate jdbcTemplate;

    @Autowired
    public ExhibitionRegisterRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<ExhibitionRegister> exhibitionRegisterRowMapper = new RowMapper<ExhibitionRegister>() {
        @Override
        public ExhibitionRegister mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExhibitionRegister exhibitionRegister = new ExhibitionRegister();
            exhibitionRegister.setId(rs.getInt("id"));
            exhibitionRegister.setConfirm(rs.getString("confirm"));
            exhibitionRegister.setUser_id(rs.getInt("user_id"));
            exhibitionRegister.setExhibition_id(rs.getInt("exhibition_id"));
            return  exhibitionRegister;
        }
    };

    @Override
    public void save(ExhibitionRegister exhibitionRegister) {
        String query = "insert into exhibitionRegister(confirm, user_id, exhibition_id) values(?,?,?)";
        jdbcTemplate.update(query, exhibitionRegister.getConfirm(), exhibitionRegister.getUser_id(), exhibitionRegister.getExhibition_id());

    }
}
