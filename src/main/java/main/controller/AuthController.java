package main.controller;

import main.dto.AuthDTO;
import main.dto.ProfileDTO;
import main.servise.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
 @Autowired
    private AuthService authService;
 
    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> login(@RequestBody AuthDTO authDTO){
        return  ResponseEntity.ok(authService.auth(authDTO));
    }
}
