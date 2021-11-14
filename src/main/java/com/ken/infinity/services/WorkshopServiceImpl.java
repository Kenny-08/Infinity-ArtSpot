package com.ken.infinity.services;

import com.ken.infinity.models.User;
import com.ken.infinity.models.Workshop;
import com.ken.infinity.repository.UserRepository;
import com.ken.infinity.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkshopServiceImpl implements WorkshopService{

    WorkshopRepository workshopRepository;
    UserRepository userRepository;

    @Autowired
    public WorkshopServiceImpl(WorkshopRepository workshopRepository, UserRepository userRepository) {
        this.workshopRepository = workshopRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Workshop> getWorkshops() {
        return workshopRepository.getWorkshops();
    }

    @Override
    public void save(Workshop workshop, User user) {

        workshop.setOrganizer_id(user.getId());
        workshop.setRegistered_seats(0);
        String imgUrl = "/img/workshop-photos/" + workshop.getId() + "/" + workshop.getImgUrl();
        workshop.setImgUrl(imgUrl);
        workshop.setStatus("notDone");
        workshopRepository.save(workshop);

    }

    @Override
    public Workshop findWorkshopById(int id) {
        return workshopRepository.findWorkshopById(id);
    }

    @Override
    public List<Workshop> findWorkshopByOrganizer(int id) {
        return workshopRepository.findWorkshopByOrganizer(id);
    }

    @Override
    public void updateWorkshopSeats(int id, int seats) {
        workshopRepository.updateWorkshopSeats(id, seats);
    }

    @Override
    public void updateWorkshopStatus(int id) {
        workshopRepository.updateWorkshopStatus(id);
    }

    @Override
    public String getWorkshopOrganizerName(Workshop workshop) {
        User user = userRepository.findByUserId(workshop.getOrganizer_id());
        String organizer = user.getFirstName();
        return organizer;
    }
}
