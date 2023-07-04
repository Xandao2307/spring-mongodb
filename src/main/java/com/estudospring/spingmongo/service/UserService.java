package com.estudospring.spingmongo.service;

import com.estudospring.spingmongo.domain.User;
import com.estudospring.spingmongo.dto.UserDTO;
import com.estudospring.spingmongo.repository.UserRepository;
import com.estudospring.spingmongo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;
    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(String id){
         Optional<User> user = userRepository.findById(id);
         return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert(User user){
        return userRepository.insert(user);
    }

    public User fromDTO(UserDTO userDTO){
        return new User(userDTO.getId(),userDTO.getName(), userDTO.getEmail());
    }

    public void delete(String id){
        userRepository.deleteById(id);
    }

    public User update(User obj){
        User newObj = findById(obj.getId());
        updateData(newObj,obj);
        return userRepository.save(newObj);
    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }
}
