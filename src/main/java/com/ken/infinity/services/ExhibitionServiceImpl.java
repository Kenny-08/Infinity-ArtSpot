package com.ken.infinity.services;

import com.ken.infinity.models.Exhibition;
import com.ken.infinity.repository.ExhibitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExhibitionServiceImpl implements ExhibitionService{

    ExhibitionRepository exhibitionRepository;

    @Autowired
    public ExhibitionServiceImpl(ExhibitionRepository exhibitionRepository) {
        this.exhibitionRepository = exhibitionRepository;
    }

    @Override
    public List<Exhibition> getExhibitions() {
        return exhibitionRepository.getExhibitions();
    }

    @Override
    public void save(Exhibition exhibition) {
        exhibition.setRegistered_seats(0);
        String imgUrl = "/img/exhibition-photos/" + exhibition.getId() + "/" + exhibition.getImgUrl();
        exhibition.setImgUrl(imgUrl);
        exhibition.setStatus("notDone");
        exhibitionRepository.save(exhibition);
    }

    @Override
    public Exhibition findExhibitionById(int id) {
        return exhibitionRepository.findExhibitionById(id);
    }

    @Override
    public void updateExhibitionSeats(int id, int seats) {
        exhibitionRepository.updateExhibitionSeats(id, seats);
    }

    @Override
    public void updateExhibitionStatus(int id) {
        exhibitionRepository.updateExhibitionStatus(id);
    }
}
