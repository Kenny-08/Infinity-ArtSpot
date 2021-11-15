package com.ken.infinity.repository;

import com.ken.infinity.models.Artwork;
import com.ken.infinity.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ArtworkRepositoryImpl implements ArtworkRepository {
    JdbcTemplate jdbcTemplate;
    UserRepository userRepository;

    @Autowired
    public ArtworkRepositoryImpl(JdbcTemplate jdbcTemplate, UserRepository userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
    }

    private RowMapper<Artwork> artworkRowMapper = new RowMapper<Artwork>() {

        public Artwork mapRow(ResultSet rs, int rowNum) throws SQLException{
            Artwork artwork = new Artwork();
            artwork.setId(rs.getInt("id"));
            artwork.setTitle(rs.getString("title"));
            artwork.setDescription(rs.getString("description"));
            artwork.setCategory(rs.getString("category"));
            artwork.setLabel(rs.getString("label"));
            artwork.setPrice(rs.getInt("price"));
            artwork.setLikes(rs.getInt("likes"));
            artwork.setOwner_id(rs.getInt("owner_id"));
            artwork.setImgUrl(rs.getString("imgUrl"));
            return artwork;
        }
    };

    @Override
    public List<Artwork> getArtworks() {
        String query = "select * from artwork";
        List<Artwork> artworks = jdbcTemplate.query(query, artworkRowMapper );
        System.out.println("Inside repo " + artworks);
        return artworks;
    }

    @Override
    public List<Artwork> findAllArtworks() {
        return null;
    }

    @Override
    public void save(Artwork artwork) {
        String query = "insert into artwork(title, description, category, label, price, likes, imgUrl, owner_id) values(?,?,?,?,?,?,?,?)";
        System.out.println(artwork.getOwner_id());
        jdbcTemplate.update(query, artwork.getTitle(), artwork.getDescription(), artwork.getCategory(), artwork.getLabel(), artwork.getPrice(), artwork.getLikes(), artwork.getImgUrl(), artwork.getOwner_id());
    }

    @Override
    public Artwork findArtworkById(int id) {
        String query = "select * from artwork where id='" + id + "'";
        return jdbcTemplate.queryForObject(query, artworkRowMapper);
    }

    @Override
    public List<Artwork> findArtworkByOwner(int id) {
        String query = "select * from artwork where owner_id='" + id + "'";
        List<Artwork> artworks = jdbcTemplate.query(query, artworkRowMapper);
        return artworks;
    }

    @Override
    public void updateArtwork(int id) {
        String query = "update artwork set label= 'Sold' where id = '"+ id + "'";
        jdbcTemplate.update(query);
    }

    @Override
    public void updateArtworkLikes(int id, int likes) {
        likes = likes+1;
        String query = "update artwork set likes = '"+ likes +"' where id = '"+ id + "'";
        jdbcTemplate.update(query);
    }

    @Override
    public void acceptArt(int id) {
        String query = "update artwork set label = 'Unsold' where id = '"+ id + "' ";
        jdbcTemplate.update(query);
    }

    @Override
    public void declineArt(int id) {
        String query = "delete from artwork where id='"+id+"' ";
        jdbcTemplate.update(query);
    }
}
