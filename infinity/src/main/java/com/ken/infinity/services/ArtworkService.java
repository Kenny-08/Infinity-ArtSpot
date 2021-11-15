package com.ken.infinity.services;

import com.ken.infinity.models.Artwork;
import com.ken.infinity.models.User;

import java.util.List;

public interface ArtworkService {

    List<Artwork> getArtworks();
    void save(Artwork artwork, User user);
    Artwork findArtworkById(int id);
    List<Artwork> findArtworkByOwner(int id);
    void updateArtwork(int id);
    void updateArtworkLikes(int id, int likes);
    String getArtOwnerName(Artwork artwork);


}
