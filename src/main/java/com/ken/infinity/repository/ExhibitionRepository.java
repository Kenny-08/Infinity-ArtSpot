package com.ken.infinity.repository;

import com.ken.infinity.models.Exhibition;


import java.util.List;

public interface ExhibitionRepository {

    List<Exhibition> getExhibitions();
    void save(Exhibition exhibition);
    Exhibition findExhibitionById(int id);
    void updateExhibitionSeats(int id, int seats);
    void updateExhibitionStatus(int id);

}
