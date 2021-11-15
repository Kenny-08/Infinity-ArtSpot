package com.ken.infinity.repository;

import com.ken.infinity.models.Artwork;
import com.ken.infinity.models.Workshop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class WorkshopRepositoryImpl implements WorkshopRepository{

    JdbcTemplate jdbcTemplate;
    UserRepository userRepository;

    @Autowired
    public WorkshopRepositoryImpl(JdbcTemplate jdbcTemplate, UserRepository userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
    }

    private RowMapper<Workshop> workshopRowMapper = new RowMapper<Workshop>() {
        @Override
        public Workshop mapRow(ResultSet rs, int rowNum) throws SQLException {
            Workshop workshop = new Workshop();
            workshop.setId(rs.getInt("id"));
            workshop.setTitle(rs.getString("title"));
            workshop.setDescription(rs.getString("description"));
            workshop.setMode(rs.getString("mode"));
            workshop.setOrganizer_id(rs.getInt("organizer_id"));
            workshop.setDatetime(rs.getTimestamp("datetime"));
            workshop.setVenue(rs.getString("venue"));
            workshop.setTotal_seats(rs.getInt("total_seats"));
            workshop.setRegistered_seats(rs.getInt("registered_seats"));
            workshop.setImgUrl(rs.getString("imgUrl"));
            workshop.setStatus(rs.getString("status"));
            return workshop;

        }
    } ;

    @Override
    public List<Workshop> getWorkshops() {
        String query = "select * from workshop";
        List<Workshop> workshops = jdbcTemplate.query(query, workshopRowMapper );
        System.out.println("Inside repo " + workshops);
        return workshops;
    }

    @Override
    public void save(Workshop workshop) {
        String query = "insert into workshop(title, description, mode, organizer_id, datetime, venue, total_seats, registered_seats, imgUrl, status) values(?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(query, workshop.getTitle(), workshop.getDescription(), workshop.getMode(), workshop.getOrganizer_id(), workshop.getDatetime(), workshop.getVenue(), workshop.getTotal_seats(), workshop.getRegistered_seats(), workshop.getImgUrl(), workshop.getStatus());

    }

    @Override
    public Workshop findWorkshopById(int id) {
        String query = "select * from workshop where id='" + id + "'";
        return jdbcTemplate.queryForObject(query, workshopRowMapper);
    }

    @Override
    public List<Workshop> findWorkshopByOrganizer(int id) {

        String query = "select * from workshop where organizer_id='" + id + "'";
        List<Workshop> workshops = jdbcTemplate.query(query, workshopRowMapper);
        return workshops;
    }

    @Override
    public void updateWorkshopSeats(int id, int seats) {
        seats = seats+1;
        String query = "update workshop set registered_seats = '"+ seats +"' where id = '"+ id + "'";
        jdbcTemplate.update(query);
    }

    @Override
    public void updateWorkshopStatus(int id) {
        String query = "update workshop set status = 'done' where id = '"+ id + "'";
        jdbcTemplate.update(query);
    }
}
