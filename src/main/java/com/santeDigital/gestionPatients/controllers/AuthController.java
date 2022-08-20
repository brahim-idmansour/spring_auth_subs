package com.santeDigital.gestionPatients.controllers;

import com.santeDigital.gestionPatients.models.AuthenticationRequest;
import com.santeDigital.gestionPatients.models.AuthenticationResponse;
import com.santeDigital.gestionPatients.models.UserModel;
import com.santeDigital.gestionPatients.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/subs")
    public ResponseEntity<?> subscribeUser(@RequestBody AuthenticationRequest authenticationRequest) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        String nom = authenticationRequest.getNom();
        String prenom = authenticationRequest.getPrenom();
        String role = authenticationRequest.getRole();
        UserModel userModel = new UserModel();
        userModel.setUsername(username);
        userModel.setPassword(password);
        userModel.setNom(nom);
        userModel.setPrenom(prenom);
        userModel.setRole(role);
        try {
            userRepository.save(userModel);
        }catch (Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("Error during subscription for User "+username));
        }
        return ResponseEntity.ok(new AuthenticationResponse("Successful Subscription for User "+username));
    }
    @PostMapping("/auth")
    public ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (Exception e) {
            return ResponseEntity.ok(new AuthenticationResponse("Error during authentication for User "+username));
        }
        return ResponseEntity.ok(new AuthenticationResponse("Successful authentication for User "+username));
    }
}
