package com.ken.infinity.services;

public interface SecurityService {
    public String findLoggedInUsername();
    public void autoLogin(String username, String password);
    public boolean isLoggedIn();

}
