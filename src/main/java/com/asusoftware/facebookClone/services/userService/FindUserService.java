package com.asusoftware.facebookClone.services.userService;

import com.asusoftware.facebookClone.dto.UserDTO;

import java.util.List;

public interface FindUserService {

    List<UserDTO> findUsers();
    UserDTO findUserById(Long id) throws Exception;
    UserDTO findByEmailAndPassword(String email, String password);
}
