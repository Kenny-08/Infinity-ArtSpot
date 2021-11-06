package com.ken.infinity.repository;

import com.ken.infinity.models.User;

public interface UserRepository {
    public User findByEmail(String email);
    public boolean userExists(String email);
    public void save(User user);
    public User findByUserId(int id);


}
