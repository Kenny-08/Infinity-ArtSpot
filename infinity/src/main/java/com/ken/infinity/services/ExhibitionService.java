package com.ken.infinity.services;

import com.ken.infinity.models.Exhibition;
import com.ken.infinity.models.User;
import com.ken.infinity.models.Workshop;

import java.util.List;

public interface ExhibitionService {
    List<Exhibition> getExhibitions();
    void save(Exhibition exhibition);
    Exhibition findExhibitionById(int id);
    void updateExhibitionSeats(int id, int seats);
    void updateExhibitionStatus(int id);
}
