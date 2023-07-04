package com.estudospring.spingmongo.resources;

import com.estudospring.spingmongo.domain.User;
import com.estudospring.spingmongo.dto.UserDTO;
import com.estudospring.spingmongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = userService.findAll();
        List<UserDTO> userDTOList = list.stream().map(UserDTO::new).toList();
        return ResponseEntity.ok().body(userDTOList);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }
    @RequestMapping (method = RequestMethod.POST)
    public ResponseEntity<UserDTO> insertUser(@RequestBody UserDTO obj){
        var user = userService.insert(userService.fromDTO(obj));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDTO(user));
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Void> delete(@PathVariable String id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping (value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO obj, @PathVariable String id){
        var user = userService.fromDTO(obj);
        user.setId(id);
        user = userService.update(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.ok().body(new UserDTO(user));
    }
}
