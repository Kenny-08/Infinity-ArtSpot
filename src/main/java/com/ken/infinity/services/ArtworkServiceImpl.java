package com.ken.infinity.services;

import com.ken.infinity.models.Artwork;
import com.ken.infinity.models.User;
import com.ken.infinity.repository.ArtworkRepository;
import com.ken.infinity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtworkServiceImpl implements ArtworkService {
    public ArtworkRepository artworkRepository;
    public UserRepository userRepository;

    @Autowired
    public ArtworkServiceImpl(ArtworkRepository artworkRepository, UserRepository userRepository) {
        this.artworkRepository = artworkRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(Artwork artwork, User user) {
        System.out.println(user.getId());
        artwork.setOwner_id(user.getId());
        artwork.setLikes(0);
        artwork.setLabel("Unsold");
        String imgUrl = "/img/artwork-photos/" + artwork.getId() + "/" + artwork.getImgUrl();
        artwork.setImgUrl(imgUrl);
        System.out.println(artwork.getImgUrl());
        artworkRepository.save(artwork);
    }

    @Override
    public List<Artwork> getArtworks() {
        return artworkRepository.findAllArtworks();
    }

    @Override
    public Artwork findArtworkById(int id) {
        return artworkRepository.findArtworkById(id);
    }

    @Override
    public List<Artwork> findArtworkByOwner(int id) {
        return artworkRepository.findArtworkByOwner(id);
    }

    @Override
    public void updateArtwork(Artwork artwork) {
        artworkRepository.updateArtwork(artwork);
    }

    @Override
    public String getArtOwnerName(Artwork artwork) {
        User user = userRepository.findByUserId(artwork.getOwner_id());
        String name = user.getFirstName();
        return name;
    }

}
