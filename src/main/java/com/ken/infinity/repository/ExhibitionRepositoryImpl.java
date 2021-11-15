package com.ken.infinity.repository;

import com.ken.infinity.models.Exhibition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ExhibitionRepositoryImpl implements ExhibitionRepository{

    JdbcTemplate jdbcTemplate;

    @Autowired
    public ExhibitionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Exhibition> exhibitionRowMapper = new RowMapper<Exhibition>() {
        @Override
        public Exhibition mapRow(ResultSet rs, int rowNum) throws SQLException {
            Exhibition exhibition = new Exhibition();
            exhibition.setId(rs.getInt("id"));
            exhibition.setTitle(rs.getString("title"));
            exhibition.setDescription(rs.getString("description"));
            exhibition.setMode(rs.getString("mode"));
            exhibition.setDatetime(rs.getTimestamp("datetime"));
            exhibition.setVenue(rs.getString("venue"));
            exhibition.setTotal_seats(rs.getInt("total_seats"));
            exhibition.setRegistered_seats(rs.getInt("registered_seats"));
            exhibition.setImgUrl(rs.getString("imgUrl"));
            exhibition.setStatus(rs.getString("status"));
            return exhibition;
        }
    } ;

    @Override
    public List<Exhibition> getExhibitions() {
        String query = "select * from exhibition";
        List<Exhibition> exhibitions = jdbcTemplate.query(query, exhibitionRowMapper );
        System.out.println("Inside repo exhibitions " + exhibitions);
        return exhibitions;
    }

    @Override
    public void save(Exhibition exhibition) {
        String query = "insert into exhibition(title, description, mode, datetime, venue, total_seats, registered_seats, imgUrl, status) values(?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(query, exhibition.getTitle(), exhibition.getDescription(), exhibition.getMode(), exhibition.getDatetime(), exhibition.getVenue(), exhibition.getTotal_seats(), exhibition.getRegistered_seats(), exhibition.getImgUrl(), exhibition.getStatus());

    }

    @Override
    public Exhibition findExhibitionById(int id) {
        String query = "select * from exhibition where id='" + id + "'";
        return jdbcTemplate.queryForObject(query, exhibitionRowMapper);
    }

    @Override
    public void updateExhibitionSeats(int id, int seats) {
        seats = seats+1;
        String query = "update exhibition set registered_seats = '"+ seats +"' where id = '"+ id + "'";
        jdbcTemplate.update(query);
    }

    @Override
    public void updateExhibitionStatus(int id) {
        String query = "update exhibition set status = 'done' where id = '"+ id + "'";
        jdbcTemplate.update(query);
    }
}
