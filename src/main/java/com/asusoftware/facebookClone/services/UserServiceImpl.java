package com.asusoftware.facebookClone.services;

import com.asusoftware.facebookClone.dto.UserDTO;
import com.asusoftware.facebookClone.exceptions.InvalidPasswordException;
import com.asusoftware.facebookClone.exceptions.NotFoundUserException;
import com.asusoftware.facebookClone.models.User;
import com.asusoftware.facebookClone.repositories.UserRepository;
import com.asusoftware.facebookClone.services.userService.CreateUserService;
import com.asusoftware.facebookClone.services.userService.DeleteUserService;
import com.asusoftware.facebookClone.services.userService.FindUserService;
import com.asusoftware.facebookClone.services.userService.UpdateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // (Lombok) ci crea lui il costruttore con gli argomenti(dipenedenze)
public class UserServiceImpl implements CreateUserService, UpdateUserService, FindUserService, DeleteUserService {

    private final UserRepository repoUser;

    @Override
    public List<UserDTO> findUsers() {
        return repoUser.findAll()
                .stream()
                .map(this::convertUserInDto)
                .collect(Collectors.toList());
    }

    @Override
    public void insertUser(User user) {
        repoUser.save(user);
    }


    @Override
    public UserDTO findUserById(Long id) {
        User user = repoUser.findById(id)
                .orElseThrow(NotFoundUserException::new);
        return convertUserInDto(user);
    }


    @Override
    public UserDTO findByEmailAndPassword(String email, String password) {
        User user = repoUser.findByEmailAndPassword(email, password).orElseThrow(NotFoundUserException::new);
        return convertUserInDto(user);
    }


    @Override
    public void updateUser(UserDTO userDto, Long id) {
        User userNew = repoUser.findById(id)
                .orElseThrow(NotFoundUserException::new);
        User user = convertUserDtoInEntity(userDto);
        userNew.setName(user.getName());
        userNew.setLastName(user.getLastName());
        userNew.setGender(user.getGender());
        userNew.setBirthday(user.getBirthday());
        userNew.setPassword(user.getPassword());
        repoUser.save(userNew);
    }


    @Override
    public void deleteUser(Long id, String password) {
        User user = repoUser.findById(id).orElseThrow(NotFoundUserException::new);
        if (user.getPassword().equals(password)) {
            repoUser.deleteById(id);
        } else {
            throw new InvalidPasswordException();
        }
    }


    private UserDTO convertUserInDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setProfileImage(user.getProfileImage());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setBirthday(user.getBirthday());
        userDto.setGender(user.getGender());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private User convertUserDtoInEntity(UserDTO userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setProfileImage(userDto.getProfileImage());
        user.setLastName(userDto.getLastName());
        user.setGender(userDto.getGender());
        user.setBirthday(userDto.getBirthday());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
