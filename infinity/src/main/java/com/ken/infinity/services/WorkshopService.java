package com.ken.infinity.services;

import com.ken.infinity.models.User;
import com.ken.infinity.models.Workshop;

import java.util.List;

public interface WorkshopService {
    List<Workshop> getWorkshops();
    void save(Workshop workshop, User user);
    Workshop findWorkshopById(int id);
    List<Workshop> findWorkshopByOrganizer(int id);
    void updateWorkshopSeats(int id, int seats);
    void updateWorkshopStatus(int id);
    String getWorkshopOrganizerName(Workshop workshop);
}
