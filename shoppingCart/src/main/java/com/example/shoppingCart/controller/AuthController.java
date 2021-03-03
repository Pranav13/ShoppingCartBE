package com.example.shoppingCart.controller;

import javax.validation.Valid;

import com.example.shoppingCart.payload.request.LoginRequest;
import com.example.shoppingCart.payload.request.SignupRequest;
import com.example.shoppingCart.payload.response.JwtResponse;
import com.example.shoppingCart.payload.response.MessageResponse;
import com.example.shoppingCart.repository.RoleRepository;
import com.example.shoppingCart.repository.UserRepository;
import com.example.shoppingCart.security.jwt.JwtUtils;
import com.example.shoppingCart.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
        return  ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        String s = authService.registerUser(signUpRequest);

        return ResponseEntity.ok(new MessageResponse(s));
    }
}
