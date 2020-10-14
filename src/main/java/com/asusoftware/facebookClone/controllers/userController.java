package com.asusoftware.facebookClone.controllers;

import com.asusoftware.facebookClone.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*") // Abilitazione delle CORS - Cross Origin Resource Sharing
@RequestMapping("/user")
public class userController {

    private final UserServiceImpl userService;

    private void getAllAccounts(){}
    private void getAccountById(){}
    private void insertPost(){}
    private void deletePost(){}
    private void createAccount(){}
    private void removeAccount(){}
}
