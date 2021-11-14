package com.ken.infinity.services;

import com.ken.infinity.models.User;
import com.ken.infinity.models.Workshop;
import com.ken.infinity.models.WorkshopRegister;

public interface WorkshopRegisterService {
    void save(WorkshopRegister workshopRegister, User user, Workshop workshop);
}
