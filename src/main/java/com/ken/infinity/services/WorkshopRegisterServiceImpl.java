package com.ken.infinity.services;


import com.ken.infinity.models.User;
import com.ken.infinity.models.Workshop;
import com.ken.infinity.models.WorkshopRegister;
import com.ken.infinity.repository.WorkshopRegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkshopRegisterServiceImpl implements WorkshopRegisterService{

    WorkshopRegisterRepo workshopRegisterRepo;

    @Autowired
    public WorkshopRegisterServiceImpl(WorkshopRegisterRepo workshopRegisterRepo) {
        this.workshopRegisterRepo = workshopRegisterRepo;
    }

    @Override
    public void save(WorkshopRegister workshopRegister, User user, Workshop workshop) {
        workshopRegister.setUser_id(user.getId());
        workshopRegister.setWorkshop_id(workshop.getId());
        workshopRegisterRepo.save(workshopRegister);
    }
}
