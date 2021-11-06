package com.ken.infinity.services;


import com.ken.infinity.models.User;
import com.ken.infinity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
        private UserRepository userRepository;
        private BCryptPasswordEncoder bCryptPasswordEncoder;

        @Autowired
        public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
            this.bCryptPasswordEncoder = bCryptPasswordEncoder;
            this.userRepository = userRepository;
        }

        @Override
        public void save(User user){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }

        @Override
        public User findByUsername(String email){
            return userRepository.findByEmail(email);
        }

        @Override
        public boolean userExists(String email) {
            return userRepository.userExists(email);
        }


        @Override
        public User findByUserId(int id) {
            return userRepository.findByUserId(id);
        }

}
