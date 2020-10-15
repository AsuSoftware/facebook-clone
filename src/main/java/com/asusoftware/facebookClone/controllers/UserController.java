package com.asusoftware.facebookClone.controllers;

import com.asusoftware.facebookClone.dto.LoginDTO;
import com.asusoftware.facebookClone.dto.UserDTO;
import com.asusoftware.facebookClone.models.User;
import com.asusoftware.facebookClone.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*") // Abilitazione delle CORS - Cross Origin Resource Sharing
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    private void createAccount(@RequestBody User user){
        userService.insertUser(user);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("login")
    private UserDTO findByEmailAndPassword(@RequestBody LoginDTO login) {
        return userService.findByEmailAndPassword(login.getEmail(), login.getPassword());
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping
    private List<UserDTO> getAllAccounts() {
        return userService.findUsers();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping(value = "/{id}")
    private UserDTO getAccountById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    private void updateUser(@RequestBody UserDTO userDto, @PathVariable Long id) {
        userService.updateUser(userDto, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    private void removeAccount(@RequestBody UserDTO userDto, @PathVariable Long id) {
        userService.deleteUser(id, userDto.getPassword());
    }
}
