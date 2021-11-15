package com.ken.infinity.services;

import com.ken.infinity.models.Exhibition;
import com.ken.infinity.models.ExhibitionRegister;
import com.ken.infinity.models.User;
import com.ken.infinity.repository.ExhibitionRegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExhibitionRegisterServiceImpl implements ExhibitionRegisterService{

    ExhibitionRegisterRepo exhibitionRegisterRepo;

    @Autowired
    public ExhibitionRegisterServiceImpl(ExhibitionRegisterRepo exhibitionRegisterRepo) {
        this.exhibitionRegisterRepo = exhibitionRegisterRepo;
    }

    @Override
    public void save(ExhibitionRegister exhibitionRegister, User user, Exhibition exhibition) {
        exhibitionRegister.setUser_id(user.getId());
        exhibitionRegister.setExhibition_id(exhibition.getId());
        exhibitionRegisterRepo.save(exhibitionRegister);
    }
}
