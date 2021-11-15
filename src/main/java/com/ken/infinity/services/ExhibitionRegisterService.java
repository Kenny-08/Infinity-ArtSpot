package com.ken.infinity.services;

import com.ken.infinity.models.*;

public interface ExhibitionRegisterService {
    void save(ExhibitionRegister exhibitionRegister, User user, Exhibition exhibition);
}
