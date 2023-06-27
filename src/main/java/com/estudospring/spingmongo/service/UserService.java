package com.estudospring.spingmongo.service;

import com.estudospring.spingmongo.domain.User;
import com.estudospring.spingmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;
    public List<User> findAll(){
        return userRepository.findAll();

    }
}
