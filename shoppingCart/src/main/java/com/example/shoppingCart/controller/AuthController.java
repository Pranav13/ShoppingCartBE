package com.example.shoppingCart.controller;

import javax.validation.Valid;

import com.example.shoppingCart.dto.request.LoginRequest;
import com.example.shoppingCart.dto.request.SignupRequest;
import com.example.shoppingCart.dto.response.JwtResponse;
import com.example.shoppingCart.dto.response.MessageResponse;
import com.example.shoppingCart.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

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
