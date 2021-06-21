package com.elroykanye.userregsystem.rest;

import com.elroykanye.userregsystem.dto.UserDTO;
import com.elroykanye.userregsystem.repo.UserJpaRep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
