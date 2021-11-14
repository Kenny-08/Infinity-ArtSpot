package com.ken.infinity.repository;

import com.ken.infinity.models.Workshop;

import java.util.List;

public interface WorkshopRepository {

    List<Workshop> getWorkshops();
    void save(Workshop workshop);
    Workshop findWorkshopById(int id);
    List<Workshop> findWorkshopByOrganizer(int id);
    void updateWorkshopSeats(int id, int seats);
    void updateWorkshopStatus(int id);

}
