package com.ken.infinity.services;

import com.ken.infinity.models.User;

public interface UserService {

    public void save(User user);
    public User findByUsername(String email);
    public boolean userExists(String email);
    public User findByUserId(int id);


}
