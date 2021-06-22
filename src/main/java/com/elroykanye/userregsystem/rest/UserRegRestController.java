package com.elroykanye.userregsystem.rest;

import com.elroykanye.userregsystem.dto.UserDTO;
import com.elroykanye.userregsystem.repo.UserJpaRep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserRegRestController {
    public static final Logger logger = LoggerFactory.getLogger(UserRegRestController.class);
    private UserJpaRep userJpaRep;

    @Autowired
    public void setUserJpaRep(UserJpaRep userJpaRep) {
        this.userJpaRep = userJpaRep;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> listUsers () {
        List<UserDTO> users = userJpaRep.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(value="/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@RequestBody final UserDTO user) {
        userJpaRep.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<Optional<UserDTO>> getUserById(@PathVariable("user_id") final Long id) {
        Optional<UserDTO> user = userJpaRep.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping(value = "/{user_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<UserDTO>> updateUser(@PathVariable("user_id") final Long id, @RequestBody UserDTO user) {
        // fetch a user based on id and set it to currentUser object of type DTO
        Optional<UserDTO> currUser = userJpaRep.findById(id);

        // update currUser object data with user object data
        currUser.get().setName(user.getName());
        currUser.get().setAddress(user.getAddress());
        currUser.get().setEmail(user.getEmail());

        // save the current user object
        userJpaRep.saveAndFlush(currUser.get());

        // return a response entity object
        return new ResponseEntity<>(currUser, HttpStatus.OK);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("user_id") final Long id) {
        userJpaRep.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
