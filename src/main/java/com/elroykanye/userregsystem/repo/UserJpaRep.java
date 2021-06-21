package com.elroykanye.userregsystem.repo;

import com.elroykanye.userregsystem.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRep extends JpaRepository<UserDTO, Long> {
    UserDTO findByName(String name);
}
