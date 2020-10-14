package com.asusoftware.facebookClone.services.userService;

import com.asusoftware.facebookClone.dto.UserDTO;

public interface UpdateUserService {
    void updateUser(UserDTO userDto, Long id);
}
