package com.chirag.service;

import com.chirag.config.JwtProvider;
import com.chirag.model.User;
import com.chirag.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    UserRepository userRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public JwtProvider getJwtProvider() {
        return jwtProvider;
    }

    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
         String email=jwtProvider.getEmailFromJwtToken(jwt);
         User user=findUserByEmail(email);
         return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);

        if(user==null){
            throw new Exception(("user not found"));
        }
        return user;
    }
}
